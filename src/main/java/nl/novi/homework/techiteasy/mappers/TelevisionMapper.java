package nl.novi.homework.techiteasy.mappers;

import nl.novi.homework.techiteasy.Dtos.TelevisionDto;
import nl.novi.homework.techiteasy.Dtos.TelevisionInputDto;
import nl.novi.homework.techiteasy.models.Television;

import java.util.List;

public class TelevisionMapper {

    public static TelevisionDto toDto(Television television) {
        TelevisionDto televisionDto = new TelevisionDto();
        televisionDto.id = television.getId();
        televisionDto.type = television.getType();
        televisionDto.brand = television.getBrand();
        televisionDto.name = television.getName();
        televisionDto.price = television.getPrice();
        televisionDto.availableSize = television.getAvailableSize();
        televisionDto.refreshRate = television.getRefreshRate();
        televisionDto.screenType= television.getScreenType();
        televisionDto.screenQuality= television.getScreenQuality();
        televisionDto.smartTv = television.isSmartTv();
        televisionDto.wifi = television.isWifi();
        televisionDto.voiceControl = television.isVoiceControl();
        televisionDto.hdr = television.isHdr();
        televisionDto.bluetooth = television.isBluetooth();
        televisionDto.ambiLight = television.isAmbiLight();
        televisionDto.releaseDate = television.getReleaseDate();
        televisionDto.stock = (television.getOriginalStock() - television.getSold());
        return televisionDto;
    }

    public static List<TelevisionDto> toDtoList(List<Television> televisions) {
        return televisions.stream().map(TelevisionMapper::toDto).toList();
    }

    public static Television toEntity(TelevisionInputDto televisionInputDto) {
        Television television = new Television();
        television.setType(televisionInputDto.type);
        television.setBrand(televisionInputDto.brand);
        television.setName(televisionInputDto.name);
        television.setPrice(televisionInputDto.price);
        television.setAvailableSize(televisionInputDto.availableSize);
        television.setRefreshRate(televisionInputDto.refreshRate);
        television.setScreenType(televisionInputDto.screenType);
        television.setScreenQuality(televisionInputDto.screenQuality);
        television.setSmartTv(televisionInputDto.smartTv);
        television.setWifi(televisionInputDto.wifi);
        television.setVoiceControl(televisionInputDto.voiceControl);
        television.setHdr(televisionInputDto.hdr);
        television.setBluetooth(televisionInputDto.bluetooth);
        television.setAmbiLight(televisionInputDto.ambiLight);
        television.setOriginalStock(televisionInputDto.originalStock);
        television.setSold(0);
        television.setReleaseDate(televisionInputDto.releaseDate);
        return television;
    }
}
