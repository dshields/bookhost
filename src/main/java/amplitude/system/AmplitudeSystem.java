package amplitude.system;

import amplitude.model.IAmplitudeDb;

public class AmplitudeSystem {

    /* To Do: Need to provide an accessor to setup classname. Should be set by the application. */
    static String daoClassName = "amplitude.persistence.hibernate.AmplitudeDB";

    static IAmplitudeDb dao;


    public AmplitudeSystem() {
    }

    static synchronized public void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (dao != null)
            return;
        Class clazz = Class.forName(daoClassName);
        dao = (IAmplitudeDb) clazz.newInstance();
    }

    static public IAmplitudeDb getAmplitudeDB() {
        return dao;
    }

}
