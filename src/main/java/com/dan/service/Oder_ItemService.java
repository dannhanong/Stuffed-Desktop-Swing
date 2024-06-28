package com.dan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.dan.model.Oder;
import com.dan.model.Oder_Item;
import com.dan.model.Product;
import com.dan.util.DBUtils;

public class Oder_ItemService {
    public static void addOder_Item(Oder_Item oder_Item){
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, oder_Item.getOder().getId());
            stmt.setString(2, oder_Item.getProduct().getId());
            stmt.setInt(3, oder_Item.getQuantity());
            stmt.setInt(4, oder_Item.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOder_Item(int id){
        String sql = "DELETE FROM order_items WHERE id = ?";

        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateOder_Item(Oder_Item oder_Item){
        String sql = "UPDATE order_items SET quantity = ?, price = ? WHERE id = ?";

        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, oder_Item.getQuantity());
            stmt.setInt(2, oder_Item.getPrice());
            stmt.setInt(3, oder_Item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Oder_Item checkByOderAndItem(Oder oder, Product product){
        Oder_Item oderItem = null;
        String sql = "SELECT * FROM order_items WHERE order_id = ? AND product_id = ?";

        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, oder.getId());
            stmt.setString(2, product.getId());
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    oderItem = new Oder_Item();
                    oderItem.setId(rs.getInt("id"));
                    oderItem.setOder(oder);
                    oderItem.setProduct(product);
                    oderItem.setQuantity(rs.getInt("quantity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oderItem;
    }

    public static List<Oder_Item> getOder_ItemByUser(String account){
        List<Oder_Item> oderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = (SELECT id FROM orders WHERE user_id = (SELECT id FROM users WHERE account = ?))";

        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Oder_Item oderItem = new Oder_Item();
                    oderItem.setId(rs.getInt("id"));
                    oderItem.setOder(OderService.getOderByUser(account));
                    oderItem.setProduct(ProductService.getProductById(rs.getString("product_id")));
                    oderItem.setQuantity(rs.getInt("quantity"));
                    oderItem.setPrice(rs.getInt("price"));
                    oderItems.add(oderItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oderItems;
    }

    public static Oder_Item getOder_ItemByOderAndProduct(Oder oder, Product product){
        Oder_Item oderItem = new Oder_Item();
        String sql = "SELECT * FROM order_items WHERE order_id = ? AND product_id = ?";

        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, oder.getId());
            stmt.setString(2, product.getId());
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    oderItem = new Oder_Item();
                    oderItem.setId(rs.getInt("id"));
                    oderItem.setOder(oder);
                    oderItem.setProduct(product);
                    oderItem.setQuantity(rs.getInt("quantity"));
                    oderItem.setPrice(rs.getInt("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oderItem;
    }

    public static Oder_Item getOder_ItemById(int id){
        Oder_Item oderItem = new Oder_Item();
        String sql = "SELECT * FROM order_items WHERE id = ?";

        try (Connection conn = DBUtils.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    oderItem.setId(rs.getInt("id"));
                    oderItem.setOder(OderService.getOderById(rs.getInt("order_id")));
                    oderItem.setProduct(ProductService.getProductById(rs.getString("product_id")));
                    oderItem.setQuantity(rs.getInt("quantity"));
                    oderItem.setPrice(rs.getInt("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oderItem;
    }
}
