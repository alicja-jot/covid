package pl.alicjajot.covid.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.alicjajot.covid.domain.CovidCase;
import pl.alicjajot.covid.dto.CovidCaseDto;
import pl.alicjajot.covid.dto.HospitalDto;
import pl.alicjajot.covid.domain.Hospital;
import pl.alicjajot.covid.repository.HospitalRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Transactional
    public HospitalDto getHospital(Long id){
        Hospital hospital = findHospital(id);
        HospitalDto hospitalDto = hospitalMapper.mapToDto(hospital);

        return hospitalDto;
    }

    @Transactional
    public List<HospitalDto> getHospitals(){
        List<Hospital> hospitals = hospitalRepository
                .findAll();
        List<HospitalDto> hospitalDtos = hospitalMapper.mapToDtos(hospitals);

        return hospitalDtos;
    }

    @Transactional
    public HospitalDto addHospital(HospitalDto hospitalDto1) {
        Hospital hospital = hospitalMapper.mapToEntity(hospitalDto1);

        if (hospitalDto1.getId() != null) {
            hospital.setSupportHospital(hospitalRepository.findById(hospitalDto1.getSupportHospitalId()).orElse(null));
        }
        hospitalRepository.save(hospital);
       HospitalDto hospitalDto = hospitalMapper.mapToDto(hospital);

        return hospitalDto;
    }

    @Transactional
    public HospitalDto updateHospital(Long id, HospitalDto hospitalDto1) {
        Hospital hospital = findHospital(id);

        hospitalMapper.update(hospital, hospitalDto1);
        hospitalRepository.save(hospital);
        hospitalRepository.save(hospital);
        HospitalDto hospitalDto = hospitalMapper.mapToDto(hospital);

        return hospitalDto;
    }

    public void deleteHospital(Long id) {
        Hospital hospital = findHospital(id);

        Hospital supportHospital = hospital.getSupportHospital();
        if (supportHospital == null) {
            throw new IllegalStateException("Cannot delete a hospital which have no supporting Hospital to move all cases!");
        }

        supportHospital.getCovidCases().addAll(hospital.getCovidCases());
        hospital.getCovidCases().clear();

        hospitalRepository.save(supportHospital);
        hospitalRepository.delete(hospital);
    }

    public Hospital findHospital(Long id) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return hospital;
    }

    public void addCase(Long hospitalId, CovidCase covidCase) {
        Hospital hospital = findHospital(hospitalId);

        covidCase.setHospital(hospital);
        hospital.getCovidCases().add(covidCase);

        hospitalRepository.save(hospital);

        return;
    }
}
