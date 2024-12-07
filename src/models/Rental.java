package models;

public class Rental {
    private int rentalId;
    private String carId;
    private String customerId;
    private int rentalDays;

    // Constructor
    public Rental(int rentalId, String carId, String customerId, int rentalDays) {
        this.rentalId = rentalId;
        this.carId = carId;
        this.customerId = customerId;
        this.rentalDays = rentalDays;
    }

    // Getters and Setters
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }
}
