package org.mmoss.tabnine.manager;

import java.nio.file.Path;
import java.util.Comparator;

/**
 * Comparator to sort TabNine paths by descending version.
 * 
 * Note that this class is rather naive.  It may return unexpected results in the paths differ in
 * ways other than version.
 */
public class VersionComparator implements Comparator<Path> {
  public int compare(Path p1, Path p2) {
    return p2.compareTo(p1);
  }
}
