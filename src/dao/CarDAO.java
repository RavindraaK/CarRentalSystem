package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Car;
import utils.DatabaseConnection;

public class CarDAO {
    public List<Car> getAvailableCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars WHERE is_available = TRUE";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                cars.add(new Car(
                        resultSet.getString("car_id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getDouble("base_price_per_day"),
                        resultSet.getBoolean("is_available")
                ));
            }
        }
        return cars;
    }

    public void updateCarAvailability(String carId, boolean isAvailable) throws SQLException {
        String query = "UPDATE cars SET is_available = ? WHERE car_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, isAvailable);
            statement.setString(2, carId);
            statement.executeUpdate();
        }
    }

    public Car getCarById(String carId) throws SQLException {
        String query = "SELECT * FROM cars WHERE car_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, carId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Car(
                            resultSet.getString("car_id"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getDouble("base_price_per_day"),
                            resultSet.getBoolean("is_available")
                    );
                }
            }
        }
        return null;
    }
}
