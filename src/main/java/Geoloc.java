import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/geoloc")
public class Geoloc extends HttpServlet {
	static final String user = "WLIPPOLI_codefest", 
						pass = "zhUFsTWI29uy",
						db_url = "asdf";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print("Lat = " + request.getParameter("latitude"));
        response.getWriter().print("Long = " + request.getParameter("longitude"));
        File file = new File("Hello1.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("hey");
        
	}

	
}