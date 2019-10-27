package org.mmoss.tabninetests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mmoss.tabnine.TabNineException;

public class TabNineExceptionTest {

  /* Verifies that the parameters passed to the constructor can be retrieved by calling the
   * appropriate methods.
   */
  @Test
  public void testConstructor() {
    TabNineException e = new TabNineException("Message", "Request");
    assertEquals(e.getMessage(), "Message");
    assertEquals(e.getRequest(), "Request");
  }
  
}