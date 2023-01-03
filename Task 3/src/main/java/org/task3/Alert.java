package org.task3;

public class Alert {
    //Attributes
    public String postedBy = "94817701-f7f3-4f5d-9419-c6da1b8d6d84";
    public int alertType;
    public String heading;
    public String description;
    public String url;
    public String imageURL;
    public int priceInCents;

    public Alert(int alertType, String heading, String description, String url, String imageUrl, int priceInCents) {
        this.alertType = alertType;
        this.heading = heading;
        this.description = description;
        this.url = url;
        this.imageURL = imageUrl;
        this.priceInCents = priceInCents;
    }

    public Alert(){ }//constructor with empty parameters
}