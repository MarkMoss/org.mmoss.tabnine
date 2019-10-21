package org.mmoss.tabnine;

import java.nio.file.Path;
import java.util.Dictionary;
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
  
  /**
   * Creates and returns a new Request representing a TabNine Autocomplete request.
   * 
   * @param before The text prior to the current cursor.
   * @param after The text after the current cursor.
   * @param region_includes_beginning Whether or not the 'before' text extends to the beginning of 
   *                                  the file.
   * @param region_includes_end Whether or not the 'after' text extends to the end of the file.
   * @param filename The name of the file containing the 'before' and 'after' text.
   * @param max_num_results The maximum number of results to return.
   * @return The request.
   */
  static public Request createAutocompleteRequest(String before,
                                                  String after,
                                                  boolean region_includes_beginning,
                                                  boolean region_includes_end,
                                                  Path filename,
                                                  int max_num_results) {
    Request req = new Request();
    req.setRequestName("Autocomplete");
    req.setRequestParameter("before", before);
    req.setRequestParameter("after", after);
    req.setRequestParameter("region_includes_beginning", region_includes_beginning);
    req.setRequestParameter("region_includes_end", region_includes_end);
    req.setRequestParameter("filename", filename.toString());
    req.setRequestParameter("max_num_results", max_num_results);
    return req;
  }
   
}
