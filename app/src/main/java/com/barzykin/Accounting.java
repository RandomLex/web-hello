package com.barzykin;

import com.barzykin.logic.Statistic;
import com.barzykin.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/average-salary")
public class Accounting extends HttpServlet {

    private List<Teacher> teachers;

    private Statistic statistic;

    @Override
    public void init() throws ServletException {
        statistic = new Statistic();
        initModel();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<h3>Средняя зарплата для следующих преподавателей = " + averageSalary(teachers) + "</h2>");
        teachers.forEach(teacher -> writer.write(teacher + "<p>"));
    }

    private BigDecimal averageSalary(List<Teacher> teachers) {
        List<Integer> salaries = teachers.stream()
                .map(Teacher::getSalary)
                .collect(Collectors.toList());
        return statistic.average(salaries);
    }

    private void initModel() {
        teachers = new ArrayList<>();
        teachers.add(new Teacher("Alex", 45, 1000));
        teachers.add(new Teacher("Ivan", 35, 1300));
        teachers.add(new Teacher("Viktor", 25, 600));
    }


}
