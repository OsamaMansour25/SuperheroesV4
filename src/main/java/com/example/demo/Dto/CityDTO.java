package com.example.demo.Dto;

public class CityDTO {
    private int id;
    private String name;

    public CityDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
    private int getId() {
        return id;
    }
    private String getName() {
        return name;
    }
}
