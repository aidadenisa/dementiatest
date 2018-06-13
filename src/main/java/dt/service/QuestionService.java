package dt.service;

import dt.model.ConnectPoint;
import dt.model.Question;
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

    @Autowired
    private ConnectPointsService connectPointsService;

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questionRepository.findAll().forEach(questions::add);
        return questions;
    }

    public void saveQuestion(Question question, long testConfigurationId) {

        questionRepository.save(question);
    }

    public Question getQuestion(long questionId) {
        return questionRepository.findOne(questionId);
    }

    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }

    public void deleteQuestion(long questionId) {
        questionRepository.delete(questionId);
    }

    public List<Question> getAllQuestionsFromTestConfig(long testConfigId) {
        TestConfiguration testConfiguration = testConfigurationService.getTestConfiguration(testConfigId);
        if(testConfiguration != null) {
            return testConfiguration.getQuestions();
        } else {
            return new ArrayList<>();
        }
    }

    public void saveQuestionsToTestConfiguration(long testConfigId, List<Question> questions) {
        TestConfiguration testConfiguration = testConfigurationService.getTestConfiguration(testConfigId);
        if(testConfiguration != null) {
            questions.forEach(testConfiguration.getQuestions()::add);
            testConfigurationService.updateTestConfiguration(testConfiguration);
        } else {

        }
    }

    public void updateQuestions(List<Question> questions) {

//        for( long i = 0; i < questions.size(); i++ ) {
//            questionRepository.save(questions.get(i));
//        }

        questionRepository.save(questions);
    }

    public void savePointsToQuestion(long questionId, List<ConnectPoint> points) {
        Question question = questionRepository.findOne(questionId);

        for (ConnectPoint point : points) {
            point.setQuestion(question);
        }

        connectPointsService.savePoints(points);

    }
}
