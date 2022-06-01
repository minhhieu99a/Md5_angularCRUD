package com.codegym.angularcrud.repository;

import com.codegym.angularcrud.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<City,Long> {
}
