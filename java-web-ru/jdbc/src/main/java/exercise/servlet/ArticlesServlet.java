package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                try {
                    showArticles(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    showArticle(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException, SQLException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        PreparedStatement s = connection.prepareStatement("SELECT * FROM ARTICLES ORDER BY ID LIMIT 10 OFFSET ?");

        int page = request.getParameter("page") == null
                ? 0: Integer.parseInt(request.getParameter("page")) - 1;

        s.setInt(1, page * 10);

        ResultSet r = s.executeQuery();

        List<Map<String, String>> list = new ArrayList<>();

        try {
            while(r.next()) {
                HashMap<String, String> m = new HashMap<>();
                m.put("ID", r.getString("id"));
                m.put("TITLE", r.getString("title"));
                m.put("BODY", r.getString("body"));
                list.add(m);
            }
        } catch (SQLException e) {
            response.sendError(404);
        }

        request.setAttribute("list", list);
        request.setAttribute("page", page + 1);
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException, SQLException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        PreparedStatement s = connection.prepareStatement("SELECT * FROM ARTICLES WHERE ID = ?");

        int id = Integer.parseInt(getAction(request));
        s.setInt(1, id);
        ResultSet r = s.executeQuery();

        Map<String, Object> map = new HashMap<>();

        try {
            if (getData(request, id) == null) {
                throw new SQLException();
            }
            map = getData(request, id);

        } catch (SQLException e) {
            response.sendError(404);
        }

        request.setAttribute("map", map);
        TemplateEngineUtil.render("articles/show.html", request, response);
    }

    private Map<String, Object> getData(HttpServletRequest request, int id) throws SQLException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        PreparedStatement s = connection.prepareStatement("SELECT * FROM ARTICLES WHERE ID = ?");
        s.setInt(1, id);
        ResultSet r = s.executeQuery();

        if(!r.first()) {
            return null;
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("ID", id);
        map.put("TITLE", r.getString("title"));
        map.put("BODY", r.getString("body"));
        return map;
    }
}
