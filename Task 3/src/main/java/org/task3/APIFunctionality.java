package org.task3;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.google.gson.Gson;
import java.io.IOException;

public class APIFunctionality {
    final OkHttpClient httpClient = new OkHttpClient();
    private int numOfAlerts = 0;
    private boolean uploaded = false;
    private boolean purged = false;

    //instantiation of alert attributes
    private boolean alertType = false;
    private boolean heading = false;
    private boolean description  = false;
    private boolean url = false;
    private boolean imageUrl = false;
    private boolean postedBy = false;
    private boolean priceInCents = false;

    int getNumOfAlerts() {
        return numOfAlerts;
    }

    boolean isUploaded(){
        return uploaded;
    }

    boolean isPurged() {
        return purged;
    }

    boolean hasAlertType(){
        return alertType;
    }

    boolean hasHeading(){
        return heading;
    }

    boolean hasDescription(){
        return description;
    }

    boolean hasUrl() {
        return url;
    }

    boolean hasImageUrl() {
        return imageUrl;
    }

    boolean hasPostedBy() {
        return postedBy;
    }

    boolean hasPriceInCents() {
        return priceInCents;
    }

    public void initialPurge() throws IOException {
        Request req = new Request.Builder().url("https://api.marketalertum.com/Alert?userId=94817701-f7f3-4f5d-9419-c6da1b8d6d84").delete().build();
        try (Response response = httpClient.newCall(req).execute()) {}
    }

    void purge() throws IOException {
        Request req = new Request.Builder().url("https://api.marketalertum.com/Alert?userId=94817701-f7f3-4f5d-9419-c6da1b8d6d84").delete().build();
        try (Response response = httpClient.newCall(req).execute()) {}

        // Get MarketAlert status
        GetResponse getResponse = getRequestFromMarketAlerts();
        if (getResponse != null && getResponse.eventLogType == 1){
            purged = true;
            uploaded = false;
            numOfAlerts = getResponse.systemState.alerts.size();
        } else {
            purged = false;
        }
    }

    void uploadCorrAlert() throws IOException {
        Alert corrAlert = new Alert(
                6,
                "Iphone 11",
                "Iphone 11 64 gb front screen has small cracks",
                "https://www.maltapark.com/item/details/9545369",
                "https://www.maltapark.com/asset/itemphotos/9545369/9545369_1.jpg?_ts=1",
                34000
        );

        // Upload alert
        String json = new Gson().toJson(corrAlert);

        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request req = new Request.Builder()
                .url("https://api.marketalertum.com/Alert")
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(req).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        }

        GetResponse getResponse = getRequestFromMarketAlerts();
        if (getResponse != null && getResponse.eventLogType == 0){
            purged = false;
            uploaded = true;
            numOfAlerts = getResponse.systemState.alerts.size();
            Alert finalAlert = getResponse.systemState.alerts.get(numOfAlerts - 1);

            //Check that alerts have all the required attributes and that none are empty or invalid
            alertType = finalAlert.alertType >= 1 && finalAlert.alertType <= 6;

            heading = !finalAlert.heading.equals("");

            description = !finalAlert.description.equals("");

            url = !finalAlert.url.equals("");

            imageUrl = !finalAlert.imageURL.equals("");

            postedBy = !finalAlert.postedBy.equals("");

            priceInCents = finalAlert.priceInCents > 0;
        } else {
            uploaded = false;
            purged = false;
            alertType = false;
            heading = false;
            description = false;
            url = false;
            imageUrl = false;
            postedBy = false;
            priceInCents = false;
        }
    }

    void uploadIncorrAlert() throws IOException {
        Alert incorrAlert = new Alert(
                -1,
                "",
                "",
                "",
                "",
                0
        );

        // Upload alert
        String json = new Gson().toJson(incorrAlert);

        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request req = new Request.Builder()
                .url("https://api.marketalertum.com/Alert")
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(req).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            //display an error message to the user
        }


        GetResponse getResponse = getRequestFromMarketAlerts();

        if (getResponse != null && getResponse.eventLogType == 0){
            purged = false;
            uploaded = false;
            numOfAlerts = getResponse.systemState.alerts.size();

            Alert finalAlert = getResponse.systemState.alerts.get(numOfAlerts - 1);

            // Check that alerts have all attributes required
            alertType = finalAlert.alertType >= 1 && finalAlert.alertType <= 6;

            heading = !finalAlert.heading.equals("");

            description = !finalAlert.description.equals("");

            url = !finalAlert.url.equals("");

            imageUrl = !finalAlert.imageURL.equals("");

            postedBy = !finalAlert.postedBy.equals("");

            priceInCents = finalAlert.priceInCents > 0;

        } else {
            uploaded = false;
            purged = false;
            alertType = false;
            heading = false;
            description = false;
            url = false;
            imageUrl = false;
            postedBy = false;
            priceInCents = false;
        }
    }

    GetResponse getRequestFromMarketAlerts() throws IOException {
        Request req = new Request.Builder()
                .url("https://api.marketalertum.com/EventsLog/94817701-f7f3-4f5d-9419-c6da1b8d6d84")
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = httpClient.newCall(req).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // composes json response
            String jsonString = response.body().string();

            GetResponse[] responseArray = new Gson().fromJson(jsonString, GetResponse[].class);

            // return object
            return responseArray[0];
        }
    }
}
