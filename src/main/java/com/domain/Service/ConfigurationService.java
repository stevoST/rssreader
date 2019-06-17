package com.domain.Service;

import com.domain.Entity.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

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
}
