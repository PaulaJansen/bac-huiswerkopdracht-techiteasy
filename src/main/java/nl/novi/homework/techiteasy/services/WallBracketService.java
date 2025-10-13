package nl.novi.homework.techiteasy.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.homework.techiteasy.Dtos.WallBracketDto;
import nl.novi.homework.techiteasy.Dtos.WallBracketInputDto;
import nl.novi.homework.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.homework.techiteasy.mappers.WallBracketMapper;
import nl.novi.homework.techiteasy.models.WallBracket;
import nl.novi.homework.techiteasy.repositories.WallBracketRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WallBracketService {

    private final WallBracketRespository wallBracketRespository;

    public WallBracketService(WallBracketRespository wallBracketRespository) {
        this.wallBracketRespository = wallBracketRespository;
    }

    public List<WallBracketDto> getAllWallBrackets() {
        return WallBracketMapper.toDtoList(wallBracketRespository.findAll());
    }

    public WallBracketDto getWallBracket(long id) {
        return WallBracketMapper.toDto(wallBracketRespository.findById(id).orElseThrow(() -> new EntityNotFoundException("Wall bracket " + id + " not found")));
    }

    public WallBracketDto createWallBracket(WallBracketInputDto wallBracketInputDto) {
        WallBracket wallBracket = WallBracketMapper.toEntity(wallBracketInputDto);
        wallBracketRespository.save(wallBracket);
        return WallBracketMapper.toDto(wallBracket);
    }

    public WallBracketDto updateWallBracket(long id, WallBracketInputDto wallBracketInputDto) {
        WallBracket existingWallBracket = wallBracketRespository.findById(id).orElseThrow(() -> new RecordNotFoundException("Wall bracket " + id + " not found"));

        existingWallBracket.setPrice(wallBracketInputDto.price);
        existingWallBracket.setSize(wallBracketInputDto.size);

        WallBracket updatedWallBracket = wallBracketRespository.save(existingWallBracket);
        return WallBracketMapper.toDto(updatedWallBracket);
    }

    public String deleteWallBracket(long id) {
        WallBracket existingWallBracket = wallBracketRespository.findById(id).orElseThrow(() -> new RecordNotFoundException("Wall bracket " + id + " not found"));
        wallBracketRespository.delete(existingWallBracket);
        return ("Wall bracket with id " + id + " has been deleted");
    }
}
