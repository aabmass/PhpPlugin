package cx.it.aabmass.phpplugin;

import cx.it.aabmass.httpd.util.IO;
import cx.it.aabmass.httpd.*;

import java.net.Socket;
import java.io.*;
import java.util.*;

public class PhpHandler implements MimeHandler {
    public static final String mimeType = "text/html";
    public static final String fileExt = "php";

    public void handleClientConnection(final Socket client, String fullCommand, File fileToServe) {
        try {
            List<String> pArgs = new LinkedList<String>();
            pArgs.add("/usr/bin/php-cgi");
            pArgs.add("-q");
            pArgs.add("-f");
            pArgs.add(fileToServe.getCanonicalPath());
            for (String s : parsePhpVariablesArgs(fullCommand)) {
                Log.debug(s);
                pArgs.add(s);
            }

            ProcessBuilder pb = 
                new ProcessBuilder(pArgs);
            Process phpInstance = pb.start();

            // rewrite php's input stream to the socket
            OutputStreamWriter out = new OutputStreamWriter(client.getOutputStream());
            InputStreamReader in = new InputStreamReader(phpInstance.getInputStream());

            IO.writeReaderToWriterChar(in, out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] parsePhpVariablesArgs(String fullCommand) {
        try {
            String[] vargs = fullCommand.split(".php")[1].split("\\?");
            return vargs = Arrays.copyOfRange(vargs, 1, vargs.length);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return new String[0];
        }
    }

    public String getMimeTypeName() {
        return PhpHandler.mimeType;
    }

    public String getFileExtension() {
        return PhpHandler.fileExt;
    }
}
