package cse530a.servlet;

import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.jcraft.jsch.Session;

import cse530a.util.PropertiesLoader;
import cse530a.util.SshTunneler;

public class ContextListener implements ServletContextListener {
    private final static Logger LOGGER = Logger.getLogger(ContextListener.class.getName());
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();

        Properties sshProperties = PropertiesLoader.loadProperties("/ssh.properties");
        if (sshProperties != null) {
            Session sshSession = SshTunneler.openSession(sshProperties);
            sc.setAttribute("sshSession", sshSession);
            LOGGER.info("Created ssh session");
        }
        
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        sc.setAttribute("hibernateSessionFactory", sessionFactory);
        LOGGER.info("Created hibernate session factory");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        
        SessionFactory sessionFactory = (SessionFactory) sc.getAttribute("hibernateSessionFactory");
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        
        Session sshSession = (Session) sc.getAttribute("sshSession");
        if (sshSession != null) {
            SshTunneler.closeSession(sshSession);
        }
    }
}
