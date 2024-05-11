package com.fusm.moodle.service.impl;

import com.fusm.moodle.external.ISettingsService;
import com.fusm.moodle.model.*;
import com.fusm.moodle.service.IMoodleService;
import com.fusm.moodle.util.Constant;
import com.fusm.moodle.util.SharedMethod;
import com.fusm.moodle.webclient.WebClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoodleService implements IMoodleService {

    @Autowired
    private WebClientConnector webClientConnector;

    @Autowired
    private SharedMethod sharedMethod;


    @Override
    public CalendarEvent getEventsByUser(EventSearch eventSearch) {
        CalendarEvent calendarEvent = CalendarEvent.builder()
                .events(new ArrayList<>())
                .warnings(new ArrayList<>())
                .build();
        String token = sharedMethod.getSettingValueOnString(Constant.TOKEN_MOODLE);
        String moodleUrl = sharedMethod.getSettingValueOnString(Constant.URL_MOODLE);
        String username = getUsername(eventSearch.getCreatedBy(), token, moodleUrl);
        List<Integer> courses = getCoursesEnrolled(username, token, moodleUrl);
        for (Integer courseId : courses) {
            calendarEvent.getEvents()
                    .addAll(getEventsByCourse(courseId, eventSearch, moodleUrl, token));
        }
        return calendarEvent;
    }

    private String getUsername(String email, String token, String moodleUrl) {
        String username = null;
        try {
            User user = webClientConnector.connectWebClient(moodleUrl)
                    .post()
                    .uri("?wstoken=" + token + "&wsfunction=core_user_get_users" +
                            "&criteria[0][key]=email" +
                            "&criteria[0][value]=" + email +
                            "moodlewsrestformat")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();
            if (user != null) {
                if (!user.getUsers().isEmpty()) {
                    username = user.getUsers().get(0).getUsername();
                }
            }
        } catch (Exception e) {}
        return username;
    }

    private List<Integer> getCoursesEnrolled(String username, String token, String moodleUrl) {
        List<Integer> courses = new ArrayList<>();
        try {
            Course course = webClientConnector.connectWebClient(moodleUrl)
                    .post()
                    .uri("?wstoken=" + token + "&wsfunction=core_enrol_get_users_courses" +
                            "&username=" + username +
                            "moodlewsrestformat")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .retrieve()
                    .bodyToMono(Course.class)
                    .block();
            if (course != null) {
                for (CourseModel courseModel : course.getCourses()) {
                    courses.add(courseModel.getId());
                }
            }
        } catch (Exception e) {}
        return courses;
    }

    private List<EventModel> getEventsByCourse(Integer courseId, EventSearch eventSearch, String moodleUrl, String token) {
        List<EventModel> eventModels = new ArrayList<>();
        try {
            CalendarEvent calendarEvent = webClientConnector.connectWebClient(moodleUrl)
                    .post()
                    .uri("?wstoken=" + token + "&wsfunction=core_calendar_get_calendar_events" +
                            "&events[courseids][0]=" + courseId +
                            "&moodlewsrestformat=json" +
                            "&options[timestart]=" + eventSearch.getStartDate() +
                            "&options[timeend]=" + eventSearch.getEndDate())
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .retrieve()
                    .bodyToMono(CalendarEvent.class)
                    .block();
            if (calendarEvent != null) {
                eventModels = calendarEvent.getEvents();
            }
        } catch (Exception e) {}
        return eventModels;
    }

}
