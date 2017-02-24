package com.ibm.bluemix.hack.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VcapServicesHelper {
	
	static private JSONObject _vcapServices = null;
	
	static public JSONObject getCredentials(String serviceName, String instanceName) {

		String vcapServicesStr = System.getenv("VCAP_SERVICES");
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(vcapServicesStr);
			JSONArray services = (JSONArray) obj.get(serviceName);
			if( services != null && services.size()>0 ) {
				
				if( instanceName == null ) {
					// grab the first one in the array
					JSONObject service = (JSONObject) services.get(0);
					JSONObject creds = (JSONObject) service.get("credentials");
					return creds;
				} else {
					for(int i=0;i<services.size(); i++) {
						JSONObject service = (JSONObject) services.get(0);
						String name = (String) service.get("name");
						if(instanceName.equals(name)) {
							JSONObject creds = (JSONObject) service.get("credentials");
							return creds;
						}
					}
				}
				
				
			} else {
				// service not bound
				System.err.println("Service " + serviceName + " not bound to this application.");
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Unable to parse VCAP_SERVICES " + e.getMessage());
		}
				
		return null;
	}

}
