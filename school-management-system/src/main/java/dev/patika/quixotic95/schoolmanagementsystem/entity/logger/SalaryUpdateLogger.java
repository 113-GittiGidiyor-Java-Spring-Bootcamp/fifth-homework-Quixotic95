package dev.patika.quixotic95.schoolmanagementsystem.entity.logger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalaryUpdateLogger extends GenericLoggerEntity {

    private long instructorId;

    private double salaryBeforeUpdate;

    private double salaryAfterUpdate;

    private double updatePercentage;

    private String clientIpAddress;

    private String clientRequestUrl;

    private String clientSessionId;

}
