package org.task3;


import junit.framework.Assert;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.task3.states.LoginSystemStates;

import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class LoginSystemModelTest implements FsmModel{
    private LoginSystemStates modelState;

    private boolean loggedIn;
    private boolean alertsViewable;

    private LoginSystem systemUnderTest;

    public LoginSystemStates getState() { return modelState; }

    public void reset(final boolean bool){
        modelState = LoginSystemStates.USER_LOGGED_OUT;
        loggedIn = false;
        alertsViewable = false;
        if (bool) {
            systemUnderTest = new LoginSystem();
        }
    }

    public boolean goodLoginGuard() {
        return getState().equals(LoginSystemStates.USER_LOGGED_OUT);
    }
    public @Action void goodLogin() {
        systemUnderTest.goodLogin();

        loggedIn = true;

        modelState = LoginSystemStates.USER_LOGGED_IN;

        Assert.assertEquals("The model's logged in state !== the system under test's state.", loggedIn, systemUnderTest.isLoggedIn());
    }

    public boolean badLoginGuard() {
        return getState().equals(LoginSystemStates.USER_LOGGED_OUT);
    }
    public @Action void badLogin() {
        systemUnderTest.badLogin();

        loggedIn = false;

        modelState = LoginSystemStates.USER_LOGGED_OUT;

        Assert.assertEquals("The model's logged in state !== the system under test's state.", loggedIn, systemUnderTest.isLoggedIn());
    }

    public boolean logOutGuard() {
        return getState().equals(LoginSystemStates.USER_LOGGED_IN) || getState().equals(LoginSystemStates.VIEWING_ALERTS);
    }
    public @Action void logOut() {
        systemUnderTest.logOut();

        loggedIn = false;
        alertsViewable = false;

        modelState = LoginSystemStates.USER_LOGGED_OUT;

        Assert.assertEquals("The model's logged in state !== the system under test's state.", loggedIn, systemUnderTest.isLoggedIn());
        Assert.assertEquals("The model's alert viewing state !== the system under test's state.", alertsViewable, systemUnderTest.areAlertsViewable());
    }

    public boolean viewAlertsGuard() {
        return getState().equals(LoginSystemStates.USER_LOGGED_IN);
    }
    public @Action void viewAlerts() {
        systemUnderTest.viewAlerts();

        alertsViewable = true;

        modelState = LoginSystemStates.VIEWING_ALERTS;
        Assert.assertEquals("The model's logged in state !== the system under test's state.", loggedIn, systemUnderTest.isLoggedIn());
        Assert.assertEquals("The model's alert viewing state !== the system under test's state.", alertsViewable, systemUnderTest.areAlertsViewable());
    }

    @Test
    public void LoginSystemModelTestRunner() throws IOException {
        final Tester loginTester = new RandomTester(new LoginSystemModelTest());
        loginTester.setRandom(new Random());
        loginTester.buildGraph();
        loginTester.addListener(new StopOnFailureListener());
        loginTester.addListener("verbose");
        loginTester.addCoverageMetric(new TransitionPairCoverage());
        loginTester.addCoverageMetric(new StateCoverage());
        loginTester.addCoverageMetric(new ActionCoverage());
        loginTester.generate(250);
        loginTester.printCoverage();
    }
}
