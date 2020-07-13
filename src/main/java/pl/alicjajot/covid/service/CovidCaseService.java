package pl.alicjajot.covid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.alicjajot.covid.domain.CovidCase;
import pl.alicjajot.covid.domain.Hospital;
import pl.alicjajot.covid.dto.CovidCaseDto;
import pl.alicjajot.covid.repository.CaseRepository;
import pl.alicjajot.covid.repository.HospitalRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CovidCaseService {

    @Autowired private HospitalService hospitalService;
    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private CaseMapper caseMapper;

    @Transactional
    public CovidCaseDto getCase(Long id){
        CovidCase covidCase = caseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        return caseMapper.mapToDto(covidCase);
    }

    @Transactional
    public List<CovidCaseDto> getCases() {
        List<CovidCase> covidCases = caseRepository.findAll();

        List<CovidCaseDto> covidCaseDtos = caseMapper.mapToDtos(covidCases);


        return covidCaseDtos;
    }

    @Transactional
    public CovidCaseDto addCase(CovidCaseDto covidCaseDto){
        CovidCase covidCase = caseMapper.mapToEntity(covidCaseDto);
        hospitalService.addCase(covidCaseDto.getHospitalId(), covidCase);

        CovidCaseDto covidCaseDto1 = caseMapper.mapToDto(covidCase);
        return covidCaseDto1;

    }

    @Transactional
    public CovidCaseDto updateCase(Long id, CovidCaseDto covidCaseDto){
        CovidCase covidCase = caseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        caseMapper.update(covidCase, covidCaseDto);


        caseRepository.save(covidCase);


        return caseMapper.mapToDto(covidCase);
    }
}
