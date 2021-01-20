package restleavemanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restleavemanagement.model.Role;
import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Override
    List<Role> findAll();

    Role findById(Integer id);
}
