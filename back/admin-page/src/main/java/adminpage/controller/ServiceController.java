package adminpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

import adminpage.DTO.ServiceDTO;
import adminpage.service.WsService;

@RestController
@CrossOrigin
@RequestMapping("services")
public class ServiceController {

    @Autowired
    private WsService wsService; //webservice service

    @GetMapping()
    public List<ServiceDTO> getServices() {
        return wsService.getServiceList();
    }

}
