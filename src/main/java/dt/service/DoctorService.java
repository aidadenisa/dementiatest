package dt.service;

import dt.model.Doctor;
import dt.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {

        List<Doctor> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctors::add);
        return doctors;

    }

    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor getDoctor(int doctorId) {
        return doctorRepository.findOne(doctorId);
    }

    public void updateDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(int doctorConfigId) {
        doctorRepository.delete(doctorConfigId);
    }
    
}
