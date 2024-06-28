package com.dan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dan.model.Oder;
import com.dan.util.DBUtils;
import com.mysql.cj.xdevapi.PreparableStatement;

public class OderService {
    public static void addOder(Oder oder){
        String sql = "INSERT INTO orders (user_id, total) VALUES (?, ?)";

        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, oder.getUser().getId());
            stmt.setInt(2, 0);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOder(int id){
        String sql = "UPDATE orders SET total = ? WHERE id = ?";
        String sql2 = "DELETE FROM order_items WHERE order_id = ?";
        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql);
            PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
            stmt.setInt(1, 0);
            stmt.setInt(2, id);
            stmt2.setInt(1, id);
            stmt2.executeUpdate();
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateOder(Oder oder){
        String sql = "UPDATE orders SET total = ? WHERE id = ?";

        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, oder.getTotal());
            stmt.setInt(2, oder.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Oder getOderByUser(String account){
        Oder oder = new Oder();
        String sql = "SELECT * FROM orders WHERE user_id = (SELECT id FROM users WHERE account = ?)";
        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    oder = new Oder();
                    oder.setId(rs.getInt("id"));
                    oder.setUser(UserService.getUser(account));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oder;
    }

    public static int getTotal(int id){
        int total = 0;
        String sql = "SELECT SUM(price) FROM order_items WHERE order_id = ?";
        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt("SUM(price)");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public static Oder getOderById(int id){
        Oder oder = new Oder();
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    oder = new Oder();
                    oder.setId(rs.getInt("id"));
                    oder.setUser(UserService.getUserById(rs.getInt("user_id")));
                    oder.setTotal(rs.getInt("total"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oder;
    }
}
