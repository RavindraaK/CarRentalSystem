package dao;

import java.sql.*;

import utils.DatabaseConnection;

public class CustomerDAO {
    public String addCustomer(String name) throws SQLException {
        String getLastCustomerIdQuery = "SELECT MAX(customer_id) AS last_id FROM customers";
        String addCustomerQuery = "INSERT INTO customers (customer_id, name) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getLastCustomerIdQuery)) {

            String customerId = "CUS1";
            if (resultSet.next() && resultSet.getString("last_id") != null) {
                int lastId = Integer.parseInt(resultSet.getString("last_id").substring(3));
                customerId = "CUS" + (lastId + 1);
            }

            try (PreparedStatement addCustomerStmt = connection.prepareStatement(addCustomerQuery)) {
                addCustomerStmt.setString(1, customerId);
                addCustomerStmt.setString(2, name);
                addCustomerStmt.executeUpdate();
            }
            return customerId;
        }
    }
}
