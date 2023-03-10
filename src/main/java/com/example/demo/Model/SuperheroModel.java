package com.example.demo.Model;

public class SuperheroModel {
  private  String heroName;
  private String realName;
    private int creationYear;
    private int id;
    private int id_City;
    public SuperheroModel(int id, String heroName, String realName, int creationYear, int id_City) {
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
        this.id_City = id_City;
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public String getHeroName() {
        return heroName;
    }
    public int getCreationYear() {
        return creationYear;
    }
    public int getId_City() {
        return id_City;
    }
    public int id() {
        return id;
    }

}
