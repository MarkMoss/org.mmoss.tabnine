package org.mmoss.tabnine.manager;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.mmoss.tabnine.manager.VersionComparator;

import junit.framework.TestCase;

public class VersionComparitorTest extends TestCase {
  
  VersionComparator uut;
  
  protected void setUp() {
    this.uut = new VersionComparator();
  }
  
  /* Verifies less-than comparison of two typical TabNine windows absolute 
   * paths.
   */
  public void testLtAbsWindows() {
    Path p1 = Paths.get("C:",
                        "foobar",
                        "2.1.18",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    Path p2 = Paths.get("C:",
                        "foobar",
                        "2.1.17",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    assert(uut.compare(p1, p2) < 0);
  }
  
  /* Verifies less-than comparison of two typical TabNine posix absolute 
   * paths.
   */
  public void testLtAbsPosix() {
    Path p1 = Paths.get("/",
                        "foobar",
                        "2.1.18",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    Path p2 = Paths.get("/",
                        "foobar",
                        "2.1.17",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    assert(uut.compare(p1, p2) < 0);
  }

  /* Verifies equal comparison of two typical TabNine windows absolute paths. */
  public void testEqAbsWindows() {
    Path p1 = Paths.get("C:",
                        "foobar",
                        "2.1.17",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    Path p2 = Paths.get("C:",
                        "foobar",
                        "2.1.17",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    assert(uut.compare(p1, p2) == 0);
  }
  
  /* Verifies equal comparison of two typical TabNine posix absolute paths. */
  public void testEqAbsPosix() {
    Path p1 = Paths.get("/",
                        "foobar",
                        "2.1.17",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    Path p2 = Paths.get("/",
                        "foobar",
                        "2.1.17",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    assert(uut.compare(p1, p2) == 0);
  }
  
  /* Verifies greater than comparison of two typical TabNine windows absolute 
   * paths. 
   */
  public void testGtAbsWindows() {
    Path p1 = Paths.get("C:",
                        "foobar",
                        "2.1.17",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    Path p2 = Paths.get("C:",
                        "foobar",
                        "2.1.18",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    assert(uut.compare(p1, p2) > 0);
  }

  /* Verifies greater than comparison of two typical TabNine posix absolute 
   * paths. 
   */
  public void testGtAbsPosix() {
    Path p1 = Paths.get("/",
                        "foobar",
                        "2.1.17",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    Path p2 = Paths.get("/",
                        "foobar",
                        "2.1.18",
                        "x86_64-pc-windows-gnu",
                        "tabnine.exe");
    assert(uut.compare(p1, p2) > 0);
  }
  
  
  
}
