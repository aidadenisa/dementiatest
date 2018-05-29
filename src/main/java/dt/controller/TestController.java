package dt.controller;

import dt.model.Test;
import dt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/tests")
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    @RequestMapping("/tests/{testId}")
    public Test getTest(@PathVariable int testId) {
        return testService.getTest(testId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tests")
    public void addTest(@RequestBody Test test) {
        testService.addTest(test);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tests")
    public void updateTest(@RequestBody Test test) {
        testService.updateTest(test);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tests/{testId}")
    public void deleteTest(@PathVariable int testId) {
        testService.deleteTest(testId);
    }

    @RequestMapping( value = "/patient/{patientId}/testConfig/{testConfigId}")
    public Test deleteTest(@PathVariable int patientId, @PathVariable int testConfigId) {
        return testService.getTestOfPatientWithConfig(patientId,testConfigId);
    }

}
