package pl.alicjajot.covid.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Data
public class Covid19SummaryDto {

    @JsonProperty("Global")
    private Covid19GlobalDto global;
    @JsonProperty("Countries")
    private List<Covid19CountryDto> countries;

    @JsonProperty("Date")
    private LocalDateTime date;;
}
