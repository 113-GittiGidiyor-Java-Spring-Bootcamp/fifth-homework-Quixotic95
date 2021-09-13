package dev.patika.quixotic95.schoolmanagementsystem.service;

import dev.patika.quixotic95.schoolmanagementsystem.dto.CourseDTO;
import dev.patika.quixotic95.schoolmanagementsystem.entity.Course;
import dev.patika.quixotic95.schoolmanagementsystem.mapper.CourseMapper;
import dev.patika.quixotic95.schoolmanagementsystem.repository.CourseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    CourseRepository mockCourseRepository;

    @Mock
    CourseMapper mockCourseMapper;

    @InjectMocks
    CourseService courseService;

    Course exampleCourse;
    CourseDTO exampleCourseDTO;
    List<Course> exampleCourseList;

    @BeforeEach
    void initialize() {
        exampleCourse = new Course();
        exampleCourseDTO = new CourseDTO();
        exampleCourseList = new ArrayList<>();
    }

    @AfterEach
    void nullifier() {
        exampleCourse = null;
        exampleCourseDTO = null;
        exampleCourseList = null;
    }

    @Test
    void should_return_List_of_CourseDTO_when_findAllCourses_called() {

        when(mockCourseRepository.findAll()).thenReturn(exampleCourseList);
        List<CourseDTO> expected = exampleCourseList
                .stream()
                .map(mockCourseMapper::mapFromCourseToCourseDTO)
                .collect(Collectors.toList());

        List<CourseDTO> actual = courseService.findAllCourses();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void should_return_CourseDTO_when_findCourseById_called() {

        when(mockCourseMapper.mapFromCourseToCourseDTO(any())).thenReturn(exampleCourseDTO);

        Optional<Course> optionalCourse = Optional.of(exampleCourse);
        when(mockCourseRepository.findById(anyLong())).thenReturn(optionalCourse);
        CourseDTO expected = mockCourseMapper.mapFromCourseToCourseDTO(exampleCourse);

        CourseDTO actual = courseService.findCourseById(5L);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void should_throw_EntityNotFoundException_when_findCourseById_called_if_Course_is_not_in_database() {

        assertThrows(EntityNotFoundException.class, () -> courseService.findCourseById(anyLong()));

    }

    @Test
    void should_return_CourseDTO_when_saveCourse_called() {

        when(mockCourseMapper.mapFromCourseToCourseDTO(any())).thenReturn(exampleCourseDTO);

        when(mockCourseRepository.save(any())).thenReturn(exampleCourse);
        CourseDTO expected = mockCourseMapper.mapFromCourseToCourseDTO(exampleCourse);

        exampleCourseDTO.setStudentIds(Arrays.asList());
        CourseDTO actual = courseService.saveCourse(exampleCourseDTO);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void should_return_CourseDTO_when_updateCourse_called() {

        when(mockCourseMapper.mapFromCourseToCourseDTO(any())).thenReturn(exampleCourseDTO);
        when(mockCourseMapper.mapFromCourseDTOtoCourse(any())).thenReturn(exampleCourse);

        Optional<Course> optionalCourse = Optional.of(exampleCourse);
        when(mockCourseRepository.findById(anyLong())).thenReturn(optionalCourse);

        when(mockCourseRepository.save(any())).thenReturn(exampleCourse);
        CourseDTO expected = mockCourseMapper.mapFromCourseToCourseDTO(exampleCourse);

        exampleCourseDTO.setStudentIds(Arrays.asList());
        CourseDTO actual = courseService.updateCourse(exampleCourseDTO, anyLong());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void should_return_CourseDTO_when_deleteCourseById_called() {

        when(mockCourseMapper.mapFromCourseToCourseDTO(any())).thenReturn(exampleCourseDTO);

        Optional<Course> optionalCourse = Optional.of(exampleCourse);
        when(mockCourseRepository.findById(anyLong())).thenReturn(optionalCourse);

        CourseDTO expected = mockCourseMapper.mapFromCourseToCourseDTO(exampleCourse);

        CourseDTO actual = courseService.deleteCourseById(3L);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }
}