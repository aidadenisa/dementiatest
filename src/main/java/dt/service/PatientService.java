package dt.service;

import dt.model.Patient;
import dt.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

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
}
