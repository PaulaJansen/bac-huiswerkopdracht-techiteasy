package nl.novi.homework.techiteasy.mappers;

import nl.novi.homework.techiteasy.Dtos.RemoteControllerDto;
import nl.novi.homework.techiteasy.Dtos.RemoteControllerInputDto;
import nl.novi.homework.techiteasy.models.RemoteController;

import java.util.List;

public class RemoteControllerMapper {

    public static RemoteControllerDto toDto(RemoteController remoteController) {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        remoteControllerDto.id = remoteController.getId();
        remoteControllerDto.compatibleWith = remoteController.getCompatibleWith();
        remoteControllerDto.batteryType = remoteController.getBatteryType();
        remoteControllerDto.name = remoteController.getName();
        remoteControllerDto.brand = remoteController.getBrand();
        remoteControllerDto.price = remoteController.getPrice();
        remoteControllerDto.originalStock = remoteController.getOriginalStock();
        return remoteControllerDto;
    }

    public static List<RemoteControllerDto> toDtoList(List<RemoteController> remoteControllers) {
        return remoteControllers.stream().map(RemoteControllerMapper::toDto).toList();
    }

    public static RemoteController toEntity(RemoteControllerInputDto remoteControllerInputDto) {
        RemoteController remoteController = new RemoteController();
        remoteController.setCompatibleWith(remoteControllerInputDto.compatibleWith);
        remoteController.setBatteryType(remoteControllerInputDto.batteryType);
        remoteController.setName(remoteControllerInputDto.name);
        remoteController.setBrand(remoteControllerInputDto.brand);
        remoteController.setPrice(remoteControllerInputDto.price);
        remoteController.setOriginalStock(remoteControllerInputDto.originalStock);
        return  remoteController;
    }
}
