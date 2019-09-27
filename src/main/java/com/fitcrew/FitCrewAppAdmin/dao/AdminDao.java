package com.fitcrew.FitCrewAppAdmin.dao;

import com.fitcrew.FitCrewAppAdmin.domains.AdminEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends CrudRepository<AdminEntity, Long> {
    AdminEntity findByEmail(String email);
}
