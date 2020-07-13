package pl.alicjajot.covid.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "HOSPITAL")
public class Hospital extends BasicEntity {

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade={CascadeType.ALL})
    private List<CovidCase> covidCases = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name="SUPPORT_HOSPITAL")
    private Hospital supportHospital;

}
