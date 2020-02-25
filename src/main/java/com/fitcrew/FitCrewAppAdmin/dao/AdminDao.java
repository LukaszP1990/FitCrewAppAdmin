package com.fitcrew.FitCrewAppAdmin.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fitcrew.FitCrewAppAdmin.domains.AdminEntity;

@Repository
public interface AdminDao extends MongoRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByEmail(String email);
}
