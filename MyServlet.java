/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibm.bluemix.hack.todo;

/**
 *
 * @author wilip
 */
import static com.ibm.bluemix.hack.todo.TodoDao.getDataSource;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ibm.bluemix.hack.util.VcapServicesHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.tomcat.jdbc.pool.DataSource;

@WebServlet("/geoloc")
public class MyServlet extends HttpServlet {

    static private DataSource _datasource = null;
    static private final String _selectSql = "SELECT * FROM todos ORDER BY id DESC limit 100";
    

    private static final long serialVersionUID = 1186968436292476469L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//	@SuppressWarnings("unchecked")
//	public JSONObject analyzeImage(byte[] buf) throws IOException {
//		
//		JSONObject imageProcessingResults = new JSONObject();
//		
//		JSONObject creds = VcapServicesHelper.getCredentials("watson_vision_combined", null);
//
//		String baseUrl = creds.get("url").toString();
//		String apiKey = creds.get("api_key").toString();
//		String detectFacesUrl = baseUrl + "/v3/detect_faces?api_key=" + apiKey + "&version=2016-05-20";
//		String classifyUrl = baseUrl + "/v3/classify?api_key=" + apiKey + "&version=2016-05-20";
//
//		OkHttpClient client = new OkHttpClient();
//
//		RequestBody requestBody = new MultipartBody.Builder()
//				.setType(MultipartBody.FORM)
//				.addFormDataPart("images_file", "sample.jpg", RequestBody.create(MediaType.parse("image/jpg"), buf))
//				.build();
//
//		Request request = new Request.Builder()
//				.url(detectFacesUrl)
//				.post(requestBody).build();
//
//		Response response = client.newCall(request).execute();
//		String result = response.body().string();
//		
//		JSONParser jsonParser = new JSONParser();
//		
//		try {
//			JSONObject results = (JSONObject) jsonParser.parse(result);
//			// since we only process one image at a time, let's simplfy the json 
//			// we send to the JSP.
//			JSONArray images = (JSONArray) results.get("images");
//			if( images != null && images.size()>0 ) {
//				JSONObject firstImage = (JSONObject) images.get(0);
//				JSONArray faces = (JSONArray) firstImage.get("faces");
//				imageProcessingResults.put("faces", faces);
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		// now request classification
//		request = new Request.Builder()
//				.url(classifyUrl)
//				.post(requestBody).build();
//
//		response = client.newCall(request).execute();
//		result = response.body().string();
//		try {
//			JSONObject results = (JSONObject) jsonParser.parse(result);
//			// since we only process one image at a time, let's simplfy the json 
//			// we send to the JSP.
//			JSONArray images = (JSONArray) results.get("images");
//			if( images != null && images.size()>0 ) {
//				JSONObject firstImage = (JSONObject) images.get(0);
//				JSONArray classifiers = (JSONArray) firstImage.get("classifiers");
//				imageProcessingResults.put("classifiers", classifiers);
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		
//
//		return imageProcessingResults;
//	}
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        DataSource datasource = getDataSource();
        Connection conn = null;

        try {
            conn = datasource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(_selectSql);
            while (results.next()) {

                int id = results.getInt("id");
                Timestamp created = results.getTimestamp("time");
                String todoVal = results.getString("todo");

                Todo todo = new Todo();
                todo.setId(id);
                todo.setCreated(created);
                todo.setTodo(todoVal);

                todos.add(todo);

            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return todos;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part file = request.getPart("image_file");
        InputStream is = file.getInputStream();
        byte[] buf = IOUtils.toByteArray(is);
        //JSONObject results = analyzeImage(buf);

        //request.setAttribute("results", results);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/image.jsp");
        dispatcher.forward(request, response);

    }

}
