package pl.alicjajot.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.alicjajot.covid.domain.Country;
import pl.alicjajot.covid.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country,Long>{

    Optional<Country> findByName(String name);
}
