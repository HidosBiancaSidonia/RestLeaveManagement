package restleavemanagement.repository;

import org.springframework.data.repository.CrudRepository;
import restleavemanagement.model.ForButtons;

import javax.transaction.Transactional;
import java.util.List;

public interface ForButtonsRepository extends CrudRepository<ForButtons, Long> {

    @Override
    List<ForButtons> findAll();

    @Override
    @Transactional
    void deleteById(Long aLong);
}
