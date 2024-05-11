package com.fusm.moodle.util;

import com.fusm.moodle.external.ISettingsService;
import com.fusm.moodle.model.external.SettingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SharedMethod {

    @Autowired
    private ISettingsService settingsService;


    public Integer getSettingValue(String settingName) {
        return Integer.parseInt(
                settingsService.getSetting(
                        SettingRequest.builder()
                                .settingName(settingName)
                                .build()
                )
        );
    }

    public String getSettingValueOnString(String settingName) {
        return settingsService.getSetting(
                SettingRequest.builder()
                        .settingName(settingName)
                        .build()
        );
    }

}
