package dev.patika.quixotic95.schoolmanagementsystem.entity.logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class GenericLoggerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @CreatedDate
    @Column(name = "timestamp", updatable = false)
    @JsonIgnore
    private LocalDateTime timestamp = LocalDateTime.now();

}
