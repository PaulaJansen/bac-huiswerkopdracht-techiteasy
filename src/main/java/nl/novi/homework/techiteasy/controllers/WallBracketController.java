package nl.novi.homework.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.homework.techiteasy.Dtos.WallBracketDto;
import nl.novi.homework.techiteasy.Dtos.WallBracketInputDto;
import nl.novi.homework.techiteasy.services.RemoteControllerService;
import nl.novi.homework.techiteasy.services.WallBracketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallbrackets")
public class WallBracketController {

    private final WallBracketService wallBracketService;

    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }

    @PostMapping
    public ResponseEntity<WallBracketDto> createWallBracket(@Valid @RequestBody WallBracketInputDto wallBracketInputDto) {
        WallBracketDto wallBracketDto = wallBracketService.createWallBracket(wallBracketInputDto);
        return new ResponseEntity<>(wallBracketDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets() {
        List<WallBracketDto> wallBrackets = wallBracketService.getAllWallBrackets();
        return ResponseEntity.ok(wallBrackets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable long id) {
        WallBracketDto wallBracketDto = wallBracketService.getWallBracket(id);
        return ResponseEntity.ok(wallBracketDto);
    }

    @PutMapping("/{id}")
    ResponseEntity<WallBracketDto> updateWallBracket(@Valid @PathVariable long id, @RequestBody WallBracketInputDto wallBracketInputDto) {
        WallBracketDto wallBracketDto = wallBracketService.updateWallBracket(id, wallBracketInputDto);
        return ResponseEntity.ok(wallBracketDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallBracket(@PathVariable long id) {
        String message = wallBracketService.deleteWallBracket(id);
        return ResponseEntity.ok(message);
    }
}
