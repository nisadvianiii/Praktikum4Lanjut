package com.example.p04_2072051.dao;

import com.example.p04_2072051.entity.Category;
import com.example.p04_2072051.entity.Items;
import com.example.p04_2072051.utility.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemsDao implements DaoInterface<Items> {
    private final Connection conn = MyConnection.getConnection();

    @Override
    public ObservableList<Items> getData() {
        ObservableList<Items> ilist;
        ilist = FXCollections.observableArrayList();

        Connection conn = MyConnection.getConnection();
        String kueri = "SELECT items.id AS id, items.name AS name, price, description, category_id, category.name AS category_name FROM items JOIN category ON items.category_id = category.id";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kueri);
            ResultSet hasil = ps.executeQuery();
            while(hasil.next()){
                Items i = new Items(
                        hasil.getInt("id"),
                        hasil.getString("name"),
                        hasil.getDouble("price"),
                        hasil.getString("description"),
                        new Category(
                                hasil.getInt("category_id"),
                                hasil.getString("category_name")
                        )
                );
                ilist.add(i);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ilist;
    }

    @Override
    public void addData(Items data) {
        Connection conn = MyConnection.getConnection();
        String kueri = "INSERT INTO items VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kueri);
            ps.setInt(1, data.getId());
            ps.setString(2, data.getName());
            ps.setDouble(3, data.getPrice());
            ps.setString(4, data.getDescription());
            ps.setInt(5, data.getCategory().getId());
            int hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setData(Items data) {
        Connection conn = MyConnection.getConnection();
        String kueri = "UPDATE items SET id = ?, name = ?, price = ?, description = ?, category_id = ? WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kueri);
            ps.setInt(1, data.getId());
            ps.setString(2, data.getName());
            ps.setDouble(3, data.getPrice());
            ps.setString(4, data.getDescription());
            ps.setInt(5, data.getCategory().getId());
            ps.setInt(6, data.getId());
            int hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delData(Items data) {
        Connection conn = MyConnection.getConnection();
        String kueri = "DELETE FROM items WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kueri);
            ps.setInt(1, data.getId());
            int hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

