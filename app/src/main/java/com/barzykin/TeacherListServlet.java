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

import static com.barzykin.utils.ApplicationUtils.getTeachers;

@WebServlet(urlPatterns = "/teachers")
public class TeacherListServlet extends AbstractHtmlUtf8Servlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        include(req, resp);
        PrintWriter writer = resp.getWriter();
        List<Teacher> teachers = getTeachers(req);
        printInfoAboutAllTeacher(writer, teachers);
    }

    private void printInfoAboutAllTeacher(PrintWriter writer, List<Teacher> teachers) {
        teachers
                .forEach(teacher -> writer.write(teacher.toString() + "<p>"));
    }
}
