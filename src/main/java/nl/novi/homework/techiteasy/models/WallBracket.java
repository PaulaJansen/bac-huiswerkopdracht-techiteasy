package nl.novi.homework.techiteasy.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class WallBracket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String size;
    private boolean adjustable;
    private String name;
    private double price;
}
