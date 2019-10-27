package org.mmoss.tabninetests;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mmoss.tabnine.Manager;

public class ManagerInternalsTest {

  public Manager uut;
  
  @BeforeEach
  protected void setUp() throws Exception {
    this.uut = new Manager(Paths.get("C:\\Users\\mmoss\\git\\TabNine\\binaries"));
  }
  
  /* Verifies getCurrentTabNineVersion retrieves a legible version. */
  @Test
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
