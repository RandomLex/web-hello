package com.barzykin.utils;

import com.barzykin.db.InMemoryDatabase;
import com.barzykin.model.Teacher;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.barzykin.Constants.DB;

public final class ApplicationUtils {
    private ApplicationUtils() {
    }

    public static InMemoryDatabase getDb(HttpServletRequest req) {
        ServletContext servletContext = req.getServletContext();
        return (InMemoryDatabase) servletContext.getAttribute(DB);
    }

    public static List<Teacher> getTeachers(HttpServletRequest req) {
        return getDb(req).getTeachers();
    }

    public static void initTeachersToContext(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
        servletContext.setAttribute(DB, inMemoryDatabase);
    }
}
