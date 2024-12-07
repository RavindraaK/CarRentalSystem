package services;

import java.sql.SQLException;
import java.util.Scanner;

import dao.CarDAO;
import dao.CustomerDAO;
import dao.RentalDAO;

public class CarRentalSystem {
    private final CarDAO carDAO = new CarDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final RentalDAO rentalDAO = new RentalDAO();

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                if (choice == 1) {
                    rentCar(scanner);
                } else if (choice == 2) {
                    returnCar(scanner);
                } else if (choice == 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Database error occurred.");
            }
        }

        System.out.println("\nThank you for using the Car Rental System!");
    }

    private void rentCar(Scanner scanner) throws SQLException {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.println("\nAvailable Cars:");
        carDAO.getAvailableCars().forEach(car -> {
            System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
        });

        System.out.print("\nEnter the car ID you want to rent: ");
        String carId = scanner.nextLine();

        System.out.print("Enter the number of days for rental: ");
        int rentalDays = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String customerId = customerDAO.addCustomer(customerName);
        rentalDAO.addRental(carId, customerId, rentalDays);
        carDAO.updateCarAvailability(carId, false);

        System.out.println("\nCar rented successfully.");
    }

    private void returnCar(Scanner scanner) throws SQLException {
        System.out.print("Enter the car ID you want to return: ");
        String carId = scanner.nextLine();

        rentalDAO.deleteRental(carId);
        carDAO.updateCarAvailability(carId, true);

        System.out.println("Car returned successfully.");
    }
}
