package br.brothers.mourao.controller;

import br.brothers.mourao.exception.CannotGeneratedException;
import br.brothers.mourao.exception.InvalidTypeAttribute;
import br.brothers.mourao.service.RecordModelService;
import br.brothers.mourao.exception.ModelNotExistsException;
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
@RequestMapping("/api")
public class DynamicRestApiController {

    @Autowired
    private RecordModelService recordModelService;

    @RequestMapping(value = "/{model}", method = RequestMethod.GET)
    public ResponseEntity getAll(@PathVariable String model) {
        try {
            return ResponseEntity.ok(recordModelService.findAll(model));
        } catch (ModelNotExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CannotGeneratedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{model}/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String model, @PathVariable String id) {
        try {
            return ResponseEntity.ok(recordModelService.findById(model, id));
        } catch (ModelNotExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CannotGeneratedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{model}", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable String model, @RequestBody Map<String, Object> attributes) {
        try {
            return ResponseEntity.ok(recordModelService.create(model, attributes));
        } catch (ModelNotExistsException | InvalidTypeAttribute e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{model}/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateById(@PathVariable String model, @PathVariable String id, @RequestBody Map<String, Object> attributes) {
        try {
            return ResponseEntity.ok(recordModelService.update(model, id, attributes));
        } catch (ModelNotExistsException | InvalidTypeAttribute e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{model}/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable String model, @PathVariable String id) {
        try {
            return ResponseEntity.ok(recordModelService.deleteById(model, id));
        } catch (ModelNotExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CannotGeneratedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
