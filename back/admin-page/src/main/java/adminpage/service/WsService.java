package adminpage.service;

import adminpage.DTO.ServiceDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WsService {

    List<ServiceDTO> getServiceList();
}
