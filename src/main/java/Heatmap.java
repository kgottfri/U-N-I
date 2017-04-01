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
		int squareWidth = 50;
		int coloredSquareWidth = 40;
		
		colorSquare(image, loc.xCord-squareWidth/2, loc.yCord-squareWidth/2, squareWidth, Color.black);
		colorSquare(image, loc.xCord-coloredSquareWidth/2, loc.yCord-coloredSquareWidth/2, coloredSquareWidth, loc.fill);
		addText(image, loc.xCord, loc.yCord-squareWidth/2, loc.name);
	}
	
	public void addText(BufferedImage image,int xCord,int yCord,String text)
	{
		Graphics2D g = image.createGraphics();
		
		int width = g.getFontMetrics(font).stringWidth(text);
		
		g.setColor(Color.black);
		g.fillRect(xCord-width/2-6, yCord-font.getSize()-6, width+12, font.getSize()+11);
		
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(text, xCord-width/2, yCord-3);
	}
	
	public void colorSquare(BufferedImage image,int xCord,int yCord,int width,Color color)
	{
		Graphics2D g = image.createGraphics();
		g.setColor(color);
		g.fillRect(xCord, yCord, width, width);
	}
	
	

	
}