package pl.alicjajot.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alicjajot.covid.domain.CovidCase;

public interface CaseRepository extends JpaRepository<CovidCase, Long>{
}
