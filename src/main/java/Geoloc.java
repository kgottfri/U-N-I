import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
	
	public static final double ACCEPTABLE_DISTANCE = 0.05;
	// lat & long
	public static final double[] DAVIS_CENTER = {44.4757914,-73.1964633};
	public static final double[] MUDDY_WATERS = {44.4761728,-73.2119901};
	public static final double[] FLETCHER_LIB = {44.4768642,-73.210435};
	public static final double[] BAILEY_HOWE = {44.4772649,-73.1967532};
	public static final double[] WATERMAN = {44.478283,-73.201157};
	public static final double RADIUS = 0.000425383239;
	
	private double userLat;
	private double userLong;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
        response.setContentType("text/html");
        response.getWriter().print("Lat = " + request.getParameter("latitude"));
        response.getWriter().print("Long = " + request.getParameter("longitude"));
        
        userLat = Double.parseDouble(request.getParameter("latitude"));
        userLong = Double.parseDouble(request.getParameter("longitude"));
        if (inRange(WATERMAN, response)){
        	response.getWriter().print("You're at waterman!   ");
        }
        else{
        	response.getWriter().print("You're not at waterman!   ");
        }
        File file = new File("test.txt");
        FileWriter writer = new FileWriter(file);
        PrintWriter w = new PrintWriter(writer);
        
        w.write("hey");
        
	}
	
	public boolean inRange(double [] location, HttpServletResponse response) throws IOException{
		double lat_dif = location[0] - userLat;
		double long_dif = location[1] - userLong;
		double distance = Math.sqrt(Math.pow(lat_dif, 2) + (Math.pow(long_dif, 2)));
		response.getWriter().print("Distance: " + distance + "   ");
		return (distance < RADIUS);
	}

	
}