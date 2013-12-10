package cx.it.aabmass.phpplugin;

import cx.it.aabmass.httpd.Registrar;
import cx.it.aabmass.httpd.*;
import cx.it.aabmass.httpd.plugin.*;

public class PhpPlugin extends AbstractPlugin {
    public static final String pluginName = "PhpPlugin";
    public static final String mimeType = "text/html";
    public static final String fileExt = "php";
    public static PhpPlugin instance;

    private MimeHandler phpHandler;

    public PhpPlugin() {
        super();
        this.phpHandler = new PhpHandler();
    }

    public void onLoad() {
        //register the handler
        Registrar.registerMimeType(PhpPlugin.mimeType, PhpPlugin.fileExt, false,  phpHandler);
    }

    public String getPluginName() {
        return PhpPlugin.pluginName;
    }
}
