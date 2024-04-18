package adminpage.controller;

import adminpage.DTO.ClientDTO;
import adminpage.service.WscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private WscService wscService; //webservice client service

    @GetMapping()
    public List<ClientDTO> getClients() {
        return wscService.getClientList();
    }
}
