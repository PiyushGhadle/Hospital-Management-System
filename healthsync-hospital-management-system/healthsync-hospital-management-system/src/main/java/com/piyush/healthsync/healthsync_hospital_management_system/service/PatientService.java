package com.piyush.healthsync.healthsync_hospital_management_system.service;

import com.piyush.healthsync.healthsync_hospital_management_system.models.Patient;
import com.piyush.healthsync.healthsync_hospital_management_system.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    public Page<Patient> getALlPatients(int page, int pageSize) {
        try {
            System.out.println("Into service layer");
            Pageable pageable = PageRequest.of(page, pageSize);
            return patientRepository.findAll(pageable);
        } catch (Exception e) {
            logger.error("An error occurred while fetching all patients : {}", e.getMessage());
            return null;
        }
    }

    public  Patient getPatientById(Long id) {
        try {
            return patientRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("An error occurred while fetching patient with id : {}", e.getMessage());
            return null;
        }
    }


    public Patient createPatirnt(Patient patient) {
        try {
            return patientRepository.save(patient);
        } catch (Exception e) {
            logger.error("An error occurred while creating patients : {}", e.getMessage());
            return null;
        }
    }

    public Patient updatePatient(Long id, Patient patient) {
        try {
            Optional<Patient> existingPatient = patientRepository.findById(id);
            if (existingPatient.isPresent()) {
                Patient p = existingPatient.get();
                p.setName(patient.getName());
                p.setAge(patient.getAge());
                p.setGender(patient.getGender());
                return patientRepository.save(p);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("An error occurred while updating patient with id {} : {}", id, e.getMessage());
            return null;
        }
    }

    public void deletePatient(Long id) {
        try {
            logger.info("Deleting patient with id {}", id);
            patientRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("An error occurred while Delete patients id {} : {}",id, e.getMessage());
        }
    }
}
