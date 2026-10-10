package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;

public class CourseRepository {
    private final ArrayList<Course> courses = new ArrayList<>();

    public void add(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> findAll() {
        return courses;
    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public boolean removeById(int id) {
        return courses.removeIf(course -> course.getId() == id);
    }
}
