package com.ibm.bluemix.hack.test;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Servlet implementation class ImageEvaluator
 */
@WebServlet("/eval")
public class ImageEvaluator extends HttpServlet {

	private static final long serialVersionUID = 1186968436292476469L;
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageEvaluator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String analyzeImage(byte[] buf) throws IOException {
		
		JSONObject creds = VcapServicesHelper.getCredentials("watson_vision_combined", null);

		String baseUrl = creds.get("url").toString();
		String apiKey = creds.get("api_key").toString();
		String detectFacesUrl = baseUrl + "/v3/detect_faces?api_key=" + apiKey + "&version=2016-05-20";

		OkHttpClient client = new OkHttpClient();

		RequestBody requestBody = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("images_file", "sample.jpg", RequestBody.create(MediaType.parse("image/jpg"), buf))
				.build();

		Request request = new Request.Builder()
				.url(detectFacesUrl)
				.post(requestBody).build();

		Response response = client.newCall(request).execute();
		String result = response.body().string();
		System.out.println(result);

		return result;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("image.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part file = request.getPart("image_file");
		InputStream is = file.getInputStream();
		byte[] buf = IOUtils.toByteArray(is);
		String results = analyzeImage(buf);
		
		response.getWriter().append(results);

	}

}
