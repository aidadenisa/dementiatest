package dt.controller;

import dt.model.TestConfiguration;
import dt.service.TestConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestConfigurationController {

    @Autowired
    private TestConfigurationService testConfigurationService;

    @RequestMapping("/testconfigs")
    public List<TestConfiguration> getAllTestConfigurations() {
        return testConfigurationService.getAllTestConfigurations();
    }

    @RequestMapping("/testconfigs/{testConfigId}")
    public TestConfiguration getAllTestConfigurations(@PathVariable int testConfigId) {
        return testConfigurationService.getTestConfiguration(testConfigId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/testconfigs")
    public void addTestConfiguration(@RequestBody TestConfiguration testConfiguration) {
        testConfigurationService.addTestConfiguration(testConfiguration);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/testconfigs")
    public void updateTestConfiguration(@RequestBody TestConfiguration testConfiguration) {
        testConfigurationService.updateTestConfiguration(testConfiguration);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/testconfigs/{testConfigId}")
    public void deleteTestConfiguration(@PathVariable int testConfigId) {
        testConfigurationService.deleteTestConfiguration(testConfigId);
    }


}
