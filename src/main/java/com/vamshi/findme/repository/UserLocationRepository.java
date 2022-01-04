package com.vamshi.findme.repository;

import com.vamshi.findme.model.UserLocation;
import com.vamshi.findme.model.UserLocationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, UserLocationId> {

    @Override
    void deleteAll();
}
