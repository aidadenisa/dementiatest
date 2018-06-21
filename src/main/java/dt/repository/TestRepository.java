package dt.repository;

import dt.model.Patient;
import dt.model.Test;
import dt.model.TestConfiguration;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test,Long> {

    Test findByPatientAndTestConfiguration(Patient patient, TestConfiguration testConfiguration);

}
