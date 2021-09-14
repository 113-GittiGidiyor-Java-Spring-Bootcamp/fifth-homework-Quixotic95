package dev.patika.quixotic95.schoolmanagementsystem.repository;

import dev.patika.quixotic95.schoolmanagementsystem.entity.logger.ExceptionLogger;
import dev.patika.quixotic95.schoolmanagementsystem.entity.logger.GenericLoggerEntity;
import dev.patika.quixotic95.schoolmanagementsystem.entity.logger.SalaryUpdateLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GenericLoggerRepository<T extends GenericLoggerRepository> extends JpaRepository<GenericLoggerEntity, Long> {

    Optional<List<ExceptionLogger>> findExceptionLoggerByStatusCodeContainingAndTimestampBetween(String statusCode, LocalDateTime from, LocalDateTime to);

    Optional<List<ExceptionLogger>> findExceptionLoggerByStatusCodeContaining(String statusCode);

    Optional<List<SalaryUpdateLogger>> findSalaryUpdateLoggerByInstructorId(long instructorId);

    Optional<List<SalaryUpdateLogger>> findSalaryUpdateLoggerByInstructorIdAndTimestampBetween(long instructorId, LocalDateTime from, LocalDateTime to);

}
