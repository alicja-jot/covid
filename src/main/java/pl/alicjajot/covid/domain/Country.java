package pl.alicjajot.covid.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "COUNTRY")
public class Country extends BasicEntity {

    @Column(name = "NAME", unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CountryCovidStatistics> statistics = new LinkedList<>();

    public Optional<CountryCovidStatistics> getLatestStatistics(){
        Optional<CountryCovidStatistics> max = statistics
                .stream()
                .max((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return max;
    }
}
