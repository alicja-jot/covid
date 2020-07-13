package pl.alicjajot.covid.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.alicjajot.covid.domain.CovidCase;
import pl.alicjajot.covid.domain.Hospital;
import pl.alicjajot.covid.dto.CovidCaseDto;
import pl.alicjajot.covid.dto.CaseStatus;
import pl.alicjajot.covid.repository.CaseRepository;
import pl.alicjajot.covid.repository.HospitalRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CovidCovidCaseServiceTestSuite {

    @Autowired
    private CovidCaseService covidCaseService;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    void testGetCase() {
        // given
        Hospital hospital = new Hospital();
        hospital.setName("Polish hospital");

        CovidCase covidCase = new CovidCase();
        covidCase.setName("John");
        covidCase.setSurname("Rambo");
        covidCase.setStatus(CaseStatus.ACTIVE);
        covidCase.setHospital(hospital);
        hospital.getCovidCases().add(covidCase);
        Hospital savedHospital = hospitalRepository.save(hospital);
        CovidCase savedCovidCase = caseRepository.save(covidCase);

        // when
        CovidCaseDto foundCase = covidCaseService.getCase(savedCovidCase.getId());

        // then
        assertThat(foundCase).isNotNull();
        assertThat(foundCase.getName()).isEqualTo("John");
        assertThat(foundCase.getSurname()).isEqualTo("Rambo");
        assertThat(foundCase.getStatus()).isEqualTo(CaseStatus.ACTIVE);
    }

    @Test
    void testGetCases() {
        // given
        Hospital hospital = new Hospital();
        hospital.setName("Polish hospital");

        CovidCase rambo = new CovidCase();
        rambo.setName("John");
        rambo.setSurname("Rambo");
        rambo.setStatus(CaseStatus.ACTIVE);
        rambo.setHospital(hospital);
        hospital.getCovidCases().add(rambo);

        CovidCase stallone = new CovidCase();
        stallone.setName("Sylvester");
        stallone.setSurname("Stallone");
        stallone.setStatus(CaseStatus.RECOVERED);
        stallone.setHospital(hospital);
        hospital.getCovidCases().add(stallone);

        hospitalRepository.save(hospital);
        caseRepository.save(rambo);
        caseRepository.save(stallone);

        // when
        List<CovidCaseDto> foundCases = covidCaseService.getCases();

        // then
        assertThat(foundCases).hasSize(2);
        assertThat(foundCases.get(0).getName()).isEqualTo("John");
        assertThat(foundCases.get(0).getSurname()).isEqualTo("Rambo");
        assertThat(foundCases.get(0).getStatus()).isEqualTo(CaseStatus.ACTIVE);
        assertThat(foundCases.get(1).getName()).isEqualTo("Sylvester");
        assertThat(foundCases.get(1).getSurname()).isEqualTo("Stallone");
        assertThat(foundCases.get(1).getStatus()).isEqualTo(CaseStatus.RECOVERED);
    }

    @Test
    void testAddCase() {
        // given
        Hospital hospital = new Hospital();
        hospital.setName("Polish hospital");
        Hospital savedHospital = hospitalRepository.save(hospital);

        CovidCaseDto covidCaseDto = CovidCaseDto.CaseDtoBuilder
                .builder()
                .name("Jason")
                .surname("Statham")
                .status(CaseStatus.DEATH)
                .hospitalId(savedHospital.getId())
                .build();

        // when
        CovidCaseDto createdCovidCaseDto = covidCaseService.addCase(covidCaseDto);

        // then
        assertThat(createdCovidCaseDto).isEqualTo(covidCaseDto);
    }

    @Test
    void testUpdateCase() {
        // given
        Hospital hospital = new Hospital();
        hospital.setName("Polish hospital");

        CovidCase covidCase = new CovidCase();
        covidCase.setName("John");
        covidCase.setSurname("Rambo");
        covidCase.setStatus(CaseStatus.ACTIVE);
        covidCase.setHospital(hospital);
        hospital.getCovidCases().add(covidCase);
        Hospital savedHospital = hospitalRepository.save(hospital);
        CovidCase savedCovidCase = caseRepository.save(covidCase);

        CovidCaseDto covidCaseDto = CovidCaseDto.CaseDtoBuilder
                .builder()
                .name("Jason")
                .surname("Statham")
                .status(CaseStatus.DEATH)
                .hospitalId(savedHospital.getId())
                .build();

        // when
        CovidCaseDto updatedCovidCaseDto = covidCaseService.updateCase(savedCovidCase.getId(), covidCaseDto);

        // then
        assertThat(updatedCovidCaseDto).isEqualTo(covidCaseDto);
        CovidCase updatedCovidCase = caseRepository.findById(savedCovidCase.getId())
                .orElseThrow(EntityNotFoundException::new);

        assertThat(updatedCovidCase.getName()).isEqualTo(covidCaseDto.getName());
        assertThat(updatedCovidCase.getSurname()).isEqualTo(covidCaseDto.getSurname());
        assertThat(updatedCovidCase.getStatus()).isEqualTo(covidCaseDto.getStatus());
    }
}