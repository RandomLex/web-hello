package com.barzykin;

import com.barzykin.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import static com.barzykin.Constants.NAME;
import static com.barzykin.utils.ApplicationUtils.getTeachers;

@WebServlet(urlPatterns = "/teacher")
public class TeacherServlet extends AbstractHtmlUtf8Servlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        PrintWriter writer = resp.getWriter();

        List<Teacher> teachers = getTeachers(req);
        String name = req.getParameter(NAME);
        if (name == null) {
            printInfoAboutAllTeacher(writer, teachers);
        } else {
            printInfoAboutOneTeacher(writer, name, findTeacherByName(teachers, name));
        }
    }

    private Optional<Teacher> findTeacherByName(List<Teacher> teachers, String name) {
        return teachers.stream()
                .filter(teacher -> name.equals(teacher.getName()))
                .findAny();
    }

    private void printInfoAboutAllTeacher(PrintWriter writer, List<Teacher> teachers) {
        teachers
                .forEach(teacher -> writer.write(teacher.toString() + "<p>"));
    }

    private void printInfoAboutOneTeacher(PrintWriter writer, String name, Optional<Teacher> teacherOptional) {
        if (teacherOptional.isPresent()) {
            writer.write(teacherOptional.get().toString());
        } else {
            writer.write("Teacher with name '" + name + "' is not found.");
        }
    }
}
