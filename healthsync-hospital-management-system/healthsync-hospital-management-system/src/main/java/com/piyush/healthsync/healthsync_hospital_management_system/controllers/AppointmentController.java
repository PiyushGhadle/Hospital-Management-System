package com.piyush.healthsync.healthsync_hospital_management_system.controllers;

import com.piyush.healthsync.healthsync_hospital_management_system.models.Appointment;
import com.piyush.healthsync.healthsync_hospital_management_system.service.AppointmentService;
import com.piyush.healthsync.healthsync_hospital_management_system.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private WebhookService webhookService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getALLAppointments()
    {
        System.out.println("getAllAppointments");
        return appointmentService.getALLAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id)
    {
        System.out.println("getAppointmentById");
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointmentRequest)
    {
        System.out.println("createAppointment");
        Appointment appointment = appointmentService.createAppointment(appointmentRequest);

        // Prepare the webhook payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("appointmentID", appointment.getId());
        payload.put("patientID", appointment.getPatientId());
        payload.put("doctorID", appointment.getDoctorId());
        payload.put("appointmentDate", appointment.getData());

        String webhookUrl = "http://localhost:8081/webhook";
        webhookService.sendWebhook(webhookUrl, payload);

        return appointment;
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment){
        System.out.println("updateAppointment");
        return appointmentService.updateAppointment(id, appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id)
    {
        System.out.println("deleteAppointment");
        appointmentService.deleteAppointment(id);
    }
}
