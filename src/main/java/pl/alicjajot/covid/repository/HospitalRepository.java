package pl.alicjajot.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alicjajot.covid.domain.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital,Long>{
}
