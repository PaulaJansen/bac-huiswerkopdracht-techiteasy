package nl.novi.homework.techiteasy.mappers;

import nl.novi.homework.techiteasy.Dtos.WallBracketDto;
import nl.novi.homework.techiteasy.Dtos.WallBracketInputDto;
import nl.novi.homework.techiteasy.models.WallBracket;

import java.util.List;

public class WallBracketMapper {

    public static WallBracketDto toDto(WallBracket wallBracket) {
        WallBracketDto wallBracketDto = new WallBracketDto();
        wallBracketDto.id = wallBracket.getId();
        wallBracketDto.size = wallBracket.getSize();
        wallBracketDto.adjustable = wallBracket.isAdjustable();
        wallBracketDto.name = wallBracket.getName();
        wallBracketDto.price = wallBracket.getPrice();
        return wallBracketDto;
    }

    public static List<WallBracketDto> toDtoList(List<WallBracket> wallBrackets) {
        return wallBrackets.stream().map(WallBracketMapper::toDto).toList();
    }

    public static WallBracket toEntity(WallBracketInputDto wallBracketInputDto) {
        WallBracket wallBracket = new WallBracket();
        wallBracket.setSize(wallBracketInputDto.size);
        wallBracket.setAdjustable(wallBracketInputDto.adjustable);
        wallBracket.setName(wallBracketInputDto.name);
        wallBracket.setPrice(wallBracketInputDto.price);
        return wallBracket;
    }
}
