package adminpage.service.impl;

import adminpage.DTO.ServiceDTO;
import adminpage.entity.ServiceEntity;
import adminpage.repository.ServiceRepository;
import adminpage.service.WsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WsServiceImpl implements WsService {

    private final ServiceRepository serviceRepository;

    public WsServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceDTO> getServiceList() {
        List<ServiceEntity> serviceEntities = serviceRepository.findByStatusEquals(1);

        return serviceEntities.stream()
                .map(ServiceDTO::new)
                .collect(Collectors.toList());
    }
}
