package nl.novi.homework.techiteasy.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.homework.techiteasy.Dtos.CIModuleDto;
import nl.novi.homework.techiteasy.Dtos.CIModuleInputDto;
import nl.novi.homework.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.homework.techiteasy.mappers.CIModuleMapper;
import nl.novi.homework.techiteasy.models.CIModule;
import nl.novi.homework.techiteasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<CIModuleDto> getAllCIModules() {
        return CIModuleMapper.toDtoList(ciModuleRepository.findAll());
    }

    public CIModuleDto getCIModule(long id) {
        return CIModuleMapper.toDto(ciModuleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CI module " + id + " not found")));
    }

    public CIModuleDto createCIModule(CIModuleInputDto ciModuleInputDto) {
        CIModule ciModule = CIModuleMapper.toEntity(ciModuleInputDto);
        ciModuleRepository.save(ciModule);
        return CIModuleMapper.toDto(ciModule);
    }

    public CIModuleDto updateCIModule(long id, CIModuleInputDto ciModuleInputDto) {
        CIModule existingCIModule = ciModuleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("CI module " + id + " not found"));

        existingCIModule.setPrice(ciModuleInputDto.price);

        CIModule updatedCIModule = ciModuleRepository.save(existingCIModule);
        return CIModuleMapper.toDto(updatedCIModule);
    }

    public String deleteCIModule(long id) {
        CIModule existingCIModule = ciModuleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("CI module " + id + " not found"));
        ciModuleRepository.delete(existingCIModule);
        return ("CI module with id " + id + " has been deleted");
    }
}
