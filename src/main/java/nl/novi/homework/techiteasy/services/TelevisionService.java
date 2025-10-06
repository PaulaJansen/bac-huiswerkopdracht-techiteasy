package nl.novi.homework.techiteasy.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.homework.techiteasy.Dtos.TelevisionDto;
import nl.novi.homework.techiteasy.Dtos.TelevisionInputDto;
import nl.novi.homework.techiteasy.Dtos.TelevisionSalesDto;
import nl.novi.homework.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.homework.techiteasy.mappers.TelevisionMapper;
import nl.novi.homework.techiteasy.models.Television;
import nl.novi.homework.techiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
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

    public TelevisionDto deleteTelevision(long id) {
        Television existingTelevision = televisionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Television " + id + " not found"));

        TelevisionDto deletedTelevision = TelevisionMapper.toDto(existingTelevision);
        televisionRepository.deleteById(id);
        return deletedTelevision;
    }
}
