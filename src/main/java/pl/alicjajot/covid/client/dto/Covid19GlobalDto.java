package pl.alicjajot.covid.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Data
public class Covid19GlobalDto{

    @JsonProperty("NewConfirmed")
    private Long newConfirmed;

    @JsonProperty("TotalConfirmed")
    private Long totalConfirmed;
    @JsonProperty("NewDeaths")
    private Long newDeaths;

    @JsonProperty("TotalDeaths")
    private Long totalDeaths;
    @JsonProperty("NewRecovered")
    private Long newRecovered;

    @JsonProperty("TotalRecovered")
    private Long totalRecovered;
}
