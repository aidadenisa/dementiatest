package dt.repository;

import dt.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount,Long> {
    UserAccount findByEmail(String email);
}
