package pl.alicjajot.covid.domain;

import lombok.Getter;
import lombok.Setter;
import pl.alicjajot.covid.dto.CaseStatus;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="COVID_CASE")
public class CovidCase extends BasicEntity {
    @Column(name = "NAME",nullable = false)
    private String name;
    @Column(name = "SURNAME",nullable = false)
    private String surname;

    @ManyToOne
    @JoinColumn(name ="HOSPITAL_ID")
    private Hospital hospital;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS",nullable = false)
    private CaseStatus status;

}
