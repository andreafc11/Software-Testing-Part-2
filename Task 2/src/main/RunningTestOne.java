package main;

import java.io.IOException;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;

public class RunningTestOne {//Tests uploading of alerts
	
	final Random random = new Random();
	final OkHttpClient httpClient = new OkHttpClient();
	
	public void uploadCorrAlert() {
		System.out.println("Alert has been successfully uploaded");
	}
	
	public void uploadIncAlert() {
		System.out.println("Alert uploaded (incorrect)");
	}
	
	public void purge() {
		System.out.println("---Alerts purged---");
	}
	
	public void attemptUploadCorrectAlert() throws IOException {
		// Generate a random alert to upload
		int maxim = 5;
		int minim = 0;
		int randomNum = random.nextInt((maxim - minim) + 1) + minim;
		
		Alert alert = new Alert();
		
		switch (randomNum) {
			case 0://car
				Alert alertCar = new Alert(
			            1,
			            "Renault Kadjar",
			            "Renault Kadjar 2016 model. Diesel, manual transmission and optional 4w drive. Only 61,000 kms in very good condition. One owner, bought new in Malta and always serviced at the agent.",
			            "https://www.maltapark.com/item/details/9545428",
			            "https://www.maltapark.com/asset/itemphotos/9545428/9545428_1.jpg?_ts=4",
			            1700000
				    );
				System.out.println("Alert type 1 - Car");
				alert = alertCar;
				break;
			case 1://boat
				Alert alertBoat = new Alert(
			            2,
			            "Trimarchi 57 S. - Tohatsu 115 hp",
			            "Trimarchi 57 S (5.70 mt.) Year 2021 ° with Tohatsu 115 hp engine year 2022 ° with only 30 hours of motion",
			            "https://www.maltapark.com/item/details/9516448",
			            "https://www.maltapark.com/asset/itemphotos/9516448/9516448_1.jpg?_ts=4",
			            2400000
				    );
				System.out.println("Alert type 2 - Boat");
				alert = alertBoat;
				break;
			case 2://rent
				Alert alertRent = new Alert(
						3,
			            "M Xlokk 3 Bedroomed Apartment For Rent",
			            "Modern Apartment comprising of a Good Sized Airconditioned Open Plan Kitchen/ Living/ Dining including T.V, 3 Bedrooms (Main with A/c & Shower Ensuite), Main Bathroom, Washroom, Back Yard and a Front Balcony. €800 per month Longlet.",
			            "https://www.maltapark.com/item/details/9144922",
			            "https://www.maltapark.com/asset/itemphotos/9144922/9144922_1.jpg?_ts=8",
			            80000
				    );
				System.out.println("Alert type 3 - Property for rent");
				alert = alertRent;
				break;
			case 3://sale
				Alert alertSale = new Alert(
						4,
				        "Flats & Penthouse - DIRECT FROM OWNER",
				        "Large apartments in the beautiful village of Zurrieq.",
				        "https://www.maltapark.com/item/details/9533309",
				        "https://www.maltapark.com/asset/itemphotos/9533309/9533309_5.jpg?_ts=7",
				        19500000
				    );
				System.out.println("Alert type 4 - Property for Sale");
				alert = alertSale;
				break;
			case 4://toy
				Alert alertToy = new Alert(
						5,
			            "Gaucho - Toy Car for kids",
			            "Toy car for kids - needs battery - otherwise in excellent condition",
			            "https://www.maltapark.com/item/details/9475244",
			            "https://www.maltapark.com/asset/itemphotos/9475244/9475244_1.jpg?_ts=1",
			            12500
				    );
				System.out.println("Alert type 5 - Toy");
				alert = alertToy;
				break;
			case 5://electronic
				Alert alertElectronic = new Alert(
						6,
			            "Macbook 2015",
			            "512gb SSD Macbook in great condition",
			            "https://www.maltapark.com/item/details/9540276",
			            "https://www.maltapark.com/asset/itemphotos/9540276/9540276_1.jpg?_ts=1",
			            45000
				    );
				System.out.println("Alert type 6 - Electronics");
				alert = alertElectronic;
				break;
		}
		
		// Upload alert
		String json = new Gson().toJson(alert);
        
        RequestBody body = RequestBody.create(json,MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("https://api.marketalertum.com/Alert")
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        } 
		
	}
	
	public void attemptPurgeAlerts() throws IOException {//purges alerts
		Request request = new Request.Builder().url("https://api.marketalertum.com/Alert?userId=94817701-f7f3-4f5d-9419-c6da1b8d6d84").delete().build();

        try (Response response = httpClient.newCall(request).execute()) {}
	}
	
	public void attemptUploadIncorrectAlert() throws IOException {//tries to upload alerts with invalid attributes
		Alert incorrectAlert = new Alert(
	            -1,
	            "",
	            "",
	            "",
	            "",
	            0
		    );
		
		// Upload alert
		String json = new Gson().toJson(incorrectAlert);
        
        RequestBody body = RequestBody.create(
    		json,
    		MediaType.parse("application/json; charset=utf-8")
		);

        Request request = new Request.Builder()
                .url("https://api.marketalertum.com/Alert")
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        }
	}
	
	public void run(final API api) throws IOException {
		final Random rand = new Random();
		
		// Assuming every time we start a run no alerts already exist and that the number of alerts is 0
		this.attemptPurgeAlerts();
		api.setApiAttributesFromGetRequest();
		int alertCounter = api.alertCounter;
		System.out.println("Initial number of alerts: " + alertCounter);
		
		while(true){
			// Giving more chance to upload a correct alert rather than purging or uploading an incorrect one
			final int randomNum = rand.nextInt(6);
					
			switch(randomNum){
			case 1:
				this.attemptUploadIncorrectAlert();
				api.setApiAttributesFromGetRequest();
				if (api.eventLogType == 0 && api.alertCounter == (alertCounter + 1)){
					// Should never get to this point but since the system is to be treated as a black box this had to be tested
					alertCounter = api.alertCounter;
					this.uploadIncAlert();
				}
				break;
			case 2:
				this.attemptPurgeAlerts();
				api.setApiAttributesFromGetRequest();
				if (api.eventLogType == 1 && api.alertCounter == 0){
					alertCounter = api.alertCounter;
					this.purge();
				}
				break;
			default:
				this.attemptUploadCorrectAlert();
				api.setApiAttributesFromGetRequest();
				if (api.eventLogType == 0 && api.alertCounter == (alertCounter + 1)){
					alertCounter = api.alertCounter;
					this.uploadCorrAlert();
				}
				break;
			}
			
			// Small pause
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) throws IOException {//main method
		final RunningTestOne r = new RunningTestOne();
		final API api = r.new API();
		r.run(api);
	}
	
	public class API {
		private int alertCounter;
		private int eventLogType;
		
		public API() {}
		

		public void setApiAttributesFromGetRequest() throws IOException { //uses the attributes in GetRequest
			Request request = new Request.Builder()
            .url("https://api.marketalertum.com/EventsLog/94817701-f7f3-4f5d-9419-c6da1b8d6d84")
            .addHeader("Content-Type", "application/json")
            .build();

		    try (Response response = httpClient.newCall(request).execute()) {
		
		        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		        // Get response body
		        String jsonString = response.body().string();
		
		        GetResponse[] respArray = new Gson().fromJson(jsonString, GetResponse[].class);
		        
		        this.alertCounter = respArray[0].systemState.alerts.size();
		        this.eventLogType = respArray[0].eventLogType;
		    }
			
		}
	}
	
}