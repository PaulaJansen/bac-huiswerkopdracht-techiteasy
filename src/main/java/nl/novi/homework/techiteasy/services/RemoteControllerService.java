package nl.novi.homework.techiteasy.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.homework.techiteasy.Dtos.RemoteControllerDto;
import nl.novi.homework.techiteasy.Dtos.RemoteControllerInputDto;
import nl.novi.homework.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.homework.techiteasy.mappers.RemoteControllerMapper;
import nl.novi.homework.techiteasy.models.RemoteController;
import nl.novi.homework.techiteasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public List<RemoteControllerDto> getAllRemoteControllers() {
        return RemoteControllerMapper.toDtoList(remoteControllerRepository.findAll());
    }

    public RemoteControllerDto getRemoteController(long id) {
        return RemoteControllerMapper.toDto(remoteControllerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Remote controller " + id + " not found")));
    }

    public RemoteControllerDto createRemoteController(RemoteControllerInputDto remoteControllerInputDto) {
        RemoteController remoteController = RemoteControllerMapper.toEntity(remoteControllerInputDto);
        remoteControllerRepository.save(remoteController);
        return RemoteControllerMapper.toDto(remoteController);
    }

    public RemoteControllerDto updateRemoteController(long id, RemoteControllerInputDto remoteControllerInputDto) {
        RemoteController existingRemoteController = remoteControllerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Remote controller " + id + " not found"));

        existingRemoteController.setPrice(remoteControllerInputDto.price);
        existingRemoteController.setCompatibleWith(remoteControllerInputDto.compatibleWith);

        RemoteController updatedRemoteController = remoteControllerRepository.save(existingRemoteController);
        return RemoteControllerMapper.toDto(updatedRemoteController);
    }

    public String deleteRemoteController(long id) {
        RemoteController existingRemoteController = remoteControllerRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Remote controller " + id + " not found"));
        remoteControllerRepository.delete(existingRemoteController);
        return("Remote controller with id " + id + " has been deleted");
    }

}
