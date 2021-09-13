package dev.patika.quixotic95.schoolmanagementsystem.controller;

import dev.patika.quixotic95.schoolmanagementsystem.dto.CourseDTO;
import dev.patika.quixotic95.schoolmanagementsystem.service.CourseService;
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
class CourseControllerTest {

    @Mock
    CourseService mockCourseService;

    @InjectMocks
    CourseController courseController;

    CourseDTO exampleCourseDTO;
    List<CourseDTO> exampleCourseDTOList;

    @BeforeEach
    void initialize() {
        exampleCourseDTO = new CourseDTO();
        exampleCourseDTOList = new ArrayList<>();
    }

    @AfterEach
    void nullifier() {
        exampleCourseDTO = null;
        exampleCourseDTOList = null;
    }

    @Test
    void should_return_ResponseEntity_of_List_of_CourseDTO_when_findAllCourse_called() {

        when(mockCourseService.findAllCourses()).thenReturn(exampleCourseDTOList);

        ResponseEntity<?> responseEntity = courseController.findAllCourses();
        List<CourseDTO> actual = (List<CourseDTO>) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleCourseDTOList, actual)
        );

    }

    @Test
    void should_return_ResponseEntity_of_CourseDTO_when_findCourseById_called() {

        when(mockCourseService.findCourseById(anyLong())).thenReturn(exampleCourseDTO);

        ResponseEntity<?> responseEntity = courseController.findCourseById(anyLong());
        CourseDTO actual = (CourseDTO) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleCourseDTO, actual)
        );

    }

    @Test
    void should_return_ResponseEntity_of_CourseDTO_when_saveCourse_called() {

        when(mockCourseService.saveCourse(any())).thenReturn(exampleCourseDTO);

        ResponseEntity<?> responseEntity = courseController.saveCourse(any());
        CourseDTO actual = (CourseDTO) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleCourseDTO, actual)
        );

    }

    @Test
    void should_return_ResponseEntity_of_CourseDTO_when_updateCourse_called() {

        when(mockCourseService.updateCourse(any(), anyLong())).thenReturn(exampleCourseDTO);

        ResponseEntity<?> responseEntity = courseController.updateCourse(anyLong(), any());
        CourseDTO actual = (CourseDTO) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleCourseDTO, actual)
        );

    }

    @Test
    void should_return_ResponseEntity_of_CourseDTO_when_deleteCourse_called() {

        when(mockCourseService.deleteCourse(any())).thenReturn(exampleCourseDTO);

        ResponseEntity<?> responseEntity = courseController.deleteCourse(any());
        CourseDTO actual = (CourseDTO) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleCourseDTO, actual)
        );

    }

    @Test
    void should_return_ResponseEntity_of_CourseDTO_when_deleteCourseById_called() {

        when(mockCourseService.deleteCourseById(anyLong())).thenReturn(exampleCourseDTO);

        ResponseEntity<?> responseEntity = courseController.deleteCourseById(anyLong());
        CourseDTO actual = (CourseDTO) responseEntity.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(exampleCourseDTO, actual)
        );

    }
}