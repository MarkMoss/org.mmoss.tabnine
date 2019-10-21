package org.mmoss.tabnine;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mmoss.tabnine.VersionComparator;

public class VersionComparitorTest {
  
  VersionComparator uut;
  
  @BeforeEach
  protected void setUp() {
    this.uut = new VersionComparator();
  }
  
  /* Verifies less-than comparison of two typical TabNine windows absolute 
   * paths.
   */
  @Test
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
  @Test
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
  @Test
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
  @Test
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
  @Test
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
  @Test
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
