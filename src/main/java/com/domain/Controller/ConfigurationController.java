package com.domain.Controller;

import com.domain.Entity.Configuration;
import com.domain.Service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Configuration> getConfiguration() {
        return configurationService.readConfigurationFromDB();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Configuration> getConfigurationById(@PathVariable("id") int id){
        return configurationService.getConfigurationById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Configuration addConfiguration(@RequestBody Configuration configuration){
        configurationService.addConfigurationToDB(configuration);
        return configuration;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteConfigurationById(@PathVariable("id") int id){
        configurationService.deleteConfigurationById(id);
    }
}
