package cse530a.util;

import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Helper class for creating ssh tunnels
 */
public class SshTunneler {
    private static final Logger LOGGER = Logger.getLogger(SshTunneler.class.getName());

    public static Session openSession(Properties properties) {
        String sshServer = properties.getProperty("sshServer");
        String dbServer = properties.getProperty("dbServer");
        String username = properties.getProperty("sshUsername");
        String password = properties.getProperty("sshPassword");
        int localPort = Integer.parseInt(properties.getProperty("localPort"));

        Session session = null;
        
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, sshServer, 22);
        
            Hashtable<String, String> config = new Hashtable<String, String>();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.setPassword(password);
            session.connect();

            session.setPortForwardingL(localPort, dbServer, 5432);
        } catch (JSchException e) {
            LOGGER.log(Level.SEVERE, "error opening ssh session", e);
        }
        
        return session;
    }
    
    public static void closeSession(Session session) {
        if (session != null) {
            session.disconnect();
        }
    }
}
