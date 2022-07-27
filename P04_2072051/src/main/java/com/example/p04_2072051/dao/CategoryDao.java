package com.example.p04_2072051.dao;

import com.example.p04_2072051.entity.Category;
import com.example.p04_2072051.utility.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao implements DaoInterface<Category> {

    @Override
    public ObservableList<Category> getData() {
        ObservableList<Category> clist;
        clist = FXCollections.observableArrayList();

        Connection conn = MyConnection.getConnection();
        String kueri = "SELECT * FROM category";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kueri);
            ResultSet hasil = ps.executeQuery();
            while(hasil.next()){
                Integer id = hasil.getInt("id");
                String name = hasil.getString("name");
                Category c = new Category(id, name);
                clist.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clist;
    }

    @Override
    public void addData(Category data) {
        Connection conn = MyConnection.getConnection();
        String kueri = "INSERT INTO category VALUES(?, ?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kueri);
            ps.setInt(1, data.getId());
            ps.setString(2, data.getName());
            int hasil = ps.executeUpdate();
            if (hasil > 0) {
                System.out.println("berhasil");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setData(Category data) {
        Connection conn = MyConnection.getConnection();
        String kueri = "UPDATE category SET name = ? WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(kueri);
            ps.setString(1, data.getName());
            ps.setInt(2, data.getId());
            int hasil = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delData(Category data) {
        Connection conn = MyConnection.getConnection();
        String kueri = "DELETE FROM category WHERE id = ?";
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
