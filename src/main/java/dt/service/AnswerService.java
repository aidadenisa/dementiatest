package dt.service;

import dt.model.*;
import dt.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TestService testService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private TestConfigurationService testConfigurationService;

    @Autowired
    private QuestionService questionService;

    public List<Answer> getAllAnswers() {

        List<Answer> answers = new ArrayList<>();
        answerRepository.findAll().forEach(answers::add);
        return answers;

    }

    public void addAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public Answer getAnswer(int answerId) {
        return answerRepository.findOne(answerId);
    }

    public void updateAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public void deleteAnswer(int answerConfigId) {
        answerRepository.delete(answerConfigId);
    }

    public void addAnswers(List<Answer> answers) {

        TestConfiguration testConfiguration = testConfigurationService.getTestConfiguration(answers.get(0).getTest().getTestConfiguration().getId());

        Test test = testService.getTest(answers.get(0).getTest().getId());

        Patient patient = patientService.getPatient(answers.get(0).getPatient().getId());

//        for(int i = 0; i<answers.size(); i++ ) {
//
//            Answer answer = answers.get(i);
//
//            if(test != null) {
//                answer.setTest(test);
//            }
//
//            if(testConfiguration != null) {
//                test.setTestConfiguration(testConfiguration);
//                answer.setTest(test);
//            }
//
//            if(patient != null) {
//                answer.setPatient(patient);
//            }
//
//        }

        answerRepository.save(answers);
    }

    public List<Answer> saveAnswersToTest(int patientId, int testConfigId, List<Answer> answers) {

        TestConfiguration testConfiguration = testConfigurationService.getTestConfiguration(testConfigId);
        Patient patient = patientService.getPatient(patientId);

        Test existingTest = testService.getTestByPatientAndConfiguration(testConfiguration, patient);
        Test test = new Test();

        if(existingTest == null) {
            test.setTestConfiguration(testConfiguration);
            test.setPatient(patient);
            testService.addTest(test);
        }

        for (int i = 0; i < answers.size(); i++) {

            Answer answer = answers.get(i);

            if (existingTest == null) {
                answer.setTest(test);
            } else {
                answer.setTest(existingTest);
            }

            if (patient != null) {
                answer.setPatient(patient);
            }

            Question question = questionService.getQuestion(answer.getQuestion().getId());
            if(question != null) {
                answer.setQuestion(question);
            }

        }

        return (List<Answer>)answerRepository.save(answers);
    }
}
