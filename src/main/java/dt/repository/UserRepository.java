package dt.repository;

import dt.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount,Integer> {
    UserAccount findByEmail(String email);
}
