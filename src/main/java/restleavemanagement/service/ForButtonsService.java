package restleavemanagement.service;

import restleavemanagement.dto.ForButtonsDto;
import restleavemanagement.model.ForButtons;

public interface ForButtonsService {
    ForButtons save(ForButtonsDto forButtonsDto) throws Exception;

    void delete(Long id);
}
