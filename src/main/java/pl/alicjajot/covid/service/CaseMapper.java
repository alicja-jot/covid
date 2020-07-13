package pl.alicjajot.covid.service;

import org.springframework.stereotype.Component;
import pl.alicjajot.covid.domain.CovidCase;
import pl.alicjajot.covid.dto.CovidCaseDto;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CaseMapper{

    public CovidCaseDto mapToDto(CovidCase covidCase) {


        CovidCaseDto covidCaseDto = new CovidCaseDto();
        covidCaseDto.setName(covidCase.getName());
        covidCaseDto.setSurname(covidCase.getSurname());
        covidCaseDto.setStatus(covidCase.getStatus());
        covidCaseDto.setHospitalId(covidCase.getHospital().getId());
        return covidCaseDto;
    }

    public List<CovidCaseDto> mapToDtos(List<CovidCase> covidCases) {
        return covidCases.stream().map(covidCase -> mapToDto(covidCase)).collect(Collectors.toList());


    }

    public CovidCase mapToEntity(CovidCaseDto covidCaseDto) {
        CovidCase covidCase = new CovidCase();
        update(covidCase, covidCaseDto);


        return covidCase;
    }

    public void update(CovidCase covidCase, CovidCaseDto covidCaseDto) {
        covidCase.setName(covidCaseDto.getName());
        covidCase.setSurname(covidCaseDto.getSurname());
        covidCase.setStatus(covidCaseDto.getStatus());
    }

    public List<CovidCase> mapToEntities(List<CovidCaseDto> covidCasesDtos) {
        if (covidCasesDtos == null) {
            return new LinkedList<>();
        }

        return covidCasesDtos.stream().map(covidCaseDto -> mapToEntity(covidCaseDto))
                .collect(Collectors.toList());

    }
}
