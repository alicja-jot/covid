package pl.alicjajot.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alicjajot.covid.domain.GlobalCovidStatistics;

import java.util.Optional;

public interface GlobalCovidStatisticsRepository extends JpaRepository<GlobalCovidStatistics, Long>{
    Optional<GlobalCovidStatistics> findFirst1ByOrderByDateDesc();
}
