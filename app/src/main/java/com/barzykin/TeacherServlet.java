package com.barzykin;

import com.barzykin.model.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import static com.barzykin.Constants.AGE;
import static com.barzykin.Constants.NAME;
import static com.barzykin.Constants.SALARY;
import static com.barzykin.utils.ApplicationUtils.getTeachers;

@WebServlet(urlPatterns = "/teacher")
public class TeacherServlet extends AbstractHtmlUtf8Servlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        include(req, resp);
        PrintWriter writer = resp.getWriter();

        List<Teacher> teachers = getTeachers(req);
        String name = req.getParameter(NAME);

        if (name != null) {
            printInfoAboutOneTeacher(writer, name, findTeacherByName(teachers, name));
        } else {
            writer.write("Не указно имя преподавателя");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String name = req.getParameter(NAME);
        int age = Integer.parseInt(req.getParameter(AGE));
        int salary = Integer.parseInt(req.getParameter(SALARY));

        Teacher teacher = new Teacher(name, age, salary);
        List<Teacher> teachers = getTeachers(req);
        teachers.add(teacher);

//        resp.sendRedirect(req.getServerName() + ":" + req.getServerPort() + "/first");
//        resp.sendRedirect("localhost:8080/first");
//        resp.sendRedirect("gmail.com");
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/teachers");
        dispatcher.forward(req, resp);
    }

    private Optional<Teacher> findTeacherByName(List<Teacher> teachers, String name) {
        return teachers.stream()
                .filter(teacher -> name.equals(teacher.getName()))
                .findAny();
    }

    private void printInfoAboutOneTeacher(PrintWriter writer, String name, Optional<Teacher> teacherOptional) {
        if (teacherOptional.isPresent()) {
            writer.write(teacherOptional.get().toString());
        } else {
            writer.write("Teacher with name '" + name + "' is not found.");
        }
    }
}
