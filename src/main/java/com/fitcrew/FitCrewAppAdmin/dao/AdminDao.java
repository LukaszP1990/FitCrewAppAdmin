package com.fitcrew.FitCrewAppAdmin.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fitcrew.FitCrewAppAdmin.domains.AdminDocument;

@Repository
public interface AdminDao extends MongoRepository<AdminDocument, Long> {
    Optional<AdminDocument> findByEmail(String email);
}
