package br.brothers.mourao.controller;

import br.brothers.mourao.controller.dto.ModelDTO;
import br.brothers.mourao.exception.CannotGeneratedException;
import br.brothers.mourao.exception.ModelAlreadyExistsException;
import br.brothers.mourao.exception.ModelNotExistsException;
import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return ResponseEntity.ok(modelService.findAll().stream().map(model -> new ModelDTO(model)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(new ModelDTO(modelService.findById(id)));
        } catch (ModelNotExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody ModelDTO modelDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ModelDTO(modelService.create(modelDTO.unwrap())));
        } catch (TypeNotExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ModelAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (CannotGeneratedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok(new ModelDTO(modelService.delete(id)));
        } catch (ModelNotExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
