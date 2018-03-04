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
    public Patient getAllPatients(@PathVariable int patientId) {
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
    public void deletePatient(@PathVariable int patientId) {
        patientService.deletePatient(patientId);
    }

}
