package main;

public class Alert {
	// Attributes
	protected int alertType;
    protected String heading;
    protected String description;
    protected String url;
    protected String imageUrl;
    protected int priceInCents;
    protected String postedBy = "94817701-f7f3-4f5d-9419-c6da1b8d6d84";
    
    public Alert(int alertType, String heading, String description, String url, String imageUrl, int priceInCents) {
        this.alertType = alertType;
        this.heading = heading;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.priceInCents = priceInCents;
    }
    
    public Alert(){ } //constructor with empty parameters
}