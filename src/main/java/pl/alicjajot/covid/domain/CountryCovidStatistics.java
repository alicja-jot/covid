package pl.alicjajot.covid.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "COUNTRY_COVID_STATISTICS")
public class CountryCovidStatistics extends CovidStatistics {
    @ManyToOne(optional = false)
    @JoinColumn(name ="COUNTRY_ID",nullable = false)
    private Country country;;
}
