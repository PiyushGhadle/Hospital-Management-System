package com.piyush.healthsync.healthsync_hospital_management_system.controllers;

import com.piyush.healthsync.healthsync_hospital_management_system.models.Patient;
import com.piyush.healthsync.healthsync_hospital_management_system.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping()
    public Page<Patient> getALlPatients(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println("getALlPatients()");
        return patientService.getALlPatients(page,pageSize);
    }

    @GetMapping("/{id}")
    public  Patient getPatientById(@PathVariable Long id) {
        System.out.println("getPatientById(" + id + ")");
        return patientService.getPatientById(id);
    }

    @PostMapping()
    public  Patient createPatient(@RequestBody Patient patient) {
        System.out.println("createPatient(" + patient + ")");
        return patientService.createPatirnt(patient);
    }

    @PutMapping("/{id}")
    public  Patient updatePatient(@PathVariable Long id,@RequestBody Patient patient) {
        System.out.println("updatePatient(" + patient + ")");
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("/{id}")
    public  void deletePatient(@PathVariable Long id) {
        System.out.println("deletePatient(" + id + ")");
        patientService.deletePatient(id);
    }


}
