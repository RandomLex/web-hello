package com.barzykin.model;

import java.util.Objects;

public class Teacher extends Person {
    private int salary;

    public Teacher() {

    }

    public Teacher(String name, int age, int salary) {
        super(name, age);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", salary=" + salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return getSalary() == teacher.getSalary();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSalary());
    }
}
