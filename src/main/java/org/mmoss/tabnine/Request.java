package org.mmoss.tabnine;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {
  final public String version;
  //final public InnerRequest request;
  public final HashMap<String, HashMap<String, Object>> request 
    = new HashMap<String, HashMap<String, Object>>();

  public Request() {
    version = "1.0.0";
  }
  
  /** Returns the request name.
   * 
   * @return The request name.
   */
  @JsonIgnore
  protected String getRequestName() {
    /* Verify request name has been set. */
    if(request.size() == 0) {
      throw new IllegalStateException("Request name not set.");
    }
    assert(request.size() == 1);
    return request.keySet().iterator().next();
  }
  
  /** Serializes the request to JSON.
   * 
   * @return A String containing the JSON representing this request.
   * @throws JsonProcessingException
   */
  public String Serialize() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    String result = mapper.writeValueAsString(this);
    return result;
  }
  
  /** Sets the request name.
   * 
   * @param name The request name.
   */
  @JsonIgnore
  protected void setRequestName(String name) {
    /* Check that the name has not already been set. */
    assert(request.size() == 0);
    /* Create a new HashMap to hold the request parameters and add it to request with the specified
     * name.
     */
    request.put(name, new HashMap<String, Object>());
  }
  
  /** Adds a parameter to the request.
   * 
   * The name of the request must be set first.
   * 
   * @param name The name of the parameter.
   * @param value The value of the parameter.
   */
  @JsonIgnore
  protected void setRequestParameter(String name, Object value) {
    String request_name = getRequestName();
    request.get(request_name).put(name, value);
  }  
}
