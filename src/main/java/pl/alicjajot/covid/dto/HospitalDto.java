package pl.alicjajot.covid.dto;

import lombok.Data;
import pl.alicjajot.covid.dto.CovidCaseDto;

import java.util.List;

@Data
public class HospitalDto {

    private Long id;
    private String name;
    private List<CovidCaseDto> covidCases;
    private Long supportHospitalId;

}
