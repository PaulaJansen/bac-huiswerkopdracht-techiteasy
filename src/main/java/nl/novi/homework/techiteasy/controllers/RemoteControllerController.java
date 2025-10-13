package nl.novi.homework.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.homework.techiteasy.Dtos.RemoteControllerDto;
import nl.novi.homework.techiteasy.Dtos.RemoteControllerInputDto;
import nl.novi.homework.techiteasy.services.RemoteControllerService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remotecontrollers")

public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @PostMapping
    public ResponseEntity<RemoteControllerDto> createRemoteController(@Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.createRemoteController(remoteControllerInputDto);
        return new ResponseEntity<>(remoteControllerDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteController(@PathVariable long id) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.getRemoteController(id);
        return ResponseEntity.ok(remoteControllerDto);
    }

    @GetMapping
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers() {
        List<RemoteControllerDto> remoteControllers = remoteControllerService.getAllRemoteControllers();
        return ResponseEntity.ok(remoteControllers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemoteController(@Valid @PathVariable long id, @RequestBody RemoteControllerInputDto remoteControllerInputDto ) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.updateRemoteController(id, remoteControllerInputDto);
        return ResponseEntity.ok(remoteControllerDto);
    }

    @DeleteMapping("/{id")
    public ResponseEntity<String> deleteRemoteController(@PathVariable long id) {
        String message = remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.ok(message);
    }
}
