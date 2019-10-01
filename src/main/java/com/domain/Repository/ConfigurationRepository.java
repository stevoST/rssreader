package com.domain.Repository;

import com.domain.Entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration,Integer> {
    Boolean existsByFeedNameAndId(String feedName, int id);
}
