package com.barzykin;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import static com.barzykin.utils.ApplicationUtils.initTeachersToContext;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initTeachersToContext(config);
    }



}
