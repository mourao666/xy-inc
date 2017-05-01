package br.brothers.mourao.controller;

import br.brothers.mourao.exception.CannotAssignException;
import br.brothers.mourao.exception.CannotGeneratedException;
import br.brothers.mourao.exception.CannotInstantiateException;
import br.brothers.mourao.exception.ModelNotExistsException;
import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.service.DynamicRestApiService;
import br.brothers.mourao.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(Constants.DYNAMIC_URL)
public class DynamicRestApiController {

    @Autowired
    private DynamicRestApiService dynamicRestApiService;

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
    public ResponseEntity create(@PathVariable String model, @RequestBody Map<String, Object> attributes) {
        try {
            dynamicRestApiService.createRecord(model, attributes);
            return ResponseEntity.ok().build();
        } catch (ModelNotExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (TypeNotExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (CannotGeneratedException | CannotInstantiateException | CannotAssignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
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
