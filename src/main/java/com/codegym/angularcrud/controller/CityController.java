package com.codegym.angularcrud.controller;

import com.codegym.angularcrud.DTO.CityForm;
import com.codegym.angularcrud.model.City;
import com.codegym.angularcrud.model.Country;
import com.codegym.angularcrud.service.city.ICityService;
import com.codegym.angularcrud.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private ICityService cityService;
    @Autowired
    private ICountryService countryService;
    @Autowired
    private Environment environment;

    @ModelAttribute("country")
    public Iterable<Country> listAllCategory() {
        return countryService.findAll();
    }

    @GetMapping("/country")
    public ResponseEntity<Iterable<Country>> getAllCountry (){
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<City>> showAllCity (){
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<City> cityDetail (@PathVariable Long id){
        return new ResponseEntity<>(cityService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> createNewCity (@ModelAttribute CityForm cityForm){
        MultipartFile multipartFile = cityForm.getImage();
        String filename = multipartFile.getOriginalFilename();
        String fileUpload =environment.getProperty("upload.path");
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(fileUpload+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        City city = new City(cityForm.getName(),cityForm.getCountry(),cityForm.getArea(),cityForm.getPopulation(),cityForm.getGdp(),cityForm.getDescription(),filename);
        return new ResponseEntity<>(cityService.save(city),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()){
            cityService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id,@ModelAttribute CityForm cityForm){
        Optional<City> city = cityService.findById(id);
        cityForm.setId(city.get().getId());
        MultipartFile multipartFile = cityForm.getImage();
       String fileName = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path");
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(fileUpload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        City city1 =new City(id,cityForm.getName(),cityForm.getCountry(),cityForm.getArea(),cityForm.getPopulation(),cityForm.getGdp(),cityForm.getDescription(),fileName);

        if (city1.getImage().equals("image")){
            city1.setImage(city.get().getImage());
        }
        return new ResponseEntity<>(cityService.save(city1),HttpStatus.OK);
    }

}
