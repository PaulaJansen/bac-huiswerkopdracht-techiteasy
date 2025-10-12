package nl.novi.homework.techiteasy.Dtos;

import jakarta.validation.constraints.NotBlank;

public class CIModuleInputDto {

    @NotBlank
    private String name;

    @NotBlank
    private String type;
    private double price;
}
