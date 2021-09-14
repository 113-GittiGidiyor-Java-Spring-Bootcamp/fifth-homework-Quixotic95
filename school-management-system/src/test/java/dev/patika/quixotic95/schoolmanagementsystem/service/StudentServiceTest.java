package dev.patika.quixotic95.schoolmanagementsystem.service;

import dev.patika.quixotic95.schoolmanagementsystem.dto.StudentDTO;
import dev.patika.quixotic95.schoolmanagementsystem.entity.Student;
import dev.patika.quixotic95.schoolmanagementsystem.exception.StudentAgeNotValidException;
import dev.patika.quixotic95.schoolmanagementsystem.mapper.StudentMapper;
import dev.patika.quixotic95.schoolmanagementsystem.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        StudentDTO actual = studentService.findStudentById(5L);

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
    void should_return_StudentDTO_when_saveStudent_called() {

        when(mockStudentMapper.mapFromStudentToStudentDTO(any())).thenReturn(exampleStudentDTO);

        when(mockStudentRepository.save(any())).thenReturn(exampleStudent);
        StudentDTO expected = mockStudentMapper.mapFromStudentToStudentDTO(exampleStudent);

        exampleStudentDTO.setBirthDate(LocalDate.now().minusYears(19));

        StudentDTO actual = studentService.saveStudent(exampleStudentDTO);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @ParameterizedTest
    @MethodSource("dateProvider")
    void should_throw_StudentAgeNotValidException_when_saveStudent_called_if_Student_age_is_not_between_18_and_40(LocalDate givenDate) {

        exampleStudentDTO.setBirthDate(givenDate);
        assertThrows(StudentAgeNotValidException.class, () -> studentService.saveStudent(exampleStudentDTO));

    }

    @Test
    void should_return_StudentDTO_when_updateStudent_called() {

        when(mockStudentMapper.mapFromStudentToStudentDTO(any())).thenReturn(exampleStudentDTO);
        when(mockStudentMapper.mapFromStudentDTOtoStudent(any())).thenReturn(exampleStudent);

        Optional<Student> optionalStudent = Optional.of(exampleStudent);
        when(mockStudentRepository.findById(anyLong())).thenReturn(optionalStudent);

        when(mockStudentRepository.save(any())).thenReturn(exampleStudent);
        StudentDTO expected = mockStudentMapper.mapFromStudentToStudentDTO(exampleStudent);

        exampleStudentDTO.setBirthDate(LocalDate.now().minusYears(19));
        StudentDTO actual = studentService.updateStudent(exampleStudentDTO, anyLong());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @ParameterizedTest
    @MethodSource("dateProvider")
    void should_throw_StudentAgeNotValidException_when_updateStudent_called_if_Student_age_is_not_between_18_and_40(LocalDate givenDate) {

        Optional<Student> optionalStudent = Optional.of(exampleStudent);
        when(mockStudentRepository.findById(anyLong())).thenReturn(optionalStudent);

        exampleStudentDTO.setBirthDate(givenDate);
        assertThrows(StudentAgeNotValidException.class, () -> studentService.updateStudent(exampleStudentDTO, anyLong()));

    }

    @Test
    void should_return_StudentDTO_when_deleteStudent_called() {

        when(mockStudentMapper.mapFromStudentToStudentDTO(any())).thenReturn(exampleStudentDTO);

        Optional<Student> optionalStudent = Optional.of(exampleStudent);
        when(mockStudentRepository
                .findStudentByFirstNameAndLastNameAndAddressAndGenderAndBirthDate(
                        exampleStudentDTO.getFirstName(),
                        exampleStudentDTO.getLastName(),
                        exampleStudentDTO.getAddress(),
                        exampleStudentDTO.getGender(),
                        exampleStudentDTO.getBirthDate())).thenReturn(optionalStudent);

        StudentDTO expected = mockStudentMapper.mapFromStudentToStudentDTO(exampleStudent);

        StudentDTO actual = studentService.deleteStudent(exampleStudentDTO);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void should_return_StudentDTO_when_deleteStudentById_called() {

        when(mockStudentMapper.mapFromStudentToStudentDTO(any())).thenReturn(exampleStudentDTO);

        Optional<Student> optionalStudent = Optional.of(exampleStudent);
        when(mockStudentRepository.findById(anyLong())).thenReturn(optionalStudent);

        StudentDTO expected = mockStudentMapper.mapFromStudentToStudentDTO(exampleStudent);

        StudentDTO actual = studentService.deleteStudentById(3L);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void should_return_StudentSet_when_findAllCourseStudentsById_called() {

        Optional<Student> optionalStudent = Optional.of(exampleStudent);
        when(mockStudentRepository.findById(anyLong())).thenReturn(optionalStudent);

        List<Long> myList = Arrays.asList(1L, 2L, 3L);
        Set<Student> result = studentService.findAllCourseStudentsById(myList);

        assertAll(
                () -> assertNotNull(result)
        );

    }

    @Test
    void should_return_LongList_when_findAllCourseStudentIdsByList_called() {

        Student student = new Student();
        Set<Student> students = new HashSet<>();
        students.add(student);

        List<Long> result = studentService.findAllCourseStudentIdsByList(students);

        assertAll(
                () -> assertNotNull(result)
        );

    }

    static Stream<LocalDate> dateProvider() {
        return Stream.of(LocalDate.now().minusYears(17), LocalDate.now().minusYears(41));
    }

}