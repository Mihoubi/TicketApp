package com.example.jdbc;


import java.sql.*;

public class TestConnection {
    private static Connection connection;


    public static void main(String [] args) throws SQLException {

        try {
            OpenDatabaseConnection();
            deleteData("%");
            CreateData("JavaScript", 3);
            CreateData("C++", 4);
            CreateData("java", 5);
            readData();
            deleteData("C++");
        }
        finally{
            CloseDatabaseConnection();
        }



    }

    private static void InitConnectionPool() {
    }


    private static void CreateData(String name, int rating) throws SQLException {
        System.out.println("Creating Data .... ");
        try (PreparedStatement statement = connection.prepareStatement("""
                            INSERT INTO programinglanguage(name, rating) 
                            VALUES(?,?)

                    """)){
        statement.setString(1, name);
        statement.setDouble(2, rating);
        int rowInserted = statement.executeUpdate();
        statement.close();
        System.out.println("Rows inserted " + rowInserted);
    }

    }

    private static void readData() {
        System.out.println("Reading Data .....");
        try (PreparedStatement statement = connection.prepareStatement("""
                SELECT * FROM  programinglanguage
                ORDER by rating desc
                """))
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString(1);
                double rating = resultSet.getDouble(2);
                System.out.println("\t>" + name + ":" + rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void updateData(String name, int newRating) throws SQLException {
        System.out.println("Updating  Data .....");
        try (PreparedStatement statement = connection.prepareStatement("""
                UPDATE  programinglanguage
                SET rating = ?
                WHERE name = ?
                """)){
            statement.setDouble(2, newRating);
            statement.setString(1, name);

            int rowUpdated = statement.executeUpdate();
            System.out.println("Rows inserted " + rowUpdated);


        }
    }


    private static void deleteData(String nameExpression) {
        System.out.println("Deleting   Data .....");
       try (PreparedStatement statement = connection.prepareStatement("""
                DELETE FROM programinglanguage
                where name LIKE ?
                """)){
            statement.setString(1, nameExpression);
            int rowDeleted = statement.executeUpdate();
           System.out.println("Rows Deleted  " + rowDeleted);
        } catch (SQLException e) {
           e.printStackTrace();
       }
    }



    private static void OpenDatabaseConnection() throws SQLException {
        System.out.println("Connecting to the database .....");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "1234");
         System.out.println("Connection is valid " + connection.isValid(10));
    }

    private static void CloseDatabaseConnection() throws SQLException {
        System.out.println("Disconnecting from the database .....");
        connection.close();
        System.out.println("Connection is valid " + connection.isValid(10));
    }

}
