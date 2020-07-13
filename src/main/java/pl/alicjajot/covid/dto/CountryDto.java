package pl.alicjajot.covid.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
public class CountryDto{

    private String name;
    private StatisticsDto statistics;


    public static class CountryDtoBuilder{
        private String name;
        private StatisticsDto statistics;

        public static CountryDtoBuilder builder() {
            return new CountryDtoBuilder();
        }

        public CountryDtoBuilder name(String name){
            this.name = name;
           return this;
        }

        public CountryDtoBuilder statistics(StatisticsDto statistics) {
            this.statistics = statistics;
           return this;
        }

        public CountryDto build() {
            CountryDto countryDto = new CountryDto();
            countryDto.setStatistics(statistics);
            countryDto.setName(name);
            return countryDto;
        }
    }
}
