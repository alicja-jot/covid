package pl.alicjajot.covid.domain.log;

import lombok.Getter;
import lombok.Setter;
import pl.alicjajot.covid.domain.BasicEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name ="REST_ACTION_LOG")
public class RestActionLog extends BasicEntity {
    private LocalDateTime time;
    private String action;

}
