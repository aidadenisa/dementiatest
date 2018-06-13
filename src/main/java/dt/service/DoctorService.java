package dt.service;

import dt.model.Doctor;
import dt.model.UserAccount;
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

    public Doctor getDoctor(long doctorId) {
        return doctorRepository.findOne(doctorId);
    }

    public void updateDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(long doctorConfigId) {
        doctorRepository.delete(doctorConfigId);
    }

    public Doctor getDoctorAttachedToAccount(long userId) {
        return doctorRepository.findByUserAccountId(userId);
    }

    public Doctor addDoctorWithAccount(long userId) {
        UserAccount user = new UserAccount();
        user.setId(userId);
        Doctor newDoctor = new Doctor();
        newDoctor.setUserAccount(user);
        return doctorRepository.save(newDoctor);
    }
}
