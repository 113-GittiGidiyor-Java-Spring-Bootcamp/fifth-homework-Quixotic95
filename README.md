# Homework 5 - School Management System

## Schema

![hm05](https://user-images.githubusercontent.com/45206582/132606840-bcc89ab7-37f4-4bbd-a950-227b838b0b3c.PNG)

* Student
* Course
* Instructor
    * Visiting Researcher
    * Permanent Instructor

## Summary

A system with Students, Courses and 2 types of Instructors all related to each other.

## Details

#### All date formats for endpoints are "YYYY-MM-DD" without quotation marks.

1. User can update the salary of an Instructor by sending id and percentage.
* `percentage` can be positive or negative. positive adds to salary, negative subs the salary.

2. All salary update interactions will be logged at database with the information of
* `client ip`
* `client request url`
* `client session id`
* `instructor id`
* `salary begore updating`
* `salary after updating`
* `percentage of change`
* `request timestamp`

3. An endpoint will serve for querying and listing salary update logs
* via instructor id
* or via instructor id and date

4. Unit tests will be written for all Controller and Service classes.

## Used Technologies

* IntelliJ IDEA `2021.2`
    * Spring Initializr
        * Java `8`
        * Maven Project
        * Spring Boot `2.5.4`
        * Dependencies
        * Plugins

## Usage
1. Run the program.
2. Go to [SwaggerUI](http://localhost:8080/swagger-ui.html) from your browser.
3. Use the controllers for CRUD operations.
4. For exception-logger-controller, date must be formatted as **'YYYY-MM-DD'** and type should be like **'404 NOT_FOUND'** or **'404'** or **'NOT_FOUND'**


## License

[MIT](https://choosealicense.com/licenses/mit/)