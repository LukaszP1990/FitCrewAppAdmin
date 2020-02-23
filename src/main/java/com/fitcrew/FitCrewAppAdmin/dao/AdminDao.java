package com.fitcrew.FitCrewAppAdmin.dao;

import java.util.Optional;

import com.fitcrew.FitCrewAppAdmin.domains.AdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends CrudRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByEmail(String email);
}
