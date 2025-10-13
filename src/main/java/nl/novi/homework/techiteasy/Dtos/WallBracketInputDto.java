package nl.novi.homework.techiteasy.Dtos;

import jakarta.validation.constraints.NotBlank;

public class WallBracketInputDto {

    public String size;
    public boolean adjustable;

    @NotBlank
    public String name;
    public double price;
}
