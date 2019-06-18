package com.domain;

import com.domain.Entity.Configuration;
import com.domain.Service.ArticleService;
import com.domain.Service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ConfigurationService configurationService;

//    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Scheduled(fixedRate = 300000)
    public void test() throws IOException {
//        log.info("The time is now {}", dateFormat.format(new Date()));

        Iterable<Configuration> configurations = configurationService.readConfigurationFromDB();
        List<String> feedLinksList = new ArrayList<>();
        configurations.forEach(configuration -> feedLinksList.add(configuration.getFeedLink()));

        String feedLink = "http://www.dsl.sk/export/rss_articles.php";
        articleService.getArticles(feedLink);
    }
}
