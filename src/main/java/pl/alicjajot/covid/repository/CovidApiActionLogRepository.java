package pl.alicjajot.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alicjajot.covid.domain.log.CovidApiActionLog;

public interface CovidApiActionLogRepository extends JpaRepository<CovidApiActionLog, Long>{
}
