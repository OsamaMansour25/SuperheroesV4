package com.example.demo.Repositories;

import com.example.demo.Dto.SuperPowerDTO;
import com.example.demo.Model.SuperheroModel;
import com.example.demo.SuperInterface.ISuperHeroRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository("Superhero_db")
public class SuperheroRepository implements ISuperHeroRepository {
    @Value("${spring.datasource.url}")
    String db_url;
    @Value("${spring.datasource.username}")
    String u_id;
    @Value("${spring.datasource.password}")
    String pwd;

    public List<SuperheroModel> getAll() {
        List<SuperheroModel> superheroes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT * from superhero";
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(SQL);
            while (rst.next()) {
                String heroName = rst.getString("heroName");
                String realName = rst.getString("realName");
                int creationYear = Integer.parseInt(rst.getString("creationYear"));
                int id_City = Integer.parseInt(rst.getString("id_City"));
                int id = Integer.parseInt(rst.getString("id"));
                superheroes.add(new SuperheroModel(id, heroName, realName, creationYear, id_City));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return superheroes;
    }

    public List<SuperheroModel> getSuperhero(String name) {
        List<SuperheroModel> superheroes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT * FROM superhero WHERE heroName = ? OR realName = ?";
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, name);
            pst.setString(2, name);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                SuperheroModel superhero = new SuperheroModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                superheroes.add(superhero);
            }

            return superheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SuperheroModel> getSuperheroesFromCity(String cityName) {
        List<SuperheroModel> listOfSuperheroes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT * from superhero join city where city.id = superhero.id_city AND city.name= ?";
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, cityName);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SuperheroModel sr = new SuperheroModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                listOfSuperheroes.add(sr);

            }
            return listOfSuperheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<SuperheroModel> getSuperheroPower(String name) {
        List<SuperheroModel> superheroes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT * from superhero join superpower join superheropower\n" +
                    "ON superheropower.id_Superpower = superpower.id\n" +
                    "AND superhero.id = superheropower.id_Superhero\n" +
                    "AND superpower.name = ?";
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SuperheroModel superhero = new SuperheroModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                superheroes.add(superhero);
            }
            System.out.println(superheroes);
            return superheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<SuperPowerDTO> countSuperheroPower(String name) {
        List<SuperPowerDTO> superheroes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT superhero.id, superhero.heroName, realName, count(superheropower.id_Superhero) " +
                    "AS count from superhero join superheropower where heroName = ? group by superhero.id;";
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String realName = rs.getString("realName");
                int count = rs.getInt("count");
                SuperPowerDTO spd = new SuperPowerDTO(heroName, realName, count);

                superheroes.add(spd);
            }
            return superheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

