package amplitude.servlets;

import amplitude.system.AmplitudeSystem;

import javax.servlet.http.HttpServletRequest;

public class WebHelper {

    static public boolean isFirstRun() {
        return !AmplitudeSystem.getAmplitudeDB().databaseExists();
    }

    static public boolean requestIsLocal(HttpServletRequest request) {
        return request.getRemoteAddr().equals(request.getRemoteAddr());
    }


}
