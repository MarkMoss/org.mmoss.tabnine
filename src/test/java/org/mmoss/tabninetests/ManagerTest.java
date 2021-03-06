/* 
 * Copyright (c) 2019 Mark Moss
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, 
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT 
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.mmoss.tabninetests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mmoss.tabnine.Manager;

/* Setup to use a per-class lifecycle to avoid setup and teardown of TabNine for each test. */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManagerTest{

  public Manager uut;
  public Path temp_path;
  
  @AfterAll
  public void close() throws Exception {
    uut.close();
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
  
  @BeforeAll
  public void createManager() throws IOException {
    temp_path = Files.createTempDirectory(null);
    uut = new Manager(temp_path);
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
