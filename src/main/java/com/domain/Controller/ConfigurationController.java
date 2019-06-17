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
//        Configuration configuration = new Configuration();
//        Configuration configuration1 = new Configuration();
//        List<Configuration> configurationList = new ArrayList<Configuration>();
//
//        configuration.setFeedName("dsl.sk");
//        configuration.setFeedLink("http://www.dsl.sk/export/rss_articles.php");
//
//        configuration1.setFeedName("zive.cz");
//        configuration1.setFeedLink("https://www.zive.cz/rss/sc-47/");
//        configurationList.add(configuration);
//        configurationList.add(configuration1);

//        return  configurationList;
        return configurationService.readConfigurationFromDB();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Configuration> getConfigurationById(@PathVariable("id") int id){
        return configurationService.getConfigurationById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addConfiguration(@RequestBody Configuration configuration){
        configurationService.addConfigurationToDB(configuration);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteConfigurationById(@PathVariable("id") int id){
        configurationService.deleteConfigurationById(id);
    }
}
