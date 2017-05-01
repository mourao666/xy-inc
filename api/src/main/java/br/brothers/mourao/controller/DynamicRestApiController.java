package br.brothers.mourao.controller;

import br.brothers.mourao.utils.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.DYNAMIC_URL)
public class DynamicRestApiController {

    //    GET /xxx - Lista todos os elementos do Modelo XXX
    @RequestMapping(value = "/{model}", method = RequestMethod.GET)
    public ResponseEntity getAll(String model) {
        return ResponseEntity.ok().build();
    }

    //    GET /xxx/{id} - Busca um registro do modelo XXX por id
    @RequestMapping(value = "/{model}/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(String model, String id) {
        return ResponseEntity.ok().build();
    }

    //    POST /xxx - Cria um novo registro do modelo XXX
    @RequestMapping(value = "/{model}", method = RequestMethod.POST)
    public ResponseEntity create(String model) {
        return ResponseEntity.ok().build();
    }

    //    PUT /xxx/{id} - Edita um registro do modelo XXX
    @RequestMapping(value = "/{model}/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateById(String model, String id) {
        return ResponseEntity.ok().build();
    }

    //    DELETE /xxx/{id} - Deleta um registo do modelo XXX
    @RequestMapping(value = "/{model}/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(String model, String id) {
        return ResponseEntity.ok().build();
    }

}
