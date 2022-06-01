package com.codegym.angularcrud.repository;

import com.codegym.angularcrud.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountryRepository extends JpaRepository<Country,Long> {
}
