package com.barzykin.db;

import com.barzykin.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase {
    private List<Teacher> teachers;

    public InMemoryDatabase() {
        teachers = new ArrayList<>();
        teachers.add(new Teacher("Alex", 45, 1000));
        teachers.add(new Teacher("Ivan", 35, 1300));
        teachers.add(new Teacher("Viktor", 25, 600));
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}
