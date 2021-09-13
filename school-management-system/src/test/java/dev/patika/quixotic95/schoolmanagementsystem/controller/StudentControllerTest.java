package dev.patika.quixotic95.schoolmanagementsystem.controller;

import dev.patika.quixotic95.schoolmanagementsystem.dto.StudentDTO;
import dev.patika.quixotic95.schoolmanagementsystem.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    StudentService mockStudentService;

    @InjectMocks
    StudentController studentController;

    StudentDTO exampleStudentDTO;
    List<StudentDTO> exampleStudentDTOList;

    @BeforeEach
    void initialize() {
        exampleStudentDTO = new StudentDTO();
        exampleStudentDTOList = new ArrayList<>();
    }

    @AfterEach
    void nullifier() {
        exampleStudentDTO = null;
        exampleStudentDTOList = null;
    }

    @Test
    void should_return_ResponseEntity_of_List_of_StudentDTO_when_findAllStudent_called() {

        when(mockStudentService.findAllStudents()).thenReturn(exampleStudentDTOList);

        ResponseEntity<?> responseEntity = studentController.findAllStudents();
        List<StudentDTO> actual = (List<StudentDTO>) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleStudentDTOList, actual)
        );

    }

    @Test
    void should_return_ResponseEntity_of_StudentDTO_when_findStudentById_called() {

        when(mockStudentService.findStudentById(anyLong())).thenReturn(exampleStudentDTO);

        ResponseEntity<?> responseEntity = studentController.findStudentById(anyLong());
        StudentDTO actual = (StudentDTO) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleStudentDTO, actual)
        );

    }

    @Test
    void should_return_ResponseEntity_of_StudentDTO_when_saveStudent_called() {

        when(mockStudentService.saveStudent(any())).thenReturn(exampleStudentDTO);

        ResponseEntity<?> responseEntity = studentController.saveStudent(any());
        StudentDTO actual = (StudentDTO) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleStudentDTO, actual)
        );

    }

    @Test
    void should_return_ResponseEntity_of_StudentDTO_when_updateStudent_called() {

        when(mockStudentService.updateStudent(any(), anyLong())).thenReturn(exampleStudentDTO);

        ResponseEntity<?> responseEntity = studentController.updateStudent(anyLong(), any());
        StudentDTO actual = (StudentDTO) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleStudentDTO, actual)
        );

    }

    @Test
    void should_return_ResponseEntity_of_StudentDTO_when_deleteStudent_called() {

        when(mockStudentService.deleteStudent(any())).thenReturn(exampleStudentDTO);

        ResponseEntity<?> responseEntity = studentController.deleteStudent(any());
        StudentDTO actual = (StudentDTO) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleStudentDTO, actual)
        );

    }

    @Test
    void should_return_ResponseEntity_of_StudentDTO_when_deleteStudentById_called() {

        when(mockStudentService.deleteStudentById(anyLong())).thenReturn(exampleStudentDTO);

        ResponseEntity<?> responseEntity = studentController.deleteStudentById(anyLong());
        StudentDTO actual = (StudentDTO) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleStudentDTO, actual)
        );

    }
}