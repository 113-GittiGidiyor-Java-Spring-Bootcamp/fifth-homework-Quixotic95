package dev.patika.quixotic95.schoolmanagementsystem.service;

import dev.patika.quixotic95.schoolmanagementsystem.dto.CourseDTO;
import dev.patika.quixotic95.schoolmanagementsystem.entity.Course;
import dev.patika.quixotic95.schoolmanagementsystem.exception.CourseIsAlreadyExistException;
import dev.patika.quixotic95.schoolmanagementsystem.exception.StudentNumberForOneCourseExceededException;
import dev.patika.quixotic95.schoolmanagementsystem.mapper.CourseMapper;
import dev.patika.quixotic95.schoolmanagementsystem.repository.CourseRepository;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

        exampleCourseDTO.setStudentIds(Collections.emptyList());
        CourseDTO actual = courseService.saveCourse(exampleCourseDTO);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void should_throw_CourseIsAlreadyExistException_when_saveCourse_called_if_a_Course_already_exists_in_database_with_Same_courseCode() {

        Optional<Course> optionalCourse = Optional.of(exampleCourse);
        when(mockCourseRepository.findCourseByCourseCodeAndIdIsNot(anyString(), anyLong())).thenReturn(optionalCourse);

        exampleCourseDTO.setStudentIds(Collections.emptyList());
        exampleCourseDTO.setCourseCode("TEST101");

        assertThrows(CourseIsAlreadyExistException.class, () -> courseService.saveCourse(exampleCourseDTO));

    }

    @ParameterizedTest
    @MethodSource("studentIdProvider")
    void should_throw_StudentNumberForOneCourseExceededException_when_saveCourse_called_if_a_Course_has_more_than_20_students(List<Long> givenIdList) {

        exampleCourseDTO.setStudentIds(givenIdList);
        assertThrows(StudentNumberForOneCourseExceededException.class, () -> courseService.saveCourse(exampleCourseDTO));

    }

    @Test
    void should_return_CourseDTO_when_updateCourse_called() {

        when(mockCourseMapper.mapFromCourseToCourseDTO(any())).thenReturn(exampleCourseDTO);
        when(mockCourseMapper.mapFromCourseDTOtoCourse(any())).thenReturn(exampleCourse);

        Optional<Course> optionalCourse = Optional.of(exampleCourse);
        when(mockCourseRepository.findById(anyLong())).thenReturn(optionalCourse);

        when(mockCourseRepository.save(any())).thenReturn(exampleCourse);
        CourseDTO expected = mockCourseMapper.mapFromCourseToCourseDTO(exampleCourse);

        exampleCourseDTO.setStudentIds(Collections.emptyList());
        CourseDTO actual = courseService.updateCourse(exampleCourseDTO, anyLong());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    void should_return_CourseDTO_when_deleteCourse_called() {

        when(mockCourseMapper.mapFromCourseToCourseDTO(any())).thenReturn(exampleCourseDTO);

        Optional<Course> optionalCourse = Optional.of(exampleCourse);
        when(mockCourseRepository.findCourseByCourseCode(any())).thenReturn(optionalCourse);

        CourseDTO expected = mockCourseMapper.mapFromCourseToCourseDTO(exampleCourse);

        CourseDTO actual = courseService.deleteCourse(exampleCourseDTO);

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

    static Stream<List<Long>> studentIdProvider() {

        List<Long> studentIds = new ArrayList<>();
        long minimum = 21;

        for (long i = 1; i <= minimum; i++) {
            studentIds.add(i);
        }
        return Stream.of(studentIds);
    }
}