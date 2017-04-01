import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


@WebServlet("/heatmap")
public class Heatmap extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String relativeWebPath = ".\\images\\heatmap.jpg";
		String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
		File f = new File(absoluteDiskPath);
		
		File dir = new File(getServletContext().getRealPath("."));
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		    } 
		}
		
		System.out.println(f.getAbsolutePath());
		response.setContentType("image/jpeg");
        BufferedImage image;
        image = ImageIO.read(f);
        ImageIO.write(image, "jpeg", response.getOutputStream());
	}
	
	

	
}