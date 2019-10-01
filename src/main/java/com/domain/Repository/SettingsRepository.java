package com.domain.Repository;

import com.domain.Entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SettingsRepository extends JpaRepository<Settings,Integer> {

    @Query(value = "SELECT value FROM Settings u WHERE u.property='email'")
    String findEmail();
}
