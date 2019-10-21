package org.mmoss.tabnine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestTest {
  
  public ObjectMapper mapper = new ObjectMapper();
  public Request uut = new Request();
  
  
  @Test
  /* Verifies that the default constructor properly sets the version. */
  public void testDefaultConstructor() throws JsonProcessingException {
    assertEquals(uut.version, "1.0.0");
  }
  
  /* Verifies that the default constructor returns a request which generates the expected JSON. */
  @Test
  public void testCreateAutocompleteRequest() throws JsonProcessingException {
    Request req = Request.createAutocompleteRequest("before", 
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
