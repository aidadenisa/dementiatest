package dt.service;

import dt.model.TestConfiguration;
import dt.repository.TestConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TestConfigurationService {

    private static final int PERSONAL_TEST_ID = 100;

    @Autowired
    private TestConfigurationRepository testConfigurationRepository;

    public List<TestConfiguration> getAllTestConfigurations() {

        List<TestConfiguration> testconfigs = new ArrayList<>();
        testConfigurationRepository.findAll().forEach(testconfigs::add);
        for( int i = 0; i< testconfigs.size(); i++) {
            if(testconfigs.get(i).getQuestions().get(0).getIndex()>0) {
                Collections.sort(testconfigs.get(i).getQuestions());
            }
        }
        return testconfigs;

    }

    public void addTestConfiguration(TestConfiguration testConfiguration) {
        testConfigurationRepository.save(testConfiguration);
    }

    public TestConfiguration getTestConfiguration(long testConfigurationId) {
        return testConfigurationRepository.findOne(testConfigurationId);
    }

    public void updateTestConfiguration(TestConfiguration testConfiguration) {
        testConfigurationRepository.save(testConfiguration);
    }

    public void deleteTestConfiguration(long testConfigId) {
        testConfigurationRepository.delete(testConfigId);
    }
}
