import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

@WebServlet("/heatmap")
public class Heatmap extends HttpServlet {
	
	private Font font = new Font("Heatmap-Font",Font.BOLD,24);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String relativeWebPath = "./images/heatmap.jpg";
		String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
		File f = new File(absoluteDiskPath);
		
		response.setContentType("image/jpeg");
        BufferedImage image;
        image = ImageIO.read(f);
        
        //*************************************For Testing Use*******************************
        Location testLib = new Location("Fletcher Library",660,415,Color.green);
        drawLocation(image,testLib);
        
        Location testUVMLib = new Location("Biley-Howl Library",1245,345,Color.red);
        drawLocation(image,testUVMLib);
        
        Location testDavis = new Location("Davis Center",1300, 475,Color.yellow);
        drawLocation(image,testDavis);
        //***********************************************************************************
        
        ImageIO.write(image, "jpeg", response.getOutputStream());
	}
	
	public void drawLocation(BufferedImage image, Location loc)
	{
		colorSquare(image, loc.xCord, loc.yCord, 50, Color.black);
		colorSquare(image, loc.xCord+5, loc.yCord+5, 40, loc.fill);
		addText(image, loc.xCord, loc.yCord-5, loc.name);
	}
	
	public void addText(BufferedImage image,int xCord,int yCord,String text)
	{
		Graphics2D g = image.createGraphics();
		
		g.setColor(Color.black);
		g.setFont(font);
		g.drawString(text, xCord, yCord);
	}
	
	public void colorSquare(BufferedImage image,int xCord,int yCord,int width,Color color)
	{
		Graphics2D g = image.createGraphics();
		g.setColor(color);
		g.fillRect(xCord, yCord, width, width);
	}
	
	

	
}