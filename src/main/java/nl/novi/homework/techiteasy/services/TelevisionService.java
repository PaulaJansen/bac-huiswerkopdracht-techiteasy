package nl.novi.homework.techiteasy.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.homework.techiteasy.Dtos.*;
import nl.novi.homework.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.homework.techiteasy.mappers.TelevisionMapper;
import nl.novi.homework.techiteasy.models.CIModule;
import nl.novi.homework.techiteasy.models.RemoteController;
import nl.novi.homework.techiteasy.models.Television;
import nl.novi.homework.techiteasy.models.WallBracket;
import nl.novi.homework.techiteasy.repositories.CIModuleRepository;
import nl.novi.homework.techiteasy.repositories.RemoteControllerRepository;
import nl.novi.homework.techiteasy.repositories.TelevisionRepository;
import nl.novi.homework.techiteasy.repositories.WallBracketRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;
    private final CIModuleRepository ciModuleRepository;
    private final WallBracketRespository wallBracketRespository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository, CIModuleRepository ciModuleRepository, WallBracketRespository wallBracketRespository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.ciModuleRepository = ciModuleRepository;
        this.wallBracketRespository = wallBracketRespository;
    }

    public TelevisionDto getTelevision(long id) {
        return TelevisionMapper.toDto(televisionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Television " + id + " not found")));
    }

    public List<TelevisionDto> getAllTelevisions() {
        return TelevisionMapper.toDtoList(televisionRepository.findAll());
    }

    public TelevisionSalesDto getSalesInformation(long id) {
        return TelevisionMapper.toSalesDto(televisionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Television " + id + " not found")));
    }

    public List<TelevisionSalesDto> getSalesInformationAllTelevisions() {
        return TelevisionMapper.toSalesDtoList(televisionRepository.findAll());
    }

    public TelevisionDto createTelevision(TelevisionInputDto televisionInputDto) {
        Television television = TelevisionMapper.toEntity(televisionInputDto);
        televisionRepository.save(television);
        return TelevisionMapper.toDto(television);
    }

    public TelevisionDto updateTelevision(long id, TelevisionInputDto televisionInputDto) {
        Television existingTelevision = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television " + id + " not found"));

        existingTelevision.setName(televisionInputDto.name);
        existingTelevision.setPrice(televisionInputDto.price);
        existingTelevision.setAvailableSize(televisionInputDto.availableSize);
        existingTelevision.setSold(0);

        Television updatedTelevision = televisionRepository.save(existingTelevision);
        return TelevisionMapper.toDto(updatedTelevision);
    }

    public String deleteTelevision(long id) {
        Television existingTelevision = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television " + id + " not found"));
        televisionRepository.delete(existingTelevision);
        return ("Television with id " + id + " has been deleted");
    }

    public TelevisionDto assignRemoteControllerToTelevision(long televisionId, long remoteControllerId) {
        Television television = televisionRepository.findById(televisionId).orElseThrow(() -> new RecordNotFoundException("Television " + televisionId + " not found"));
        RemoteController remoteController = remoteControllerRepository.findById(remoteControllerId).orElseThrow(() -> new RecordNotFoundException("Remote controller " + remoteControllerId + " not found"));
        television.setRemoteController(remoteController);
        televisionRepository.save(television);
        return TelevisionMapper.toDto(television);
    }

    public TelevisionDto assignCIModuleToTelevision(long televisionId, long ciModuleId) {
        Television television = televisionRepository.findById(televisionId).orElseThrow(() -> new RecordNotFoundException("Television " + televisionId + " not found"));
        CIModule ciModule = ciModuleRepository.findById(ciModuleId).orElseThrow(() -> new RecordNotFoundException("Remote controller " + ciModuleId + " not found"));
        television.setCiModule(ciModule);
        televisionRepository.save(television);
        return TelevisionMapper.toDto(television);
    }

    public TelevisionDto assignWallBracketToTelevision(long televisionId, long wallBracketId) {
        Television television = televisionRepository.findById(televisionId).orElseThrow(() -> new RecordNotFoundException("Television " + televisionId + " not found"));
        WallBracket wallBracket = wallBracketRespository.findById(wallBracketId).orElseThrow(() -> new RecordNotFoundException("Wall bracket " + wallBracketId + " not found"));
        television.getWallBrackets().add(wallBracket);
        televisionRepository.save(television);
        return TelevisionMapper.toDto(television);
    }

}
