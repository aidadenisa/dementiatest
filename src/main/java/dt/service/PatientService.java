package dt.service;

import dt.model.Doctor;
import dt.model.Patient;
import dt.model.UserAccount;
import dt.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    public List<Patient> getAllPatients() {

        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;

    }

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getPatient(int patientId) {
        return patientRepository.findOne(patientId);
    }

    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void deletePatient(int patientConfigId) {
        patientRepository.delete(patientConfigId);
    }

    public List<Patient> getPatientsOfDoctor(int doctorId) {
        Doctor doctor = doctorService.getDoctor(doctorId);
        if(doctor != null) {
            return doctor.getPatients();
        } else {
            return null;
        }
    }

    public List<Patient> savePatientOfDoctor(int doctorId, Patient patient) {

        Doctor doctor = doctorService.getDoctor(doctorId);
        if(doctor != null) {
            doctor.getPatients().add(patient);
            doctorService.updateDoctor(doctor);
            return doctor.getPatients();
        } else {
            return null;
        }

    }

    public Patient getPatientAttachedToAccount(int userId) {
        return patientRepository.findByUserAccountId(userId);
    }

    public void addPatientWithAccount(int userId) {
//        UserAccount user = userService.getUser(userId);
        UserAccount user = new UserAccount();
        user.setId(userId);
        Patient newPatient = new Patient();
        newPatient.setUserAccount(user);
        patientRepository.save(newPatient);
    }
}
