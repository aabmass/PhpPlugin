package cx.it.aabmass.phpplugin;

import cx.it.aabmass.httpd.util.IO;
import cx.it.aabmass.httpd.*;

import java.net.Socket;
import java.io.*;

public class PhpHandler implements MimeHandler {
    public static final String mimeType = "text/html";
    public static final String fileExt = "php";

    public void handleClientConnection(final Socket client, File fileToServe) {
        try {
            ProcessBuilder pb = 
                new ProcessBuilder ("/usr/bin/php", fileToServe.getCanonicalPath());
            Process phpInstance = pb.start();

            // rewrite php's input stream to the socket
            IO.writeInputStreamToSocket(client, phpInstance.getInputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMimeTypeName() {
        return PhpHandler.mimeType;
    }

    public String getFileExtension() {
        return PhpHandler.fileExt;
    }
}
