package com.codegym.angularcrud.DTO;

import com.codegym.angularcrud.model.Country;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

public class CityForm {
    private Long id ;
    private  String name;
    private Country country;
    private Double area;
    private Long population;
    private Double gdp;
    private String description;
    private MultipartFile image;

    public CityForm() {
    }

    public CityForm(String name, Country country, Double area, Long population, Double gdp, String description, MultipartFile image) {
        this.name = name;
        this.country = country;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.description = description;
        this.image = image;
    }

    public CityForm(Long id, String name, Country country, Double area, Long population, Double gdp, String description, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Double getGdp() {
        return gdp;
    }

    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
