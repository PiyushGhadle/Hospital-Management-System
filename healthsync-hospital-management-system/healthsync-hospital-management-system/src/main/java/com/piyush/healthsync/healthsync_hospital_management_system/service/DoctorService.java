package com.piyush.healthsync.healthsync_hospital_management_system.service;

import com.piyush.healthsync.healthsync_hospital_management_system.models.Doctor;
import com.piyush.healthsync.healthsync_hospital_management_system.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    public Page<Doctor> getAllDoctors(int page, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            return doctorRepository.findAll(pageable);
        } catch (Exception e) {
            logger.error("An error occurred while fetching all doctors : {}", e.getMessage());
            return null;
        }
    }

    public Doctor getDoctorById(Long id) {
        try {
            return doctorRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("An error occurred while fetching doctors by Id : {}", e.getMessage());
            return null;
        }
    }

    public Doctor createDoctor(Doctor doctor) {
        try {
            return doctorRepository.save(doctor);
        } catch (Exception e) {
            logger.error("An error occurred while creating doctors : {}", e.getMessage());
            return null;
        }
    }

    public Doctor updateDoctor(Long id, Doctor doctor) {
        try {
            Optional<Doctor> existingDoctor = doctorRepository.findById(id);
            if(existingDoctor.isPresent()) {
                Doctor d =  existingDoctor.get();
                d.setName(doctor.getName());
                d.setSpeciality(doctor.getSpeciality());
                return  doctorRepository.save(d);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("An error occurred while updating doctors id {}: {}",id, e.getMessage());
            return null;
        }
    }

    public void deleteDoctor(Long id) {
        try {
            doctorRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("An error occurred while Delete doctors id {}: {}",id, e.getMessage());

        }
    }
}
