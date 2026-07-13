package com.shivani.seleniumdesignframework.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import java.util.HashMap;
import java.util.List;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsondataToMap(String filePath) throws IOException {
		
		//read json to string
		String jsonContent= FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//string to hashmap using ObjectMapper (Jackson Databind) --> used to read and write Json
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});	//read jsonContent, convert it into given structure --> here it converts into a list of hashmap.
		
		return data;
		//{map,map}
	}
}
