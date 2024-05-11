package com.fusm.moodle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {

    private int id;
    private String name;
    private String description;
    private int format;
    private int courseid;
    private Integer categoryid;
    private Integer groupid;
    private int userid;
    private Integer repeatid;
    private String modulename;
    private Integer instance;
    private String eventtype;
    private long timestart;
    private int timeduration;
    private int visible;
    private int sequence;
    private long timemodified;
    private Integer subscriptionid;

}
