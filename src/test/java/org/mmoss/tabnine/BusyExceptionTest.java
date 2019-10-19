package org.mmoss.tabnine;

import org.mmoss.tabnine.BusyException;

import junit.framework.TestCase;

public class BusyExceptionTest extends TestCase {

  /* Verifies that the parameters passed to the constructor can be retrieved by calling the
   * appropriate methods.
   */
  public void testConstructor() {
    BusyException e = new BusyException("Message", "Request");
    assertEquals(e.getMessage(), "Message");
    assertEquals(e.getRequest(), "Request");
  }
  
}
