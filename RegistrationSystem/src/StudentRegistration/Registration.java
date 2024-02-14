package StudentRegistration; 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getRegisteredStudents() {
        return registeredStudents;
    }

    public void registerStudent() {
        registeredStudents++;
    }

    public void removeStudent() {
        registeredStudents--;
    }

    public boolean isFull() {
        return registeredStudents >= capacity;
    }
}

class Student {
    private String id;
    private String name;
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        course.registerStudent();
    }

    public void removeCourse(Course course) {
        registeredCourses.remove(course);
        course.removeStudent();
    }
}

public class Registration {
    private List<Course> courses;
    private List<Student> students;

    public Registration() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void registerStudent(Student student, Course course) {
        if (!course.isFull()) {
            student.registerCourse(course);
            System.out.println("Student " + student.getName() + " has been registered for course " + course.getCode());
        } else {
            System.out.println("Course " + course.getCode() + " is full. Registration failed.");
        }
    }

    public void removeCourse(Student student, Course course) {
        student.removeCourse(course);
        System.out.println("Student " + student.getName() + " has been removed from course " + course.getCode());
    }

    public void displayAvailableCourses() {
        for (Course course : courses) {
            int availableSlots = course.getCapacity() - course.getRegisteredStudents();
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + availableSlots);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Registration registrationSystem = new Registration();
        Course course1 = new Course("CSCI101", "Introduction to Computer Science", "Fundamentals of programming", 30, "Mon/Wed 9-11am");
        Course course2 = new Course("MATH101", "Calculus I", "Introduction to differential and integral calculus", 25, "Tue/Thu 1-3pm");
        registrationSystem.addCourse(course1);
        registrationSystem.addCourse(course2);

        Student student1 = new Student("S001", "Alice");
        Student student2 = new Student("S002", "Bob");

        registrationSystem.registerStudent(student1, course1);
        registrationSystem.registerStudent(student2, course2);

        registrationSystem.displayAvailableCourses();
    }
}
