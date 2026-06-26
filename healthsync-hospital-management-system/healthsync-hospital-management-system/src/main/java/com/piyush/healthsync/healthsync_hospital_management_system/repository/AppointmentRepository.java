package com.piyush.healthsync.healthsync_hospital_management_system.repository;

import com.piyush.healthsync.healthsync_hospital_management_system.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
