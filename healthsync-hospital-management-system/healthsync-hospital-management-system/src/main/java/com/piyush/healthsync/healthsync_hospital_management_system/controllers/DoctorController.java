package com.piyush.healthsync.healthsync_hospital_management_system.controllers;

import com.piyush.healthsync.healthsync_hospital_management_system.models.Doctor;
import com.piyush.healthsync.healthsync_hospital_management_system.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public Page<Doctor> getAllDoctors(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "3") int pageSize) {
        return doctorService.getAllDoctors(page, pageSize);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        System.out.println("DoctorController.getDoctorById()");
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        System.out.println("DoctorController.createDoctor()");
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        System.out.println("DoctorController.updateDoctor()");
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        System.out.println("DoctorController.deleteDoctor()");
        doctorService.deleteDoctor(id);
    }
}
