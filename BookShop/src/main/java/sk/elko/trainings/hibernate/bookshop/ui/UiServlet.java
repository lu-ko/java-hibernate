package sk.elko.trainings.hibernate.bookshop.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class UiServlet
 */
public class UiServlet extends HttpServlet {
    private static final long serialVersionUID = -7857799217322450456L;

    private static final Log log = LogFactory.getLog(UiServlet.class);

    /**
     * Default constructor.
     */
    public UiServlet() {
        log.info("UiServlet - BookShop initialized.");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet - ");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println("BookShop: doGet");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doPost - ");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println("BookShop: doPost");
    }

}
