package org.mmoss.tabnine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RequestTest {
  
  public Request uut = new Request();
  
  @Test
  /* Verifies that the default constructor properly sets the version. */
  public void testDefaultConstructor() throws JsonProcessingException {
    assertEquals(uut.version, "1.0.0");
  }
   
}
