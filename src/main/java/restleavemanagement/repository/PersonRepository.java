package restleavemanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restleavemanagement.model.Person;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {

    Person findByEmail(String email);

    @Override
    List<Person> findAll();

    Person findById(Integer id);

}
