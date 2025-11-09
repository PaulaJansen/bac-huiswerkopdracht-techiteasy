package nl.novi.homework.techiteasy.Dtos;

import jakarta.validation.constraints.NotBlank;

public class RemoteControllerInputDto {
    public String compatibleWith;
    public String batteryType;

    @NotBlank
    public String name;

    @NotBlank
    public String brand;
    public double price;
    public int originalStock;
}
