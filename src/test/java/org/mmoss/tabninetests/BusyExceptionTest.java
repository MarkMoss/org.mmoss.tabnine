package org.mmoss.tabninetests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mmoss.tabnine.BusyException;

public class BusyExceptionTest {

  /* Verifies that the parameters passed to the constructor can be retrieved by calling the
   * appropriate methods.
   */
  @Test
  public void testConstructor() {
    BusyException e = new BusyException("Message", "Request");
    assertEquals(e.getMessage(), "Message");
    assertEquals(e.getRequest(), "Request");
  }
  
}
