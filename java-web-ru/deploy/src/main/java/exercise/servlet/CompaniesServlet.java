package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        PrintWriter p = response.getWriter();

        List<String> listOfCompanies = Data.getCompanies();

        if (!(request.getQueryString() == null)
                 && request.getQueryString().contains("search")) {

            String s = request.getParameter("search");

            String newListOfCompanies = listOfCompanies.stream()
                    .filter(string -> string.contains(s))
                    .collect(Collectors.joining("\n"))
                    .trim();
            String res = newListOfCompanies.length() > 0 ? newListOfCompanies: "Companies not found";
            p.println(res.trim());
            
        } else {
            p.println(String.join("\n", listOfCompanies).trim());
        }
    }
}

