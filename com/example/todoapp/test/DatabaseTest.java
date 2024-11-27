package com.example.todoapp.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) {
    	String url = "jdbc:postgresql://localhost:5432/todo_app"; // DBのURL

    	String user = "postgres"; // ユーザー名
    	String password = "fullhouse8"; // パスワード


        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("接続成功！");
            }
        } catch (SQLException e) {
            System.out.println("接続失敗！");
            e.printStackTrace();
        }
    }
}
