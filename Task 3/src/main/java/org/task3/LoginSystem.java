package org.task3;

public class LoginSystem {
    private boolean loggedIn = false;
    private boolean alertsViewable = false;

    boolean isLoggedIn(){
        return loggedIn;
    }

    boolean areAlertsViewable(){
        return alertsViewable;
    }

    void goodLogin() {
        if (!loggedIn){
            loggedIn = true;
        }
    }

    void viewAlerts() {
        if (loggedIn) {
            alertsViewable = true;
        }
    }

    void badLogin(){
        if (!loggedIn){
            loggedIn = false;
        }
    }

    void logOut() {
        if (loggedIn) {
            loggedIn = false;
            alertsViewable = false;
        }
    }
}