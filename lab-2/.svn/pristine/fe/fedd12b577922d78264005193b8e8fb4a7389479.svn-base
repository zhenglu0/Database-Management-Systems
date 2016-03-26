package cse530a.servlet;

import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jcraft.jsch.Session;

import cse530a.util.PropertiesLoader;
import cse530a.util.SshTunneler;

public class ContextListener implements ServletContextListener {
    private final static Logger LOGGER = Logger.getLogger(ContextListener.class.getName());
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Properties sshProperties = PropertiesLoader.loadProperties("/ssh.properties");
        if (sshProperties != null) {
            Session sshSession = SshTunneler.openSession(sshProperties);
            ServletContext sc = event.getServletContext();
            sc.setAttribute("sshSession", sshSession);

            LOGGER.info("Created ssh session");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        Session sshSession = (Session) sc.getAttribute("sshSession");
        if (sshSession != null) {
            SshTunneler.closeSession(sshSession);
        }
    }
}
