package restleavemanagement.repository;

import org.springframework.data.repository.CrudRepository;
import restleavemanagement.model.ForButtons;

import java.util.List;

public interface ForButtonsRepository extends CrudRepository<ForButtons, Long> {

    @Override
    List<ForButtons> findAll();
}
