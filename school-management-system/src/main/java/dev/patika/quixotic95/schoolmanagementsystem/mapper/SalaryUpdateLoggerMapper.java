package dev.patika.quixotic95.schoolmanagementsystem.mapper;

import dev.patika.quixotic95.schoolmanagementsystem.dto.SalaryUpdateLoggerDTO;
import dev.patika.quixotic95.schoolmanagementsystem.entity.logger.SalaryUpdateLogger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SalaryUpdateLoggerMapper {

    public abstract SalaryUpdateLoggerDTO toDto(SalaryUpdateLogger salaryUpdateLogger);

}
