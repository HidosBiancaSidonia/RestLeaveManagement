package restleavemanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restleavemanagement.model.LeaveRequest;
import restleavemanagement.model.Person;

@Repository
public interface LeaveRequestRepository extends CrudRepository<LeaveRequest,Long> {
}
