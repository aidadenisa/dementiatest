package dt.repository;

import dt.model.Answer;
import dt.model.Patient;
import dt.model.Test;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    List<Answer> findAllByPatientAndTest(Patient patient, Test test);
}
