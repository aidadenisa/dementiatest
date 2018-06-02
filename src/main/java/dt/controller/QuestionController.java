package dt.controller;

import dt.model.ConnectPoint;
import dt.model.Question;
import dt.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @RequestMapping("/questions/{questionId}")
    public Question getQuestion(@PathVariable int questionId) {
        return questionService.getQuestion(questionId);
    }

    @RequestMapping(method = RequestMethod.POST ,value = "/questions")
    public void createQuestion(@RequestBody List<Question> questions) {
        questionService.updateQuestions(questions);
    }

    @RequestMapping(method = RequestMethod.PUT ,value = "/questions")
    public void updateQuestion(@RequestBody Question question) {
        questionService.updateQuestion(question);
    }

    @RequestMapping(method = RequestMethod.DELETE ,value = "/questions/{questionId}")
    public void deleteQuestion(@PathVariable int questionId) {
        questionService.deleteQuestion(questionId);
    }

    @RequestMapping("testconfigs/{testConfigId}/questions")
    public List<Question> getAllQuestionsFromATestConfig(@PathVariable int testConfigId) {
        return questionService.getAllQuestionsFromTestConfig(testConfigId);
    }

    @RequestMapping(method = RequestMethod.POST ,value = "testconfigs/{testConfigId}/questions")
    public void saveQuestionsFromATestConfig(@PathVariable int testConfigId, @RequestBody List<Question> questions) {
        questionService.saveQuestionsToTestConfiguration(testConfigId, questions);
    }

    @RequestMapping("testconfigs/{testConfigId}/questions/{questionId}")
    public Question getQuestionFromTestConfig(@PathVariable int testConfigId, @PathVariable int questionId) {
        return questionService.getQuestion(questionId);
    }

    @RequestMapping(method = RequestMethod.POST ,value = "questions/{questionId}/points")
    public void saveQuestionPoints(@PathVariable int questionId, @RequestBody List<ConnectPoint> points) {
        questionService.savePointsToQuestion(questionId, points);
    }

}
