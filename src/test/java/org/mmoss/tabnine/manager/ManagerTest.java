package org.mmoss.tabnine.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.mmoss.tabnine.manager.Manager;

import junit.framework.TestCase;

public class ManagerTest extends TestCase {

  public Manager uut;
  
  protected void setUp() throws Exception {
    super.setUp();
    this.uut = new Manager(Paths.get("C:\\Users\\mmoss\\git\\TabNine\\binaries"));
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /* Verifies that normal usage of the one-parameter constructor does not throw an exception. */
  public void testDefaultConstructor1() {
    new Manager(Paths.get("c:\\foobar"));
  }
    
  /* TabNine should return the string "null" for no input. */
  public void testNoInput() throws Throwable {
    /* Also verifies the ability to make multiple requests. */
    assertEquals(this.uut.request("{}"), "null");
    assertEquals(this.uut.request("{}"), "null");
    assertEquals(this.uut.request("{}"), "null");
  }
  
  /* Test download capability */
  public void testDownload() throws Exception {
    Path temp_path = Files.createTempDirectory(null);
    Manager local_uut = new Manager(temp_path);
    assertEquals(local_uut.request("{}"), "null");
    local_uut.close();
    /* Try to clean-up...retry for up to ~10 s. The first few tries may fail until the tab
     * nine executable is no longer in use.  This may be complicated by virus scanners, etc.
     * which may access the file.
     */
    int maxTries = 100;
    for(int i = 0; ; i++) {
      try {
        FileUtils.deleteDirectory(temp_path.toFile());
        break;
      } catch(IOException e) {
        if(i <= maxTries) {
          Thread.sleep(100);
        } else {
          throw e;
        }
      }
    }
  }
}
