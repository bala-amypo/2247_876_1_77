// package com.example.demo.servlet;

// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.annotation.WebServlet;
// import java.io.IOException;

// @WebServlet(urlPatterns = "/health")
// public class HealthServlet extends HttpServlet {

//     @Override
//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//         resp.setStatus(HttpServletResponse.SC_OK);
//         resp.getWriter().write("OK");
//     }
// }
package com.example.demo.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/health")
public class HealthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }
}
