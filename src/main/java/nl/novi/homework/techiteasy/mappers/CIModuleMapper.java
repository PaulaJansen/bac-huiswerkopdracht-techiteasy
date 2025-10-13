package nl.novi.homework.techiteasy.mappers;

import nl.novi.homework.techiteasy.Dtos.CIModuleDto;
import nl.novi.homework.techiteasy.Dtos.CIModuleInputDto;
import nl.novi.homework.techiteasy.models.CIModule;

import java.util.List;

public class CIModuleMapper {

    public static CIModuleDto toDto(CIModule ciModule) {
        CIModuleDto ciModuleDto = new CIModuleDto();
        ciModuleDto.id = ciModule.getId();
        ciModuleDto.name = ciModule.getName();
        ciModuleDto.type = ciModule.getType();
        ciModuleDto.price = ciModule.getPrice();
        return ciModuleDto;
    }

    public static List<CIModuleDto> toDtoList(List<CIModule> ciModules) {
        return ciModules.stream().map(CIModuleMapper::toDto).toList();
    }

    public static CIModule toEntity(CIModuleInputDto ciModuleInputDto) {
        CIModule ciModule = new CIModule();
        ciModule.setName(ciModuleInputDto.name);
        ciModule.setType(ciModuleInputDto.type);
        ciModule.setPrice(ciModuleInputDto.price);
        return ciModule;
    }
}
