package com.domain.Service;

import com.domain.Entity.Settings;
import com.domain.Repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    public Iterable<Settings> readSettingsFromDB() {
        return settingsRepository.findAll();
    }
}
