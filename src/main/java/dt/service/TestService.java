package dt.service;

import dt.model.Patient;
import dt.model.Test;
import dt.model.TestConfiguration;
import dt.repository.TestConfigurationRepository;
import dt.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestConfigurationService testConfigurationService;

    @Autowired
    private PatientService patientService;

    public List<Test> getAllTests() {

        List<Test> tests = new ArrayList<>();
        testRepository.findAll().forEach(tests::add);
        return tests;

    }

    public void addTest(Test test) {
        Test existingTest = testRepository.findByPatientAndTestConfiguration(test.getPatient(),test.getTestConfiguration());
        if(existingTest == null) {
            testRepository.save(test);
        }
    }

    public Test getTest(int testId) {
        return testRepository.findOne(testId);
    }

    public Test updateTest(Test test) {
         return testRepository.save(test);
    }

    public void deleteTest(int testConfigId) {
        testRepository.delete(testConfigId);
    }

    public Test getTestByPatientAndConfiguration(TestConfiguration testConfiguration, Patient patient) {
        return testRepository.findByPatientAndTestConfiguration(patient,testConfiguration);
    }

    public Test getTestOfPatientWithConfig(int patientId, int testConfigId) {

        TestConfiguration testConfiguration = testConfigurationService.getTestConfiguration(testConfigId);
        Patient patient = patientService.getPatient(patientId);

        return testRepository.findByPatientAndTestConfiguration(patient, testConfiguration);
    }
}
