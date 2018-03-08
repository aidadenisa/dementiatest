package dt.service;

import dt.model.Question;
import dt.model.Test;
import dt.model.TestConfiguration;
import dt.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestConfigurationService testConfigurationService;

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questionRepository.findAll().forEach(questions::add);
        return questions;
    }

    public void saveQuestion(Question question, int testConfigurationId) {

        questionRepository.save(question);
    }

    public Question getQuestion(int questionId) {
        return questionRepository.findOne(questionId);
    }

    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }

    public void deleteQuestion(int questionId) {
        questionRepository.delete(questionId);
    }

    public List<Question> getAllQuestionsFromTestConfig(int testConfigId) {
        TestConfiguration testConfiguration = testConfigurationService.getTestConfiguration(testConfigId);
        if(testConfiguration != null) {
            return testConfiguration.getQuestions();
        } else {
            return new ArrayList<>();
        }
    }

    public void saveQuestionToTestConfiguration(int testConfigId, List<Question> questions) {
        TestConfiguration testConfiguration = testConfigurationService.getTestConfiguration(testConfigId);
        if(testConfiguration != null) {
            questions.forEach(testConfiguration.getQuestions()::add);
            testConfigurationService.updateTestConfiguration(testConfiguration);
        } else {

        }
    }
}
