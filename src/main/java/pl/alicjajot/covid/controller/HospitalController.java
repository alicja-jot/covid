package pl.alicjajot.covid.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.alicjajot.covid.dto.HospitalDto;
import pl.alicjajot.covid.service.HospitalService;
import pl.alicjajot.covid.service.RestActionLogService;

import java.util.List;

@RestController
@RequestMapping("/v1/hospital")
public class HospitalController {

    @Autowired
    private RestActionLogService restActionLogService;

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/{id}")
    public HospitalDto getHospital(@PathVariable Long id){
        restActionLogService.logRestEndpoint("GET /v1/hospital/" + id);


        HospitalDto hospital = hospitalService.getHospital(id);
        return hospital;
    }

    @GetMapping
    public List<HospitalDto> getHospitals(){
        restActionLogService.logRestEndpoint("GET /v1/hospital");

        return hospitalService.getHospitals();
    }

    @PostMapping
    public HospitalDto addHospital(@RequestBody HospitalDto hospitalDto){
        restActionLogService.logRestEndpoint("POST /v1/hospital");

        HospitalDto hospitalDto1 = hospitalService.addHospital(hospitalDto);
        return hospitalDto1;
    }

    @PutMapping("/{id}")
    public HospitalDto updateHospital(@PathVariable Long id, @RequestBody HospitalDto hospitalDto) {
        restActionLogService.logRestEndpoint("PUT /v1/hospital/" + id);


        HospitalDto hospitalDto1 = hospitalService.updateHospital(id, hospitalDto);
        return hospitalDto1;
    }

    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable Long id){
      restActionLogService.logRestEndpoint("DELETE /v1/hospital/" + id);


        hospitalService.deleteHospital(id);

        return;
    }
}
