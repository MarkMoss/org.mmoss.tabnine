package org.mmoss.tabnine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mmoss.tabnine.Manager;

public class ManagerTest{

  public Manager uut;
  
  @BeforeEach
  protected void setUp() throws Exception {
    this.uut = new Manager(Paths.get("C:\\Users\\mmoss\\git\\TabNine\\binaries"));
  }

  /* Verifies that normal usage of the one-parameter constructor does not throw an exception. */
  @Test
  public void testDefaultConstructor1() {
    new Manager(Paths.get("c:\\foobar"));
  }
    
  /* TabNine should return the string "null" for no input. */
  @Test
  public void testNoInput() throws Throwable {
    /* Also verifies the ability to make multiple requests. */
    assertEquals(this.uut.request("{}"), "null");
    assertEquals(this.uut.request("{}"), "null");
    assertEquals(this.uut.request("{}"), "null");
  }
  
  /* Test download capability */
  @Test
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
