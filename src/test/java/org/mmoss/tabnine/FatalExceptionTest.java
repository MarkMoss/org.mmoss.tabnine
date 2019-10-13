package org.mmoss.tabnine;

import junit.framework.TestCase;

public class FatalExceptionTest extends TestCase {

  /* Verifies that the parameters passed to the constructor can be retrieved by calling the
   * appropriate methods.
   */
  public void testConstructor() {
    FatalException e = new FatalException("Message", "Request");
    assertEquals(e.getMessage(), "Message");
    assertEquals(e.getRequest(), "Request");
  }
  
  /* Verifies the one-parameter constructor (implied null request) */
  public void testOneParameterConstructor() {
    FatalException e = new FatalException("Message");
    assertEquals(e.getMessage(), "Message");
    assertEquals(e.getRequest(), null);
  }
  
}