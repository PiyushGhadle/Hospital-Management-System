package com.piyush.healthsync.healthsync_hospital_management_system.repository;

import com.piyush.healthsync.healthsync_hospital_management_system.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
