package dt.repository;

import dt.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor,Integer> {

    Doctor findByUserAccountId(Integer userId);
}
