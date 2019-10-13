package org.mmoss.tabnine;

import junit.framework.TestCase;

public class TargetStringTest extends TestCase {
  /* Verify that a target string containing no data does not produce results. */
  public void testEmpty() {
    TargetString ts = new TargetString("");
    assertEquals(ts.getABI(), "");
    assertEquals(ts.getArchitecture(), "");
    assertEquals(ts.getOS(), "");
    assertEquals(ts.getVendor(), "");
    
  }
  
  /* Tests a target string known to be used by TabNine. */
  public void testNormalValue1() {
    TargetString ts = new TargetString("i686-apple-darwin");
    assertEquals(ts.getABI(), "");
    assertEquals(ts.getArchitecture(), "i686");
    assertEquals(ts.getOS(), "darwin");
    assertEquals(ts.getVendor(), "apple");
  }
  
  /* Tests a target string known to be used by TabNine. */
  public void testNormalValue2() {
    TargetString ts = new TargetString("i686-pc-windows-gnu");
    assertEquals(ts.getABI(), "gnu");
    assertEquals(ts.getArchitecture(), "i686");
    assertEquals(ts.getOS(), "windows");
    assertEquals(ts.getVendor(), "pc");
  }
  
  /* Tests a target string known to be used by TabNine. */
  public void testNormalValue3() {
    TargetString ts = new TargetString("i686-unknown-linux-gnu");
    assertEquals(ts.getABI(), "gnu");
    assertEquals(ts.getArchitecture(), "i686");
    assertEquals(ts.getOS(), "linux");
    assertEquals(ts.getVendor(), "unknown");
  }
  
  /* Tests a target string known to be used by TabNine. */
  public void testNormalValue4() {
    TargetString ts = new TargetString("x86_64-apple-darwin");
    assertEquals(ts.getABI(), "");
    assertEquals(ts.getArchitecture(), "x86_64");
    assertEquals(ts.getOS(), "darwin");
    assertEquals(ts.getVendor(), "apple");
  }
  
  /* Tests a target string known to be used by TabNine. */
  public void testNormalValue5() {
    TargetString ts = new TargetString("x86_64-pc-windows-gnu");
    assertEquals(ts.getABI(), "gnu");
    assertEquals(ts.getArchitecture(), "x86_64");
    assertEquals(ts.getOS(), "windows");
    assertEquals(ts.getVendor(), "pc");
  }
  
  /* Tests a target string known to be used by TabNine. */
  public void testNormalValue6() {
    TargetString ts = new TargetString("x86_64-unknown-linux-gnu");
    assertEquals(ts.getABI(), "gnu");
    assertEquals(ts.getArchitecture(), "x86_64");
    assertEquals(ts.getOS(), "linux");
    assertEquals(ts.getVendor(), "unknown");
  }
  
  /* Tests that a string containing a single hyphen isn't parsed. */
  public void testSingleHyphen() {
    TargetString ts = new TargetString("Stuff-Junk");
    assertEquals(ts.getABI(), "");
    assertEquals(ts.getArchitecture(), "");
    assertEquals(ts.getOS(), "");
    assertEquals(ts.getVendor(), "");
  }
  
  /* Tests that a string containing a leading hyphen isn't parsed. */
  public void testLeadingHyphen() {
    TargetString ts = new TargetString("-Stuff");
    assertEquals(ts.getABI(), "");
    assertEquals(ts.getArchitecture(), "");
    assertEquals(ts.getOS(), "");
    assertEquals(ts.getVendor(), "");
  }
  
  /* Tests that a string containing a trailing hyphen isn't parsed. */
  public void testTrailingHyphen() {
    TargetString ts = new TargetString("Stuff-");
    assertEquals(ts.getABI(), "");
    assertEquals(ts.getArchitecture(), "");
    assertEquals(ts.getOS(), "");
    assertEquals(ts.getVendor(), "");
  }
  
  /* Tests that a string containing a leading and trailing hyphens isn't parsed. */
  public void testLeadingTrailingHyphens() {
    TargetString ts = new TargetString("-Stuff-");
    assertEquals(ts.getABI(), "");
    assertEquals(ts.getArchitecture(), "");
    assertEquals(ts.getOS(), "");
    assertEquals(ts.getVendor(), "");
  }
  
  /* Tests that a string containing a leading, mid, and trailing hyphens isn't parsed. */
  public void testLeadingMidTrailingHyphens() {
    TargetString ts = new TargetString("-Stuff-Junk-Clutter");
    assertEquals(ts.getABI(), "");
    assertEquals(ts.getArchitecture(), "");
    assertEquals(ts.getOS(), "");
    assertEquals(ts.getVendor(), "");
  }
  
}
