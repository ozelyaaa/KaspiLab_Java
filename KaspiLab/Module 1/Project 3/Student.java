package kz.lab.module1.advanced;

import java.util.Objects;

public final class Student {

    private final int id;
    private final String name;
    private final String phone;

    public Student(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int id() { return id; }
    public String name() { return name; }
    public String phone() { return phone; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Student student = (Student) obj;
        return id == student.id && name.equals(student.name) && phone.equals(student.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }

    @Override
    public String toString() {
        return String.format("Student[id=%d, name=%s, phone=%s]", id, name, phone);
    }
}
