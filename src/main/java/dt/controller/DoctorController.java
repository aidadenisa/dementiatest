package dt.controller;

import dt.model.Doctor;
import dt.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @RequestMapping("/doctors/{doctorId}")
    public Doctor getAllDoctors(@PathVariable int doctorId) {
        return doctorService.getDoctor(doctorId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/doctors")
    public void addDoctor(@RequestBody Doctor doctor) {
        doctorService.addDoctor(doctor);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/doctors")
    public void updateDoctor(@RequestBody Doctor doctor) {
        doctorService.updateDoctor(doctor);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/doctors/{doctorId}")
    public void deleteDoctor(@PathVariable int doctorId) {
        doctorService.deleteDoctor(doctorId);
    }
    
}
