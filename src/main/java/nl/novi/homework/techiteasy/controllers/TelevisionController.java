package nl.novi.homework.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.homework.techiteasy.Dtos.TelevisionDto;
import nl.novi.homework.techiteasy.Dtos.TelevisionInputDto;
import nl.novi.homework.techiteasy.Dtos.TelevisionSalesDto;
import nl.novi.homework.techiteasy.services.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService){
        this.televisionService = televisionService;
    }

    @PostMapping
    public ResponseEntity<TelevisionDto> createTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto){
        TelevisionDto televisionDto = televisionService.createTelevision(televisionInputDto);
        return new ResponseEntity<>(televisionDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        List<TelevisionDto> televisions = televisionService.getAllTelevisions();
        return ResponseEntity.ok(televisions);
    }

   @GetMapping("/{id}")
   public ResponseEntity<TelevisionDto> getTelevisionById(@PathVariable long id) {
        TelevisionDto televisionDto = televisionService.getTelevision(id);
            return ResponseEntity.ok(televisionDto);

    }

    // GetMapping voor alleen de sales informatie voor alle televisies en voor 1 televisie
    @GetMapping
    public ResponseEntity<List<TelevisionSalesDto>> getSalesInformationAllTelevisions() {
        List<TelevisionSalesDto> televisionsSales = televisionService.getSalesInformationAllTelevisions();
        return ResponseEntity.ok(televisionsSales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionSalesDto> getSalesInformation (@PathVariable long id) {
        TelevisionSalesDto televisionSalesDto = televisionService.getSalesInformation(id);
        return ResponseEntity.ok(televisionSalesDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@Valid @PathVariable long id, @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto televisionDto = televisionService.updateTelevision(id, televisionInputDto);
        return ResponseEntity.ok(televisionDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable long id) {
        String message = televisionService.deleteTelevision(id);
        return ResponseEntity.ok(message);
    }
}

