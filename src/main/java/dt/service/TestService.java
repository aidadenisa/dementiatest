package dt.service;

import dt.model.Test;
import dt.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<Test> getAllTests() {

        List<Test> tests = new ArrayList<>();
        testRepository.findAll().forEach(tests::add);
        return tests;

    }

    public void addTest(Test test) {
        testRepository.save(test);
    }

    public Test getTest(int testId) {
        return testRepository.findOne(testId);
    }

    public void updateTest(Test test) {
        testRepository.save(test);
    }

    public void deleteTest(int testConfigId) {
        testRepository.delete(testConfigId);
    }
    
}
