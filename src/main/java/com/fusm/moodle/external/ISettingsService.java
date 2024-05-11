package com.fusm.moodle.external;

import com.fusm.moodle.model.external.SettingRequest;
import org.springframework.stereotype.Service;

@Service
public interface ISettingsService {
    String getSetting(SettingRequest settingRequest);
}
