package nl.novi.homework.techiteasy.Dtos;

import nl.novi.homework.techiteasy.models.ScreenType;

import java.time.LocalDate;

public class TelevisionDto {
    public long id;
    public String type;
    public String brand;
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
    public LocalDate releaseDate;
    public int stock;

}
