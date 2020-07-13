package pl.alicjajot.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alicjajot.covid.domain.log.RestActionLog;

public interface RestActionLogRepository extends JpaRepository<RestActionLog, Long>{
}
