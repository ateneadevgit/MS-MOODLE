package com.fusm.moodle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseModel {

    private Integer id;
    private String fullname;
    private String shortname;
    private String category;
    private Integer enrolledusercount;

}
