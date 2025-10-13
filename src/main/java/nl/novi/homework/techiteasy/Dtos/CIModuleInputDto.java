package nl.novi.homework.techiteasy.Dtos;

import jakarta.validation.constraints.NotBlank;

public class CIModuleInputDto {

    @NotBlank
    public String name;

    @NotBlank
    public String type;
    public double price;
}
