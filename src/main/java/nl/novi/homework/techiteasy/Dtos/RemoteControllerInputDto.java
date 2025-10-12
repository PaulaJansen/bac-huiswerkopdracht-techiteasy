package nl.novi.homework.techiteasy.Dtos;

import jakarta.validation.constraints.NotBlank;

public class RemoteControllerInputDto {
    private String compatibleWith;
    private String batteryType;

    @NotBlank
    private String name;

    @NotBlank
    private String brand;
    private double price;
    private int originalStock;
}
