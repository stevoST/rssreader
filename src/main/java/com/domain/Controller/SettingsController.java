package com.domain.Controller;

import com.domain.Entity.Settings;
import com.domain.Service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @RequestMapping (method = RequestMethod.GET)
    public Iterable<Settings> getSettings() {
        return settingsService.readSettingsFromDB();
    }
}
