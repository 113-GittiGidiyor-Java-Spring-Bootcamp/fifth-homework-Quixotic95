package dev.patika.quixotic95.schoolmanagementsystem.repository;

import dev.patika.quixotic95.schoolmanagementsystem.entity.logger.GenericLoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericLoggerRepository<T extends GenericLoggerRepository> extends JpaRepository<GenericLoggerEntity, Long> {
}
