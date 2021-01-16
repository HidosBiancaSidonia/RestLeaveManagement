package restleavemanagement.service;

import org.springframework.stereotype.Service;
import restleavemanagement.dto.ForButtonsDto;
import restleavemanagement.model.ForButtons;
import restleavemanagement.repository.ForButtonsRepository;

@Service
public class ForButtonsServiceImpl implements ForButtonsService{
    private ForButtonsRepository forButtonsRepository;

    public ForButtonsServiceImpl(ForButtonsRepository forButtonsRepository) {
        this.forButtonsRepository = forButtonsRepository;
    }

    @Override
    public ForButtons save(ForButtonsDto forButtonsDto) throws Exception {
        ForButtons forButtons = new ForButtons(forButtonsDto.getEmployee_id(),forButtonsDto.getBoss_id(),
                false);
        return forButtonsRepository.save(forButtons);
    }
}
