package org.task3;

import nz.ac.waikato.modeljunit.Action;
import junit.framework.Assert;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.task3.states.APIFunctionalityStates;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.fail;

public class APIFunctionalityModelTest implements FsmModel {
    private APIFunctionalityStates modelState;
    private int numOfAlerts;
    private boolean uploaded;
    private boolean purged;
    private boolean alertType;
    private boolean heading;
    private boolean description;
    private boolean url;
    private boolean imageUrl;
    private boolean postedBy;
    private boolean priceInCents;

    private APIFunctionality systemUnderTest;

    public APIFunctionalityStates getState() {
        return modelState;
    }

    public void reset(final boolean bool){
        modelState = APIFunctionalityStates.API_INITIALISED;
        numOfAlerts = 0;
        uploaded = false;
        purged = false;
        alertType = false;
        heading = false;
        description = false;
        url = false;
        imageUrl = false;
        postedBy = false;
        priceInCents = false;
        if(bool) {
            APIFunctionality dummyReq = new APIFunctionality();
            try {
                dummyReq.initialPurge();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                GetResponse dummyResponse = dummyReq.getRequestFromMarketAlerts();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            systemUnderTest = new APIFunctionality();
        }
    }

    public boolean uploadCorrAlertGuard() {
        return getState().equals(APIFunctionalityStates.API_INITIALISED) || getState().equals(APIFunctionalityStates.UPLOADED_ALERTS) || getState().equals(APIFunctionalityStates.PURGED_ALERTS);
    }
    public @Action void uploadCorrAlert() throws IOException {

        uploaded = true;
        purged = false;
        alertType = true;
        heading = true;
        description = true;
        url = true;
        imageUrl = true;
        postedBy = true;
        priceInCents = true;

        systemUnderTest.uploadCorrAlert();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            numOfAlerts ++;


        modelState = APIFunctionalityStates.UPLOADED_ALERTS;

        Assert.assertEquals("The model's uploaded state !== the system under test's state.", uploaded, systemUnderTest.isUploaded());
        Assert.assertEquals("The model's number of alerts !== the system under test's number of alerts.", numOfAlerts, systemUnderTest.getNumOfAlerts());
        Assert.assertEquals("The model's purged state !== the system under test's state.", purged, systemUnderTest.isPurged());
        Assert.assertEquals("The model's alert type state !== the system under test's state.", alertType, systemUnderTest.hasAlertType());
        Assert.assertEquals("The model's heading state !== the system under test's state.", heading, systemUnderTest.hasHeading());
        Assert.assertEquals("The model's heading state !== the system under test's state.", description, systemUnderTest.hasDescription());
        Assert.assertEquals("The model's description state !== the system under test's state.", alertType, systemUnderTest.hasDescription());
        Assert.assertEquals("The model's url state !== the system under test's state.", url, systemUnderTest.hasUrl());
        Assert.assertEquals("The model's image url state !== the system under test's state.", imageUrl, systemUnderTest.hasImageUrl());
        Assert.assertEquals("The model's posted by state !== the system under test's state.", postedBy, systemUnderTest.hasPostedBy());
        Assert.assertEquals("The model's price in cents state !== the system under test's state.", priceInCents, systemUnderTest.hasPriceInCents());
    }

    /*public boolean uploadIncorrAlertGuard() {
        return getState().equals(APIFunctionalityStates.API_INITIALISED) || getState().equals(APIFunctionalityStates.UPLOADED_ALERTS) || getState().equals(APIFunctionalityStates.PURGED_ALERTS);
    }
    public @Action void uploadIncorrAlert() throws IOException {
        uploaded = false;
        purged = false;
        alertType = false;
        heading = false;
        description = false;
        url = false;
        imageUrl = false;
        postedBy = false;
        priceInCents = false;
        systemUnderTest.uploadIncorrAlert();
        modelState = APIFunctionalityStates.UPLOADED_ALERTS;

        Assert.assertEquals("The model's uploaded state !== the system under test's state.", uploaded, systemUnderTest.isUploaded());
        Assert.assertEquals("The model's number of alerts !== the system under test's number of alerts.", numberOfAlerts, systemUnderTest.getNumberOfAlerts());
        Assert.assertEquals("The model's purged state !== the system under test's state.", purged, systemUnderTest.isPurged());
        Assert.assertEquals("The model's alert type state !== the system under test's state.", alertType, systemUnderTest.hasAlertType());
        Assert.assertEquals("The model's heading state !== the system under test's state.", heading, systemUnderTest.hasHeading());
        Assert.assertEquals("The model's heading state !== the system under test's state.", description, systemUnderTest.hasDescription());
        Assert.assertEquals("The model's description state !== the system under test's state.", alertType, systemUnderTest.hasDescription());
        Assert.assertEquals("The model's url state !== the system under test's state.", url, systemUnderTest.hasUrl());
        Assert.assertEquals("The model's image url state !== the system under test's state.", imageUrl, systemUnderTest.hasImageUrl());
        Assert.assertEquals("The model's posted by state !== the system under test's state.", postedBy, systemUnderTest.hasPostedBy());
        Assert.assertEquals("The model's price in cents state !== the system under test's state.", priceInCents, systemUnderTest.hasPriceInCents());
    }*/
    public boolean purgeGuard() {
        return getState().equals(APIFunctionalityStates.API_INITIALISED) || getState().equals(APIFunctionalityStates.UPLOADED_ALERTS) || getState().equals(APIFunctionalityStates.PURGED_ALERTS);
    }
    public @Action void purge() throws IOException {
        systemUnderTest.purge();

        uploaded = false;
        purged = true;
        numOfAlerts = 0;

        modelState = APIFunctionalityStates.PURGED_ALERTS;

        Assert.assertEquals("The model's uploaded state !== the system under test's state.", uploaded, systemUnderTest.isUploaded());
        Assert.assertEquals("The model's number of alerts !== the system under test's number of alerts.", numOfAlerts, systemUnderTest.getNumOfAlerts());
        Assert.assertEquals("The model's purged state !== the system under test's state.", purged, systemUnderTest.isPurged());
    }
    @Test
    public void APIFunctionalityModelTestRunner() throws IOException {
        final Tester apiTest = new RandomTester(new APIFunctionalityModelTest());
        apiTest.setRandom(new Random());
        apiTest.buildGraph();
        apiTest.addListener(new StopOnFailureListener());
        apiTest.addListener("verbose");
        apiTest.addCoverageMetric(new TransitionPairCoverage());
        apiTest.addCoverageMetric(new StateCoverage());
        apiTest.addCoverageMetric(new ActionCoverage());
        apiTest.generate(20);
        apiTest.printCoverage();
    }
}
