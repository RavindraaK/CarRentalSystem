package models;

public class Customer {
    private String customerId;
    private String name;

    // Constructor
    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
