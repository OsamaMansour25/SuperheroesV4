package com.example.demo.SuperInterface;

import com.example.demo.Model.SuperheroModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("Superhero_Stub")
public class SuperHeroStub implements ISuperHeroRepository {
    private List<SuperheroModel> superHeroes;

    public SuperHeroStub() {

        superHeroes = new ArrayList<>(List.of
                (new SuperheroModel(100, "Supergirl", "Kara Zor-El", 1920, 2)));
    }

    @Override
    public List<SuperheroModel> getAll() {
        return superHeroes;
    }

    @Override
    public List<SuperheroModel> getSuperhero(String name) {
        for (SuperheroModel superhero : superHeroes) {
            if (superhero.getHeroName().toLowerCase().equals(name)) {
                superHeroes.add(superhero);
                return superHeroes;
            }
        }
        return null;
    }

}
