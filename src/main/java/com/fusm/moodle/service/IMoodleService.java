package com.fusm.moodle.service;

import com.fusm.moodle.model.CalendarEvent;
import com.fusm.moodle.model.EventSearch;
import org.springframework.stereotype.Service;

@Service
public interface IMoodleService {

    CalendarEvent getEventsByUser(EventSearch eventSearch);

}
