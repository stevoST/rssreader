package com.domain.Service;

import com.domain.Entity.Article;
import com.domain.Entity.Configuration;
import com.domain.Repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private ArticleService articleService;

    public Iterable<Configuration> readConfigurationFromDB() {
        return configurationRepository.findAll();
    }

    public Optional<Configuration> getConfigurationById(int id) {
        return configurationRepository.findById(id);
    }

    public void addConfigurationToDB(Configuration configuration) {
        configurationRepository.save(configuration);
    }

    public void deleteConfigurationById(int id) {
        configurationRepository.deleteById(id);
    }

    public Iterable<Article> validateConfigurationByName(String feedName, int id) {
        if (configurationRepository.existsByFeedNameAndId(feedName, id)) {
            return articleService.readArticlesByFeedNameFromDB(feedName);
        } else {
            return null;
        }
    }
}
