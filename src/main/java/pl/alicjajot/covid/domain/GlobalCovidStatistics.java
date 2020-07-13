package pl.alicjajot.covid.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name="GLOBAL_COVID_STATISTICS")
public class GlobalCovidStatistics extends CovidStatistics {
}
