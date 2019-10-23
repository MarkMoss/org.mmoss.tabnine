package org.mmoss.tabnine;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutocompleteResponse extends Response {
  public String old_prefix;
  public AutocompleteResponseResult[] results;
  public String[] user_message;
  
  static public AutocompleteResponse fromJson(String json) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json,  AutocompleteResponse.class);
  }  
}
