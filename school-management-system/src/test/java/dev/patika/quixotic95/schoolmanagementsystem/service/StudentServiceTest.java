package dev.patika.quixotic95.schoolmanagementsystem.service;

import dev.patika.quixotic95.schoolmanagementsystem.dto.StudentDTO;
import dev.patika.quixotic95.schoolmanagementsystem.entity.Student;
import dev.patika.quixotic95.schoolmanagementsystem.mapper.StudentMapper;
import dev.patika.quixotic95.schoolmanagementsystem.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository mockStudentRepository;

    @Mock
    StudentMapper mockStudentMapper;

    @InjectMocks
    StudentService studentService;

    Student exampleStudent;
    StudentDTO exampleStudentDTO;
    List<Student> exampleStudentList;

    @BeforeEach
    void initialize() {
        exampleStudent = new Student();
        exampleStudentDTO = new StudentDTO();
        exampleStudentList = new ArrayList<>();

    }

    @AfterEach
    void nullifier() {
        exampleStudent = null;
        exampleStudentDTO = null;
        exampleStudentList = null;
    }

    @Test
    void should_return_List_of_StudentDTO_when_findAllStudents_called() {

        when(mockStudentRepository.findAll()).thenReturn(exampleStudentList);
        List<StudentDTO> expected = exampleStudentList
                .stream()
                .map(mockStudentMapper::mapFromStudentToStudentDTO)
                .collect(Collectors.toList());


        List<StudentDTO> actual = studentService.findAllStudents();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void should_return_StudentDTO_when_findStudentById_called() {

        when(mockStudentMapper.mapFromStudentToStudentDTO(any())).thenReturn(exampleStudentDTO);

        Optional<Student> optionalStudent = Optional.of(exampleStudent);
        when(mockStudentRepository.findById(anyLong())).thenReturn(optionalStudent);

        StudentDTO expected = mockStudentMapper.mapFromStudentToStudentDTO(exampleStudent);

        StudentDTO actual = studentService.findStudentById(anyLong());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void should_throw_EntityNotFoundException_when_findStudentById_called_if_Student_is_not_in_database() {

        assertThrows(EntityNotFoundException.class, () -> studentService.findStudentById(anyLong()));

    }

    @Test
    void saveStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void deleteStudentById() {
    }

    @Test
    void findAllCourseStudentsById() {
    }

    @Test
    void findAllCourseStudentIdsByList() {
    }
}