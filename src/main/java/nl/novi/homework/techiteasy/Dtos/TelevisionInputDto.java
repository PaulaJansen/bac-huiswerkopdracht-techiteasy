package nl.novi.homework.techiteasy.Dtos;

import jakarta.validation.constraints.NotBlank;
import nl.novi.homework.techiteasy.models.ScreenType;

import java.time.LocalDate;

public class TelevisionInputDto {

    @NotBlank
    public String type;

    @NotBlank
    public String brand;

    @NotBlank
    public String name;

    public double price;
    public double availableSize;
    public int refreshRate;
    public ScreenType screenType;
    public String screenQuality;
    public boolean smartTv;
    public boolean wifi;
    public boolean voiceControl;
    public boolean hdr;
    public boolean bluetooth;
    public boolean ambiLight;
    public int originalStock;
    public LocalDate releaseDate;
}
