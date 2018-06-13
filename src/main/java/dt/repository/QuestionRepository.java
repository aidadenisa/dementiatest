package dt.repository;

import dt.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Long> {

//    public List<Question> findByTestConfigurationId(int TestConfigurationId);
}
