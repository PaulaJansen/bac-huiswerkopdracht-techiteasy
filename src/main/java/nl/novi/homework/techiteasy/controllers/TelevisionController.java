package nl.novi.homework.techiteasy.controllers;

import nl.novi.homework.techiteasy.exceptions.NameTooLongException;
import nl.novi.homework.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.homework.techiteasy.models.Television;
import nl.novi.homework.techiteasy.repositories.TelevisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionRepository televisionRepository;

    public TelevisionController(TelevisionRepository televisionRepository){
        this.televisionRepository = televisionRepository;
    }

    @PostMapping
    public ResponseEntity<Television> createTelevision(@RequestBody Television television){
        if(television.getName().length() <= 20) {
            Television savedTelevision = televisionRepository.save(television);
            return new ResponseEntity<>(savedTelevision, HttpStatus.CREATED);
        } else {
            throw new NameTooLongException("Name is too long!");
        }
    }

    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevisions() {
        List<Television> televisions = televisionRepository.findAll();
        return ResponseEntity.ok(televisions);
    }

    @GetMapping("/{id}")
   public ResponseEntity<Television> getTelevisionById(@PathVariable long id) {
        Optional<Television> television = televisionRepository.findById(id);
        if (television.isPresent()) {
            return ResponseEntity.ok(television.get());
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable long id, @RequestBody Television updatedTelevision) {
        Optional<Television> existingTelevision = televisionRepository.findById(id);
        if (existingTelevision.isPresent()) {
            Television television = existingTelevision.get();
            television.setName(updatedTelevision.getName());
            // eventuele andere properties die aangepast moeten worden
            return ResponseEntity.ok(televisionRepository.save(television));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable long id) {
        if (televisionRepository.existsById(id)) {
            televisionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

}
