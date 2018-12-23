package databases;

import models.Item;

import java.sql.*;
import java.util.ArrayList;

public class CustomerItemDB {
    public ArrayList loadData() {
        ArrayList<Item> items = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Item.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                DatabaseMetaData databaseMetaData = (DatabaseMetaData) connection.getMetaData();
                String query = "select * from Data";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString(1);
                    String type = resultSet.getString(2);
                    String name = resultSet.getString(3);
                    int quantity = resultSet.getInt(4);
                    int cost = resultSet.getInt(5);
                    String description = resultSet.getString(6);
                    items.add(new Item(id, type, name, quantity, cost, description));
                }
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void updateQtyItem(String id, int qty) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Item.db";
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "update * from Item set Quantity=\'" + qty + "\' where ID=\'" + id + "\'";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}