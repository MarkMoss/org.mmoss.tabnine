/* 
 * Copyright (c) 2019 Mark Moss
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, 
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT 
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
