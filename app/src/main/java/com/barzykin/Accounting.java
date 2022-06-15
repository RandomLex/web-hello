package com.barzykin;

import com.barzykin.logic.Statistic;
import com.barzykin.model.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.barzykin.utils.ApplicationUtils.getTeachers;

@WebServlet(urlPatterns = "/average-salary")
public class Accounting extends AbstractHtmlUtf8Servlet {

    private Statistic statistic;

    @Override
    public void init() throws ServletException {
        statistic = new Statistic();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        include(req, resp);
        PrintWriter writer = resp.getWriter();

        List<Teacher> teachers = getTeachers(req);
        writer.write("<h3>Средняя зарплата для следующих преподавателей = " + averageSalary(teachers) + "</h2>");
        teachers.forEach(teacher -> writer.write(teacher + "<p>"));
    }


    private BigDecimal averageSalary(List<Teacher> teachers) {
        List<Integer> salaries = teachers.stream()
                .map(Teacher::getSalary)
                .collect(Collectors.toList());
        return statistic.average(salaries);
    }

}
