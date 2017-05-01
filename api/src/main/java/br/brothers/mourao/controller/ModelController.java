package br.brothers.mourao.controller;

import br.brothers.mourao.exception.CannotGeneratedException;
import br.brothers.mourao.exception.ModelAlreadyExistsException;
import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.persistence.entity.Model;
import br.brothers.mourao.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Model model) {
        try {
            Model newModel = modelService.create(model);
            return ResponseEntity.ok().body(newModel);
        } catch (ModelAlreadyExistsException | TypeNotExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (CannotGeneratedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
