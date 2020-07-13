package pl.alicjajot.covid.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
public class RestActionLogDto {
    private LocalDateTime time;
    private String action;
}
