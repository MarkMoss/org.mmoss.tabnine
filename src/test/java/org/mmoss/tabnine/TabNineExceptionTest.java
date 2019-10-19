package org.mmoss.tabnine;

import org.mmoss.tabnine.TabNineException;

import junit.framework.TestCase;

public class TabNineExceptionTest extends TestCase {

  /* Verifies that the parameters passed to the constructor can be retrieved by calling the
   * appropriate methods.
   */
  public void testConstructor() {
    TabNineException e = new TabNineException("Message", "Request");
    assertEquals(e.getMessage(), "Message");
    assertEquals(e.getRequest(), "Request");
  }
  
}