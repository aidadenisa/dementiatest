package dt.controller;

import dt.model.Answer;
import dt.model.Question;
import dt.model.Test;
import dt.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping("/answers")
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @RequestMapping("/answers/{answerId}")
    public Answer getAllAnswers(@PathVariable int answerId) {
        return answerService.getAnswer(answerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/answers")
    public void addAnswer(@RequestBody List<Answer> answers) {
        answerService.addAnswers(answers);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/answers")
    public void updateAnswer(@RequestBody Answer answer) {
        answerService.updateAnswer(answer);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/answers/{answerId}")
    public void deleteAnswer(@PathVariable int answerId) {
        answerService.deleteAnswer(answerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/patient/{patientId}/testConfig/{testConfigId}/answers")
    public Test saveTestResults(@PathVariable int patientId, @PathVariable int testConfigId, @RequestBody List<Answer> answers){
        return answerService.saveAnswersToTest(patientId, testConfigId, answers);
    }

    @RequestMapping( "/patient/{patientId}/test/{testId}/answers")
    public List<Answer> saveTestResults(@PathVariable int patientId, @PathVariable int testId){
        return answerService.getAnswersOfPatientTest(patientId, testId);
    }

}
