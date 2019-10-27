package org.mmoss.tabninetests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mmoss.tabnine.FatalException;

public class FatalExceptionTest {

  /* Verifies that the parameters passed to the constructor can be retrieved by calling the
   * appropriate methods.
   */
  @Test
  public void testConstructor() {
    FatalException e = new FatalException("Message", "Request");
    assertEquals(e.getMessage(), "Message");
    assertEquals(e.getRequest(), "Request");
  }
  
  /* Verifies the one-parameter constructor (implied null request) */
  @Test
  public void testOneParameterConstructor() {
    FatalException e = new FatalException("Message");
    assertEquals(e.getMessage(), "Message");
    assertEquals(e.getRequest(), null);
  }
  
}