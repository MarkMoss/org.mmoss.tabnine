package org.mmoss.tabnine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to represent a target, for example "i686-pc-windows-gnu".
 * 
 * This class is intended to be accepting of invalid input.  Any fields which cannot be 
 * determine from the string passed to the constructor will simply return an empty string.
 * @author mmoss
 *
 */
public class TargetString {
  
  /* Regular expression to match a string in the format "arch-vendor-os-abi", with ABI being
   * optional. 
   * 
   * Specific values know to be used by TabNine are:
   * 
   * i686-apple-darwin
   * i686-pc-windows-gnu
   * i686-unknown-linux-gnu
   * x86_64-apple-darwin
   * x86_64-pc-windows-gnu
   * x86_64_unknown-linux-gnu
   * */
  private Pattern p;
  private String architecture;
  private String vendor;
  private String os;
  private String abi;
  
  /**
   * Constructor.
   * 
   * @param target A target string, for example "i686-pc-windows-gnu", consisting of an
   *               architecture, a vendor, an os, and an optional ABI separated by '-' characters.
   */
  public TargetString(String target) {
    try {
      this.p = Pattern.compile("([^-]{1,})-([^-]{1,})-([^-]{1,})-?([^-]*)");
      Matcher m = this.p.matcher(target);
      if(m.matches()) {
        /* This is likely a valid target. */
        this.architecture = m.group(1).toLowerCase();
        this.vendor = m.group(2).toLowerCase();
        this.os = m.group(3).toLowerCase();
        this.abi = m.group(4).toLowerCase();
      } else {
        /* This is probably not a valid target. */
        this.architecture = "";
        this.vendor = "";
        this.os = "";
        this.abi = "";
      }
    } catch(Exception e) {
      System.out.print("Exception for input \"" + target + "\".\n");
      /* Should any exception occur, fill all fields with blanks. */
      this.architecture = "";
      this.vendor = "";
      this.os = "";
      this.abi = "";
    }
  }
  
  /**
   * Returns the ABI.
   * 
   * For example, "gnu".  May be empty if an abi could not be parsed from the string passed to the
   * constructor.
   * 
   * @return the ABI.
   * 
   */
  public String getABI() {
    return this.abi;
  }
  
  /**
   * Returns the architecture.
   * 
   * For example "i686" or "x86_64".  May be empty if an architecture could not be parsed from the 
   * string passed to the constructor.
   * 
   * @return the architecture.
   */
  public String getArchitecture() {
    return this.architecture;
  }
  
  /**
   * Returns the OS.
   * 
   * For example, "darwin" or "linux" or "windows".  May be empty if an Os could not be parsed from
   * the string passed to the constructor.
   * @return the OS.
   */
  public String getOS() {
    return this.os;
  }
  
  /**
   * Returns the vendor.
   * 
   * For example, "apple" or "pc" or "unknown".  May be empty if a vendor could not be parsed from
   * the string passed to the constructor.
   * @return the vendor.
   */
  public String getVendor() {
    return this.vendor;
  }
}
