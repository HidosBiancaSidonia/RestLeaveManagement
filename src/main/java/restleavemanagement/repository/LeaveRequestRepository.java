package restleavemanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restleavemanagement.model.LeaveRequest;
import restleavemanagement.model.Person;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends CrudRepository<LeaveRequest,Long> {

    LeaveRequest findByPerson(Person person);

    @Override
    List<LeaveRequest> findAll();

    @Override
    @Transactional
    void deleteById(Long aLong);

}
