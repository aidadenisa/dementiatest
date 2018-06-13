package dt.service;

import dt.model.Doctor;
import dt.model.Patient;
import dt.model.UserAccount;
import dt.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorService doctorService;

    public List<Patient> getAllPatients() {

        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;

    }

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getPatient(long patientId) {
        return patientRepository.findOne(patientId);
    }

    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void deletePatient(long patientConfigId) {
        patientRepository.delete(patientConfigId);
    }

    public List<Patient> getPatientsOfDoctor(long doctorId) {
        Doctor doctor = doctorService.getDoctor(doctorId);
        if(doctor != null) {
            return doctor.getPatients();
        } else {
            return null;
        }
    }

    public List<Patient> savePatientOfDoctor(long doctorId, Patient patient) {

        Doctor doctor = doctorService.getDoctor(doctorId);
        if(doctor != null) {
            doctor.getPatients().add(patient);
            doctorService.updateDoctor(doctor);
            return doctor.getPatients();
        } else {
            return null;
        }

    }

    public Patient getPatientAttachedToAccount(long userId) {
        return patientRepository.findByUserAccountId(userId);
    }

    public Patient addPatientWithAccount(long userId) {
//        UserAccount user = userService.getUser(userId);
        UserAccount user = new UserAccount();
        user.setId(userId);
        Patient newPatient = new Patient();
        newPatient.setUserAccount(user);
        return patientRepository.save(newPatient);
    }

    public Patient saveExistingPatientOfDoctor(long doctorId, long patientId) {

        Doctor doctor = doctorService.getDoctor(doctorId);

        Patient patient = patientRepository.findOne(patientId);

        if(patient != null &&  patient.getDoctors() != null && doctor != null) {
//            patient.getDoctors().add(doctor);
//            return patientRepository.save(patient);
            doctor.getPatients().add(patient);
            doctorService.updateDoctor(doctor);
            return patient;
        }
        return null;
    }
}
