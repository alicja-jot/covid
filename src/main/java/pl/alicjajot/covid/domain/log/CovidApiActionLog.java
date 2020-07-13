package pl.alicjajot.covid.domain.log;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import pl.alicjajot.covid.domain.BasicEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="COVID_API_ACTION_LOG")
public class CovidApiActionLog extends BasicEntity {

    private LocalDateTime time;
    private String action;

}
