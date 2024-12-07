package dao;

import java.sql.*;

import utils.DatabaseConnection;

public class RentalDAO {
    public void addRental(String carId, String customerId, int rentalDays) throws SQLException {
        String query = "INSERT INTO rentals (car_id, customer_id, rental_days) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, carId);
            statement.setString(2, customerId);
            statement.setInt(3, rentalDays);
            statement.executeUpdate();
        }
    }

    public void deleteRental(String carId) throws SQLException {
        String query = "DELETE FROM rentals WHERE car_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, carId);
            statement.executeUpdate();
        }
    }
}
