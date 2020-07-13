package pl.alicjajot.covid.dto;

import lombok.Data;

@Data
public class CovidCaseDto {

    private String name;
    private String surname;
    private CaseStatus status;
    private Long hospitalId;

    public static class CaseDtoBuilder {

        private String name;
        private String surname;
        private CaseStatus status;
        private Long hospitalId;

        public static CaseDtoBuilder builder() {
            return new CaseDtoBuilder();
        }

        public CaseDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CaseDtoBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public CaseDtoBuilder status(CaseStatus status) {
            this.status = status;
            return this;
        }

        public CaseDtoBuilder hospitalId(Long hospitalId) {
            this.hospitalId = hospitalId;
            return this;
        }

        public CovidCaseDto build() {
            CovidCaseDto covidCaseDto = new CovidCaseDto();
            covidCaseDto.setName(name);
            covidCaseDto.setSurname(surname);
            covidCaseDto.setStatus(status);
            covidCaseDto.setHospitalId(hospitalId);
            return covidCaseDto;
        }
    }

}
