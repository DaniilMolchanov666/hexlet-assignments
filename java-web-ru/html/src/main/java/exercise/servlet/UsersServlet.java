package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
        } else {
            String[] pathParts = pathInfo.split("/");
            String id = ArrayUtils.get(pathParts, 1, "");
            showUser(request, response, id);
        }
    }

    public List<HashMap<String, String>> getUsers() throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        List<HashMap<String, String>> map =
                mapper.readValue(new File("./src/main/resources/users.json"), new TypeReference<>() {});
        return map;
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        ObjectWriter w = mapper.writerWithDefaultPrettyPrinter();
        PrintWriter p = response.getWriter();
        StringBuilder s = new StringBuilder();

        String start = "<html> "
                + "<body>"
                    + "<table>";
        String end = "</table> "
            + "</body> "
                + "</html>";

        s.append(start);

        for (HashMap<String, String> a: getUsers()) {
            s.append("<tr>");

            HashMap<String, String> m = new HashMap<>();

            String fullName = a.get("firstName") + " " + a.get("lastName");

            s.append("<td> Full Name: ");
            s.append(String.format("<a href=\"/users/%s\"> %s </a></td", a.get("id"), fullName));
            s.append("<td> id: " + a.get("id") + "</td>");
            s.append("</tr>");
        }
        response.setContentType("text/html;charset=UTF-8");
        s.append(end);
        p.println(s);
    }

    private void showUser(HttpServletRequest request,
                        HttpServletResponse response,
                        String id)
                throws IOException {

        PrintWriter p = response.getWriter();
        StringBuilder s = new StringBuilder();
        getUsers()
                .stream()
                .filter(m -> m.containsValue(id))
                .flatMap(e->e.entrySet().stream())
                .forEach(a-> s.append(a).append("\n"));
        response.setContentType("text/html;charset=UTF-8");
        p.println(s);
    }
}
