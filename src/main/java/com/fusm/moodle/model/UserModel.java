package com.fusm.moodle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private String fullname;
    private String email;
    private String department;
    private Long firstAccess;
    private Long lastAccess;
    private String auth;
    private Boolean suspended;
    private Boolean confirmed;
    private String lang;
    private String theme;
    private String timezone;
    private Integer mailformat;
    private String description;
    private String descriptionformat;
    private String city;
    private String country;
    private String profileimageurlsmall;
    private String profileimageurl;

}
