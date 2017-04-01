import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.geometry.Point2D;

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
	
	// lat & long
	public static final double[] DAVIS_CENTER = {44.4757914,-73.1964633};
	public static final double[] MUDDY_WATERS = {44.4761728,-73.2119901};
	public static final double[] FLETCHER_LIB = {44.4768642,-73.210435};
	public static final double[] BAILEY_HOWE = {44.4772649,-73.1967532};
	public static final double[] WATERMAN = {44.478283,-73.201157};
	public static final double RADIUS = 0.000425383239;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
        response.setContentType("text/html");
        response.getWriter().print("Lat = " + request.getParameter("latitude"));
        response.getWriter().print("Long = " + request.getParameter("longitude"));
        File file = new File("test.txt");
        FileWriter writer = new FileWriter(file);
        PrintWriter w = new PrintWriter(writer);
        
        w.write("hey");
        
	}

	
}