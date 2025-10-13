package nl.novi.homework.techiteasy.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import nl.novi.homework.techiteasy.Dtos.CIModuleDto;
import nl.novi.homework.techiteasy.Dtos.CIModuleInputDto;
import nl.novi.homework.techiteasy.services.CIModuleService;
import nl.novi.homework.techiteasy.services.RemoteControllerService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cimodules")

public class CIModuleController {

    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @PostMapping
    public ResponseEntity<CIModuleDto> createCIModule(@Valid @RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto ciModuleDto = ciModuleService.createCIModule(ciModuleInputDto);
        return new ResponseEntity<>(ciModuleDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        List<CIModuleDto> ciModules = ciModuleService.getAllCIModules();
        return ResponseEntity.ok(ciModules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getCIModule(@PathVariable long id) {
        CIModuleDto ciModuleDto = ciModuleService.getCIModule(id);
        return ResponseEntity.ok(ciModuleDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@Valid @PathVariable long id, @RequestBody CIModuleInputDto ciModuleInputDto ) {
        CIModuleDto ciModuleDto = ciModuleService.updateCIModule(id, ciModuleInputDto);
        return ResponseEntity.ok(ciModuleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCIModule(@PathVariable long id) {
        String message = ciModuleService.deleteCIModule(id);
        return ResponseEntity.ok(message);
    }
}
