package pl.alicjajot.covid.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
public class Covid19CountryDto {

    @JsonProperty("Country")
    private String country;
   @JsonProperty("CountryCode")
   private String countryCode;

    @JsonProperty("Slug")
    private String slug;
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

   @JsonProperty("Date")
    private LocalDateTime date;

    @JsonProperty("Premium")
    private Object premium;


}
