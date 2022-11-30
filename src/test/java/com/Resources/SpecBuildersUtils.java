package com.Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuildersUtils {
	public static RequestSpecification request;
	
	public RequestSpecification RequestSpecBuild() throws IOException {
		Properties prop = loadPropertyFile();
		
		if(request==null) {
		
		PrintStream stream = new PrintStream(new FileOutputStream("log.txt"));
		 request=new RequestSpecBuilder().setBaseUri(prop.getProperty("baseurl")).setRelaxedHTTPSValidation().
				 addFilter(RequestLoggingFilter.logRequestTo(stream)).
		
				addFilter(ResponseLoggingFilter.logResponseTo(stream)). 
		addQueryParam(prop.getProperty("key")).
		setContentType(ContentType.JSON).build();
		 return request;
		}
		return request;
	}
	
	public ResponseSpecification ResponseSpecBuild() {
		ResponseSpecBuilder res = new ResponseSpecBuilder();
		res.expectContentType(ContentType.JSON).setDefaultParser(Parser.JSON).expectStatusCode(200);
		ResponseSpecification response=res.build();
		return response;
	}
	
	public static Properties loadPropertyFile() throws IOException {
		 FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream("project.properties");
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	}
	
	public String getValuesFromProp() {
		
		return null;
		
	}
}
