package dt.repository;

import dt.model.TestConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface TestConfigurationRepository extends CrudRepository<TestConfiguration,Long>{
}
