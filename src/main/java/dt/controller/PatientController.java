package dt.controller;

import dt.model.Patient;
import dt.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @RequestMapping("/patients/{patientId}")
    public Patient getPatient(@PathVariable long patientId) {
        return patientService.getPatient(patientId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/patients")
    public void addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/patients")
    public void updatePatient(@RequestBody Patient patient) {
        patientService.updatePatient(patient);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/patients/{patientId}")
    public void deletePatient(@PathVariable long patientId) {
        patientService.deletePatient(patientId);
    }

    @RequestMapping("doctors/{doctorId}/patients")
    public List<Patient> getPatientsOfDoctor(@PathVariable long doctorId) {
        return patientService.getPatientsOfDoctor(doctorId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "doctors/{doctorId}/patients")
    public List<Patient> savePatientOfDoctor(@PathVariable long doctorId, @RequestBody Patient patient) {
        return patientService.savePatientOfDoctor(doctorId,patient);
    }

    @RequestMapping(method = RequestMethod.POST, value = "doctors/{doctorId}/patients/{patientId}")
    public Patient saveExistingPatientOfDoctor(@PathVariable long doctorId, @PathVariable long patientId) {
        return patientService.saveExistingPatientOfDoctor(doctorId,patientId);
    }

    @RequestMapping("/users/{userId}/patient")
    public Patient getPatientAttachedToAccount(@PathVariable long userId) {
        return patientService.getPatientAttachedToAccount(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/patient")
    public Patient savePatientWithAccount(@PathVariable long userId) {
        return patientService.addPatientWithAccount(userId);
    }
}
