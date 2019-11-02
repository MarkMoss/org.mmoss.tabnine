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
