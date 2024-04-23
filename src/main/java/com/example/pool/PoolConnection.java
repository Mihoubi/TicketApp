package com.example.pool;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;

import java.sql.*;

public class PoolConnection {

    private static HikariDataSource dataSource;

    public static void main(String [] args) throws SQLException {

        try {
            // OpenDatabaseConnection();
            InitConnectionPool();
            deleteData("%");
            CreateData("JavaScript", 3);
            CreateData("C++", 4);
            CreateData("java", 5);
            readData();
            updateData("java", 10);
            readData();
            deleteData("java");
            readData();
        }
        finally{
            CloseDatabaseConnectionPool();
        }



    }


    private static void CreateData(String name, int rating) throws SQLException {
        System.out.println("Creating Data .... ");
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("""
                            INSERT INTO programinglanguage(name, rating) 
                            VALUES(?,?)

                    """)){
            statement.setString(1, name);
            statement.setInt(2, rating);
            int rowInserted = statement.executeUpdate();
            statement.close();
            System.out.println("Rows inserted " + rowInserted);
        }

    }

    private static void readData() throws SQLException {
        System.out.println("Reading Data .....");
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT * FROM  programinglanguage
                    ORDER by rating desc
                    """)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString(1);
                    int rating = resultSet.getInt(2);
                    System.out.println("\t>" + name + ":" + rating);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void updateData(String name, int newRating) throws SQLException {
            System.out.println("Updating  Data .....");
            try (Connection connection = dataSource.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement("""
                        UPDATE  programinglanguage
                        SET rating = ?
                        WHERE name = ?
                        """)) {
                    statement.setInt(1, newRating);
                    statement.setString(2, name);

                    int rowUpdated = statement.executeUpdate();
                    System.out.println("Rows inserted " + rowUpdated);


                }
            }
        }


    private static void deleteData(String nameExpression) throws SQLException {
                System.out.println("Deleting   Data .....");
                try (Connection connection = dataSource.getConnection()) {
                    try (PreparedStatement statement = connection.prepareStatement("""
                            DELETE FROM programinglanguage
                            where name LIKE ?
                            """)) {
                        statement.setString(1, nameExpression);
                        int rowDeleted = statement.executeUpdate();
                        System.out.println("Rows Deleted  " + rowDeleted);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }



    private static void InitConnectionPool() {
        System.out.println("Connecting to the database .....");
        dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(10);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/book");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
    }

    private static void CloseDatabaseConnectionPool()  {
        System.out.println("Disconnecting from the database .....");
        dataSource.close();
    }

}
