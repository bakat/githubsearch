package services;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PojoMapper {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static JsonFactory jsonFactory = new JsonFactory();
	
	public static <T> Object fromJson(String jsonContents, 
			Class<T> pojoClass)
		    throws JsonMappingException, JsonParseException, 
		    IOException {
		
		return objectMapper.readValue(
				jsonContents, pojoClass);
	}
	
	public static String toJson(Object pojo, Boolean prettyPrint) 
			throws JsonMappingException, JsonGenerationException, 
			IOException{
		StringWriter stringWriter = new StringWriter();
		
		JsonGenerator jsonGenerator = 
				jsonFactory.createGenerator(stringWriter);
		
		if(prettyPrint){
			jsonGenerator.useDefaultPrettyPrinter();
		}
		
		if(pojo != null){
			objectMapper.writeValue(jsonGenerator, pojo);
		} else {
			objectMapper.writeValueAsString(new String(" "));
		}
		
		return stringWriter.toString();
	}

	public static <T> Object fromJson(String jsonContents,
			TypeReference<Collection<T>> typeReference) throws 
			JsonParseException, JsonMappingException, IOException {
		
		return objectMapper.readValue(
				jsonContents, typeReference);
	}

}
