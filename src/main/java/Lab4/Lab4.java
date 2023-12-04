package Lab4;

import Lab4.entity.TripOffering;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Lab4 {
    public static void main(String[] args) {
        SpringApplication.run(Lab4.class, args);

        String jdbcUrl = "jdbc:mysql://localhost:3306/lab4",
                username = "root",
                password = "password";

        // JDBC objects
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
//            q1(connection);
//            q2a(connection);
//            q2b(connection);
//            q2c(connection);
//            q2d(connection);
//            q3(connection);
//            q4(connection);
//            q5(connection);
//            q6(connection);
//            q7(connection);
            q8(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void q1(Connection connection) throws SQLException {
        String startLocation = "City A";
        String destination = "City B";
        String date = "2023-01-01";

        // Create SQL query
        String sqlQuery = "SELECT TOF.trip_number, TOF.date, TOF.scheduled_start_time, TOF.scheduled_arrival_time, TOF.driver_name, TOF.bus_id " +
                "FROM trip_offering TOF " +
                "JOIN Trip T ON TOF.trip_number = T.trip_number " +
                "WHERE T.start_location_name = ? AND T.destination_name = ? AND TOF.date = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            // Set parameters
            preparedStatement.setString(1, startLocation);
            preparedStatement.setString(2, destination);
            preparedStatement.setString(3, date);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process result set
            while (resultSet.next()) {
                int tripNumber = resultSet.getInt("trip_number");
                String tripDate = resultSet.getString("date");
                String startTime = resultSet.getString("scheduled_start_time");
                String arrivalTime = resultSet.getString("scheduled_arrival_time");
                String driverName = resultSet.getString("driver_name");
                int busId = resultSet.getInt("bus_id");

                // Display the schedule information
                System.out.println("Trip Number: " + tripNumber);
                System.out.println("Date: " + tripDate);
                System.out.println("Scheduled Start Time: " + startTime);
                System.out.println("Scheduled Arrival Time: " + arrivalTime);
                System.out.println("Driver Name: " + driverName);
                System.out.println("Bus ID: " + busId);
                System.out.println();
            }
        }
    }

    public static void q2a(Connection connection) throws SQLException {
        int tripNumber = 1;
        String date = "2023-01-01";
        String scheduledStartTime = "08:00:00";

        String deleteQuery = "DELETE FROM trip_offering WHERE trip_number = ? AND date = ? AND scheduled_start_time = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, tripNumber);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, scheduledStartTime);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Trip Offering deleted successfully.");
            } else {
                System.out.println("No matching Trip Offering found.");
            }
        }
    }

    public static void q2b(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO trip_offering (trip_number, date, scheduled_start_time, scheduled_arrival_time, driver_name, bus_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        List<TripOffering> tripOfferings = new ArrayList<>();
        tripOfferings.add(new TripOffering(6, Date.valueOf("2023-01-06"), Time.valueOf("18:00:00"), Time.valueOf("19:00:00"), "Driver6", 106));
        tripOfferings.add(new TripOffering(7, Date.valueOf("2023-01-07"), Time.valueOf("19:30:00"), Time.valueOf("20:00:00"), "Driver6", 106));

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            for (TripOffering tripOffering : tripOfferings) {
                preparedStatement.setInt(1, tripOffering.getTripNumber());
                preparedStatement.setString(2, tripOffering.getDate().toString());
                preparedStatement.setString(3, tripOffering.getScheduledStartTime().toString());
                preparedStatement.setString(4, tripOffering.getScheduledArrivalTime().toString());
                preparedStatement.setString(5, tripOffering.getDriverName());
                preparedStatement.setInt(6, tripOffering.getBusId());

                preparedStatement.addBatch();
            }

            int[] rowsInserted = preparedStatement.executeBatch();
            System.out.println(rowsInserted.length + " Trip Offerings added successfully.");
        }
    }

    public static void q2c(Connection connection) throws SQLException {
        String newDriverName = "Driver7",
                date = "2023-01-02",
                scheduledStartTime = "09:30:00";
        int tripNumber = 2;

        String updateQuery = "UPDATE trip_offering SET driver_name = ? WHERE trip_number = ? AND date = ? AND scheduled_start_time = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newDriverName);
            preparedStatement.setInt(2, tripNumber);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, scheduledStartTime);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Driver changed successfully.");
            } else {
                System.out.println("No matching Trip Offering found.");
            }
        }
    }

    public static void q2d(Connection connection) throws SQLException {
        int newBusId = 103,
                tripNumber = 2;
        String date = "2023-01-02",
                scheduledStartTime = "09:30:00";

        String updateQuery = "UPDATE trip_offering SET bus_id = ? WHERE trip_number = ? AND date = ? AND scheduled_start_time = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, newBusId);
            preparedStatement.setInt(2, tripNumber);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, scheduledStartTime);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Bus changed successfully.");
            } else {
                System.out.println("No matching Trip Offering found.");
            }
        }
    }

    public static void q3(Connection connection) throws SQLException {
        int tripNumber = 2;

        String selectQuery = "SELECT * FROM trip_stop_info WHERE trip_number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, tripNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("No stops found for the given trip number.");
                } else {
                    while (resultSet.next()) {
                        int stopNumber = resultSet.getInt("stop_number");
                        int sequenceNumber = resultSet.getInt("sequence_number");
                        Time drivingTime = resultSet.getTime("driving_time");

                        System.out.println("Stop Number: " + stopNumber);
                        System.out.println("Sequence Number: " + sequenceNumber);
                        System.out.println("Driving Time: " + drivingTime);
                        System.out.println();
                    }
                }
            }
        }
    }

    public static void q4(Connection connection) throws SQLException {
        String driverName = "Driver6",
                date = "2023-01-01";

        String selectQuery = "SELECT TOF.trip_number, TOF.date, TOF.scheduled_start_time, TOF.scheduled_arrival_time, TOF.driver_name, TOF.bus_id " +
                "FROM trip_offering TOF " +
                "WHERE TOF.driver_name = ? AND TOF.date BETWEEN ? AND DATE_ADD(?, INTERVAL 6 DAY)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, driverName);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("No trips found for the given driver and date.");
                } else {
                    while (resultSet.next()) {
                        int tripNumber = resultSet.getInt("trip_number");
                        String scheduledDate = resultSet.getString("date");
                        String scheduledStartTime = resultSet.getString("scheduled_start_time");
                        String scheduledArrivalTime = resultSet.getString("scheduled_arrival_time");
                        String busId = resultSet.getString("bus_id");

                        System.out.println("Trip Number: " + tripNumber);
                        System.out.println("Scheduled Date: " + scheduledDate);
                        System.out.println("Scheduled Start Time: " + scheduledStartTime);
                        System.out.println("Scheduled Arrival Time: " + scheduledArrivalTime);
                        System.out.println("Bus ID: " + busId);
                        System.out.println();
                    }
                }
            }
        }
    }

    public static void q5(Connection connection) throws SQLException {
        String driverName = "Driver8",
                driverTelephoneNumber = "888-888-8888";

        String insertQuery = "INSERT INTO Driver (driver_name, driver_telephone_number) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, driverName);
            preparedStatement.setString(2, driverTelephoneNumber);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Driver added successfully.");
            } else {
                System.out.println("Failed to add driver.");
            }
        }
    }

    public static void q6(Connection connection) throws SQLException {
        int busId = 106,
                year = 2023;
        String model = "ModelC";

        String insertQuery = "INSERT INTO Bus (bus_id, model, year) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, busId);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, year);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Bus added successfully.");
            } else {
                System.out.println("Failed to add bus.");
            }
        }
    }

    public static void q7(Connection connection) throws SQLException {
        int busId = 106;

        String deleteQuery = "DELETE FROM Bus WHERE bus_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, busId);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Bus deleted successfully.");
            } else {
                System.out.println("No matching bus found.");
            }
        }
    }

    public static void q8(Connection connection) throws SQLException {
        int tripNumber = 9,
                stopNumber = 5,
                numberOfPassengerIn = 3,
                numberOfPassengerOut = 1;

        String date = "2023-01-10",
                scheduledStartTime = "09:00:00",
                scheduledArrivalTime = "10:00:00",
                actualStartTime = "09:30:00",
                actualArrivalTime = "11:00:00";

        String insertQuery = "INSERT INTO actual_trip_stop_info (trip_number, date, scheduled_start_time, stop_number, " +
                "scheduled_arrival_time, actual_start_time, actual_arrival_time, number_of_passenger_in, number_of_passenger_out) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, tripNumber);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, scheduledStartTime);
            preparedStatement.setInt(4, stopNumber);
            preparedStatement.setString(5, scheduledArrivalTime);
            preparedStatement.setString(6, actualStartTime);
            preparedStatement.setString(7, actualArrivalTime);
            preparedStatement.setInt(8, numberOfPassengerIn);
            preparedStatement.setInt(9, numberOfPassengerOut);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Actual trip stop info recorded successfully.");
            } else {
                System.out.println("Failed to record actual trip stop info.");
            }
        }
    }
}
