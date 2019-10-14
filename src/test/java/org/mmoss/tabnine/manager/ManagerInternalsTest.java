package org.mmoss.tabnine.manager;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.mmoss.tabnine.manager.Manager;

import junit.framework.TestCase;

public class ManagerInternalsTest extends TestCase {

  public Manager uut;
  
  protected void setUp() throws Exception {
    super.setUp();
    this.uut = new Manager(Paths.get("C:\\Users\\mmoss\\git\\TabNine\\binaries"));
  }
  
  /* Verifies getCurrentTabNineVersion retrieves a legible version. */
  public void test_getCurrentTabNineVersion() throws Exception {
    /* Access private method via reflection. */
    String response;
    Method mut = uut.getClass().getDeclaredMethod("getCurrentTabNineVersion");
    mut.setAccessible(true);
    /* Verify response is in the form 1.2.3.... */
    response = (String)mut.invoke(uut);
    assert(Pattern.compile("[\\d\\.]+").matcher(response).matches());
  }
    
}
