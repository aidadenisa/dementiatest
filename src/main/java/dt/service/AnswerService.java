package dt.service;

import dt.model.Answer;
import dt.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

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
    
}
