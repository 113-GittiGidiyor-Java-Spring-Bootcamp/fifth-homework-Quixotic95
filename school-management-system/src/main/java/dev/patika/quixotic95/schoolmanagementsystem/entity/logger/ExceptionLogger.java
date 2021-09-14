package dev.patika.quixotic95.schoolmanagementsystem.entity.logger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExceptionLogger extends GenericLoggerEntity {

    private String statusCode;

    private String message;

    public ExceptionLogger(String message, HttpStatus type) {
        this.message = message;
        this.statusCode = type.toString();
    }

}
