package pl.alicjajot.covid.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public abstract class CovidStatistics extends BasicEntity {
    private Long totalConfirmed = 0L;
    private Long newConfirmed = 0L;
    private Long newDeaths = 0L;
    private Long totalDeaths = 0L;
    private Long newRecovered = 0L;
    private Long totalRecovered = 0L;
    private LocalDateTime date;
}
