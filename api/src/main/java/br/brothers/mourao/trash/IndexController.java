package br.brothers.mourao.trash;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class IndexController {
    
    @RequestMapping("/")
    public String index() {
        return "xy-inc - Backend as a Service!";
    }
    
}