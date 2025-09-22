package nl.novi.homework.techiteasy.controller;

import nl.novi.homework.techiteasy.exception.NameTooLongException;
import nl.novi.homework.techiteasy.exception.RecordNotFoundException;
import nl.novi.homework.techiteasy.model.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    // database for now
    Map<Integer, Television> televisionMap = new HashMap<Integer, Television>();

    @PostMapping
    public ResponseEntity<Television> createTelevision(@RequestBody Television television){
        if(television.getName().length() <= 20) {
            this.televisionMap.put(television.getId(), television);
            return new ResponseEntity<>(television, HttpStatus.CREATED);
        } else {
            throw new NameTooLongException("Name is too long!");
        }
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Television>> getAllTelevisions() {
        return ResponseEntity.ok(this.televisionMap);
    }

    @GetMapping("/{id}")
   public ResponseEntity<Television> getTelevisionById(@PathVariable int id) {
        if (this.televisionMap.containsKey(id)) {
            return ResponseEntity.ok(this.televisionMap.get(id));
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable int id, @RequestBody Television updatedTelevision) {
        if (this.televisionMap.containsKey(id)) {
            updatedTelevision.setId(id);
            this.televisionMap.put(id, updatedTelevision);
            return ResponseEntity.ok(updatedTelevision);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable int id) {
        if (this.televisionMap.containsKey(id)) {
            this.televisionMap.remove(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

}
