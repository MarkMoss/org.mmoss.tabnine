package org.mmoss.tabnine;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang3.SystemUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TabNineManager {
  
  /* Hold the parameters passed into the constructor. */
  private final Path tabNineDirectory;
  
  /* Semaphore to serialize access to this instance from multiple threads. */
  private final Semaphore lock = new Semaphore(1, true);
  
  private Process tabNine = null;
  
  public TabNineManager(Path tabNineDirectory) {
    this.tabNineDirectory = tabNineDirectory;
  }

  public void close() {
    if(tabNine != null) {
      tabNine.destroyForcibly();
      tabNine = null;
    }
  }
  
  /**
   * Passes request to TabNine and returns the response.
   * 
   * @param request The request.
   * @return The response from TabNine.
   * @throws Exception 
   * @threws BusyException Upon timeout while waiting for TabNine to become available.
   */
  public String request(String request) throws Exception {
    String result = "null";  
    
    /* Serialize callers. */
    if(this.lock.tryAcquire(1, 50, TimeUnit.MILLISECONDS)) {
      try {
        result = internalRequest(request);
      } finally {
        lock.release(); 
      }
    } else {
      throw new BusyException("Request timed out.", request);
    }
    return result;
  }
  
  
  private void downloadTabNine() throws Exception {
    String version = getCurrentTabNineVersion();
    URL url = getTabNineDownloadURL(version);
    
    Path download_path = Paths.get(tabNineDirectory.toString(), 
                                   version, 
                                   getTargetString(), 
                                   getTabNineExecutableName());
    /* Make any missing directories along the path. */
    download_path.getParent().toFile().mkdirs();
    /* Create an input stream which downloads the executable from the TabNine site */
    try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(url.toURI());
      try(CloseableHttpResponse httpresponse = httpclient.execute(httpGet)) {
        HttpEntity entity = httpresponse.getEntity();
        BufferedInputStream in = new BufferedInputStream(entity.getContent());
        /* Create an output stream which writes the data to disk.*/
        FileOutputStream file = new FileOutputStream(download_path.toFile());
        BufferedOutputStream out = new BufferedOutputStream(file);
    
        int temp = in.read();
        while(-1 != temp) {
          out.write(temp & 0xFF);
          temp = in.read();
        }
        in.close();
        out.close();
        file.close();
      }
    }
  }
  
  private Path findTabNine() throws Exception {
    Path result = null;
    int fail_count = 0;
    for(;;) {
      /* Phase 1: Search path for TabNine executables. */
      BiPredicate<Path,BasicFileAttributes> matcher;
      String executable = getTabNineExecutableName();
      if(SystemUtils.IS_OS_WINDOWS) {
        /* On Windows, look for the case-insensitive "tabnine.exe" */
        Pattern pattern = Pattern.compile(".*[\\\\/][\\d\\.]+[\\\\/]" 
                                            + getArch().toLowerCase()
                                            + "-[^-]+-"
                                            + getOs().toLowerCase()
                                            + "-*[^-]*[\\\\/]" + executable.toLowerCase() + "$");
        matcher = (path, attr) ->  Files.isRegularFile(path)
                                    && Files.isExecutable(path)
                                    && pattern.matcher(path.toString().toLowerCase()).matches();
      } else {
        /* Everywhere else, look for the case-sensitive "TabNine.exe". */
        Pattern pattern = Pattern.compile(".*[\\\\/][\\d\\.]+[\\\\/]" 
                                            + getArch()
                                            + "-[^-]+-"
                                            + getOs()
                                            + "-[^-]*\\\\" + executable + "$");
        matcher = (path, attr) ->  Files.isRegularFile(path)
                                     && Files.isExecutable(path)
                                     && pattern.matcher(path.toString()).matches();
      }
      try(Stream<Path> paths = Files.find(this.tabNineDirectory, Integer.MAX_VALUE, matcher)){
        result = paths.sorted(new TabNineComparator()).findFirst().get();
        break;
      } catch(NoSuchElementException e) {
        if(fail_count < 1) {
          fail_count++;
          downloadTabNine();
        } else {
          break;
        }
      }
    }
  return result;
  }
  
  /**
   * Returns one of the architecture strings used by TabNine, or throws a FatalException if the
   * current architecture is not supported.
   * @return
   * @throws FatalException
   */
  private String getArch() throws FatalException {
    String arch = "";
    if(SystemUtils.OS_ARCH.equals("amd64")) {
      arch = "x86_64";
    } else if(SystemUtils.OS_ARCH.equals("x86")) {
      /* Not all x86 are i686, but let's try. */
      arch = "i686";
    } else {
      throw new FatalException("Architecture " + SystemUtils.OS_ARCH + " is not supported.");
    }
    return arch;
  }

  /**
   * Queries the TabNine server for the current TabNine version, which is returned as a string.
   * @return the current TabNine version.
   * @throws IOException 
   */
  private String getCurrentTabNineVersion() throws IOException {
    String result = "";
    ByteArrayOutputStream buffer = new ByteArrayOutputStream(16);
    URL url = new URL("https://update.tabnine.com/version");
    BufferedInputStream in = new BufferedInputStream(url.openStream());
    int response;
    response = in.read();
    while(-1 != response) {
      buffer.write(response & 0xFF);
      response = in.read();
    }
    result = buffer.toString("UTF-8").trim();
    return result;
  }
  
  private URL getTabNineDownloadURL(String version) throws IOException {
    String path = version + "/" + getTargetString() + "/" + getTabNineExecutableName();
    return new URL("http://update.tabnine.com/" + path);
  }
  
  private String getTabNineExecutableName() {
    /* Determine executable name based on OS.  "TabNine.exe" for Windows, "TabNine" for all others. */
    String executable;
    if(SystemUtils.IS_OS_WINDOWS) {
      executable = "TabNine.exe";
    } else {
      executable = "TabNine";
    }
    return executable;
  }
  
  /* Returns the full target string (e.g. x86_64-pc-windows-gnu) for the current environment. */
  private String getTargetString() {
    /* Determine the architecture and OS. */
    String arch = getArch();
    String os = getOs();
    /* Expand arch and os into the complete target. */
    String target;
    if(os == "darwin") {
      target = arch + "-apple-darwin";
    } else if (os == "windows") {
      target = arch + "-pc-windows-gnu"; 
    } else if (os == "linux") {
      target = arch + "-unknown-linux-gnu";
    } else {
      /* Not supported. */
      throw new FatalException("The current architecture (" 
                                  + arch 
                                  + ") and OS (" 
                                  + os 
                                  +") is not supported.");
    }
    return target;
  }
  
  /**
   * Returns one of the OS strings used by TabNine or throws a FatalException if the current
   * architecture is not supported.
   * 
   * @return an OS string used by TabNine
   * @throws FatalException if the OS is not supported.
   */
  private String getOs() throws FatalException{
    String os = "";
    /* Translate from the IS_OS_* macros to the os names used by TabNine. */
    if(SystemUtils.IS_OS_WINDOWS) {
      os = "windows";
    } else if (SystemUtils.IS_OS_MAC) {
      os = "darwin"; 
    } else if (SystemUtils.IS_OS_LINUX) {
      os = "linux";
    } else {
      throw new FatalException("OS " + SystemUtils.OS_NAME + "is not supported.");
    }
    return os;
  }
   
  private String internalRequest(String request) throws Exception {
    /* Start process, if necessary. */
    if(this.tabNine == null) {
      startProcess();
    }
    /* Add terminating '\n', unless one is already included in request. */
    if((request.length() > 0) && (request.charAt(request.length() - 1) != '\n')) {
      request += "\n";
    }
    /* Send the request to the TabNine process. */
    this.tabNine.getOutputStream().write(request.getBytes(StandardCharsets.UTF_8));
    this.tabNine.getOutputStream().flush();
    /* Get the response. 
     * 
     * Wrap the stdout stream from TabNine in an input stream reader.  This handles decoding the
     * UTF-8 response from TabNine into Java characters.
     *
     */
    InputStreamReader buffer = new InputStreamReader(this.tabNine.getInputStream(), 
                                                     StandardCharsets.UTF_8); 
    /* Loop over the characters returned by TabNine until reaching an end-of-line character.
     * 
     * Append everything read, except the end-of-line character, to response.
     */
    String response = "";
    char temp = '\0';
    do {
      if(buffer.ready()) {
          temp = (char)buffer.read();
          if(temp != '\n') {
            response += temp;
          }
      } else {
        /* Yield before polling again. */
        Thread.yield();
      }
    } while (temp != '\n');
    /* For now, kill tabNine after each call. */
    return response;
  }
  
  private void startProcess() throws Exception {
    String command = this.findTabNine().toString();   
    /* If there is already a process running, kill it. */
    if(this.tabNine != null) {
      this.tabNine.destroy();
      this.tabNine = null;
    }
    /* Start the new process */
    ProcessBuilder builder = new ProcessBuilder(command);
    this.tabNine = builder.start();
  }  
}
