package com.example.demo.Dto;

public class SuperheroPowerDTO {
   private int id_Superhero;
    private int id_SuperPower;

    public SuperheroPowerDTO(int id_Superhero, int id_SuperPower) {
        this.id_Superhero = id_Superhero;
        this.id_SuperPower = id_SuperPower;

    }
    public int getId_Superhero() {
        return id_Superhero;
    }
    public int getId_SuperPower() {
        return id_SuperPower;
    }
}
