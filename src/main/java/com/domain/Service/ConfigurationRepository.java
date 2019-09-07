package com.domain.Service;

import com.domain.Entity.Configuration;
import org.springframework.data.repository.CrudRepository;

public interface ConfigurationRepository extends CrudRepository<Configuration,Integer> {
    Boolean existsByFeedNameAndId(String feedName, int id);
}
