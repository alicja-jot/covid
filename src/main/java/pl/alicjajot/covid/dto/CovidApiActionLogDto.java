package pl.alicjajot.covid.dto;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Data
public class CovidApiActionLogDto {
    private LocalDateTime time;
    private String action;


    public static class CovidApiActionLogDtoBuilder{
        private LocalDateTime time;;
        private String action;

        public static CovidApiActionLogDtoBuilder builder() {
            return new CovidApiActionLogDtoBuilder();
        }

        public CovidApiActionLogDtoBuilder time(LocalDateTime time) {
            this.time=time;
            return this;
        }

        public CovidApiActionLogDtoBuilder action(String action) {
            this.action= action;
            return this;
        }

        public CovidApiActionLogDto build() {
            CovidApiActionLogDto covidApiActionLogDto = new CovidApiActionLogDto();
            covidApiActionLogDto.setTime(time);
            covidApiActionLogDto.setAction(action);
            return covidApiActionLogDto;
        }
    }
}
