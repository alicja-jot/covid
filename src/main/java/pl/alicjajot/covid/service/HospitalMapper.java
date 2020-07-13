package pl.alicjajot.covid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.alicjajot.covid.domain.CovidCase;
import pl.alicjajot.covid.dto.HospitalDto;
import pl.alicjajot.covid.domain.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HospitalMapper{

    @Autowired
    private CaseMapper caseMapper;;

    public HospitalDto mapToDto(Hospital hospital){
        HospitalDto hospitalDto = new HospitalDto();

        hospitalDto.setId(hospital.getId());
       hospitalDto.setName(hospital.getName());
       hospitalDto.setCovidCases(caseMapper.mapToDtos(hospital.getCovidCases()));

        return hospitalDto;
    }

    public List<HospitalDto> mapToDtos(List<Hospital> hospitals){
        List<HospitalDto> hospitalDtos = new ArrayList<>();

        for (Hospital hospital : hospitals) {
            HospitalDto hospitalDto = mapToDto(hospital);
            hospitalDtos.add(hospitalDto);
        }

        return hospitalDtos;
    }

    public Hospital mapToEntity(HospitalDto hospitalDto){
        Hospital hospital = new Hospital();
        update(hospital, hospitalDto);

        return hospital;
    }

    public void update(Hospital hospital, HospitalDto hospitalDto){
        hospital.setName(hospitalDto.getName());
        List<CovidCase> covidCases = caseMapper.mapToEntities(hospitalDto.getCovidCases());
        hospital.setCovidCases(covidCases);


    }
}
