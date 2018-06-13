package dt.repository;

import dt.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor,Long> {

    Doctor findByUserAccountId(Long userId);
}
