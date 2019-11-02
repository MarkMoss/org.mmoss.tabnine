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

package org.mmoss.tabninetests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.mmoss.tabnine.AutocompleteRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AutocompleteRequestTest {
  public ObjectMapper mapper = new ObjectMapper();
  
  /* Verifies that the default constructor returns a request which generates the expected JSON. */
  @Test
  public void testCreateAutocompleteRequest() throws JsonProcessingException, IOException {
    AutocompleteRequest req = new AutocompleteRequest("before", 
                                                      "after",
                                                      true,
                                                      false,
                                                      Paths.get("foobar"),
                                                      42);
    String json = req.Serialize();
    JsonNode node = mapper.readTree(json);
    assertEquals(node.get("version").asText(), "1.0.0");
    assertEquals(node.get("request").get("Autocomplete").get("before").asText(), "before");
    assertEquals(node.get("request").get("Autocomplete").get("after").asText(), "after");
    assertEquals(node.get("request").get("Autocomplete").get("region_includes_beginning").asBoolean(), true);
    assertEquals(node.get("request").get("Autocomplete").get("region_includes_end").asBoolean(), false);
    assertEquals(node.get("request").get("Autocomplete").get("filename").asText(), "foobar");
    assertEquals(node.get("request").get("Autocomplete").get("max_num_results").asInt(), 42);
   }
  
}
