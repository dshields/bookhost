package amplitude.persistence.hibernate;

import amplitude.persistence.hibernate.dao._RootDAO;
import amplitude.system.AmplitudeProperties;
import amplitude.system.AmplitudeSystem;
import amplitude.system.IAmplitudeProperties;
import org.apache.log4j.Logger;
import org.h2.tools.DeleteDbFiles;
import org.h2.tools.RunScript;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {
    static Logger log = Logger.getLogger(DBUtils.class.getName());
    IAmplitudeProperties props = AmplitudeProperties.getAmplitudeProperties();

    public static void initialize(boolean firstTimeSetup) throws Exception {
        DBUtils utils = new DBUtils();
        if (firstTimeSetup) {
            utils.deleteDatabase();
            utils.createDatabase();
        }
        Properties properties = new Properties();
        properties.put("hibernate.connection.url", AmplitudeProperties.getAmplitudeProperties().getDatabaseURL());
        org.hibernate.cfg.Configuration cfg = HibernateSessionFactory.getDefaultConfiguration();
        cfg.addProperties(properties);
        HibernateSessionFactory.setDefaultConfiguration(cfg);
        _RootDAO.initialize(null, cfg);
        _RootDAO.initialize("", cfg);
        _RootDAO.initialize("/hibernate.cfg.xml", cfg);
        HibernateSessionFactory.getSession();
        AmplitudeSystem.initialize();
        //utils.loadDatabase();
    }

    public void createDatabase() throws Exception {
        InputStream is = this.getClass().getResourceAsStream("create.sql");
        if (is == null) {
            throw new Exception("Resource create.sql not found");
        }
        InputStreamReader reader = new InputStreamReader(is);
        Connection conn = getDBConnection();
        RunScript.execute(conn, reader);
        conn.close();
    }

    public void loadDatabase() throws Exception {
        DBLoader loader = new DBLoader();
        loader.addFromDirectory(new File(AmplitudeProperties.getAmplitudeProperties().getSongDirectory()));
    }

    public void refreshDatabase() throws Exception {
        loadDatabase();
    }

    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName(props.getDatabaseDriverName());
        System.out.println("db url is " + props.getDatabaseURL());
        return DriverManager.getConnection(props.getDatabaseURL(), props.getDatabaseUser(), props.getDatabasePassword());

    }

    public void deleteDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            Statement s = conn.createStatement();
            s.execute("shutdown");
        } catch (Exception ex) {
            System.out.println("Could not shutdown database. Deleting anyway.");
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        System.out.println("Deleting files from: " + props.getInstallDir());
        DeleteDbFiles.execute(props.getInstallDir(), "amplitude", false);
    }

    public boolean tablesExist() {
        try {
            Connection conn = getDBConnection();
            Statement s = conn.createStatement();
            s.execute("select count(*) from artist");
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //return false;
        }
        return true;
    }

    public boolean databaseExists() {
        File f = new File(props.getInstallDir() + File.separator + "amplitude.mv.db");
        System.out.println("Checking if " + f.getAbsolutePath() + " exists.");
        if (!f.exists())
            return false;
        return tablesExist();
    }
}
