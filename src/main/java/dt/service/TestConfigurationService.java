package dt.service;

import dt.model.Question;
import dt.model.TestConfiguration;
import dt.repository.TestConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestConfigurationService {

    @Autowired
    private TestConfigurationRepository testConfigurationRepository;

    public List<TestConfiguration> getAllTestConfigurations() {

        List<TestConfiguration> testconfigs = new ArrayList<>();
        testConfigurationRepository.findAll().forEach(testconfigs::add);
        return testconfigs;

    }

    public void addTestConfiguration(TestConfiguration testConfiguration) {
        testConfigurationRepository.save(testConfiguration);
    }

    public TestConfiguration getTestConfiguration(int testConfigurationId) {
        return testConfigurationRepository.findOne(testConfigurationId);
    }

    public void updateTestConfiguration(TestConfiguration testConfiguration) {
        testConfigurationRepository.save(testConfiguration);
    }

    public void deleteTestConfiguration(int testConfigId) {
        testConfigurationRepository.delete(testConfigId);
    }
}
