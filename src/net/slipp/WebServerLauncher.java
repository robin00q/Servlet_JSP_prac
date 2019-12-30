package net.slipp;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.slipp.user.UpdateFormUserServlet;

public class WebServerLauncher {
	private static final Logger logger = LoggerFactory.getLogger(WebServerLauncher.class);

	public static void main(String[] args) throws Exception {
        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8282);

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        logger.debug("configuring app with basedir: {}" + new File("./" + webappDirLocation).getAbsolutePath());
//        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
