package com.piyush.healthsync.healthsync_hospital_management_system.service;

import com.piyush.healthsync.healthsync_hospital_management_system.models.Appointment;
import com.piyush.healthsync.healthsync_hospital_management_system.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
    
    public List<Appointment> getALLAppointments() {
        try {
//            Pageable pageable = PageRequest.of(page, pageSize);
            return appointmentRepository.findAll();
        } catch (Exception e) {
            logger.error("An error occurred while fetching all appointment : {}", e.getMessage());
            return null;
        }
    }

    public Appointment getAppointmentById(Long id) {
        try {
            return appointmentRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("An error occurred while fetching appointment by Id : {}", e.getMessage());
            return null;
        }
    }

    public Appointment createAppointment(Appointment appointment) {
        try {
            return appointmentRepository.save(appointment);
        } catch (Exception e) {
            logger.error("An error occurred while create appointment : {}", e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(Long id, Appointment appointment) {
        try {
            Optional<Appointment> existingAppointment = appointmentRepository.findById(id);
            if (existingAppointment.isPresent()) {
                Appointment existing = existingAppointment.get();
                existing.setPatientId(appointment.getPatientId());
                existing.setDoctorId(appointment.getDoctorId());
                existing.setData(appointment.getData());
                return appointmentRepository.save(existing);
            }else
                return null;
        } catch (Exception e) {
            logger.error("An error occurred while update appointment id {}: {}",id, e.getMessage());
            return null;
        }
    }

    public void deleteAppointment(Long id) {
        try {
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("An error occurred while Delete appointment id {}: {}",id, e.getMessage());

        }
    }
}
