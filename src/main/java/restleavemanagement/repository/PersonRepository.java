package restleavemanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restleavemanagement.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {

    Person findByEmail(String email);

}
