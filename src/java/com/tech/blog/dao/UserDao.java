package com.tech.blog.dao;

import com.tech.blog.entities.User;
import java.sql.*;

public class UserDao {

    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    //method to insert user to database
    public boolean saveUser(User user) {
        boolean f = false;
        try {

            //user to ==> database
            String query = "insert into user(name,email,password,gender,about) values(?,?,?,?,?);";
            PreparedStatement pst = this.con.prepareStatement(query);

            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getGender());
            pst.setString(5, user.getAbout());

            //execute Query bu executeUpdate method
            pst.executeUpdate();
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    //get user by useremail and userpassword
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;

        try {
            String query = "select * from user where email=? and password=?;";
            PreparedStatement pst = this.con.prepareStatement(query);

            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new User();

                //data from db
                String name = rs.getString("name");

                //set to user object
                user.setName(name);
                user.setEmail(rs.getString("email"));
                user.setId(rs.getInt("id"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAbout(rs.getString("about"));
                user.setRdate(rs.getTimestamp("rdate"));
                user.setProfile(rs.getString("profile"));

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return user;
    }

    public boolean updateUser(User user) {
        boolean f = false;
        try {

            String query = "update user set name=? , email=?, password=?, gender=?, about=?, profile=? where id=?;";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getGender());
            pst.setString(5, user.getAbout());
            pst.setString(6, user.getProfile());
            pst.setInt(7, user.getId());

            pst.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public User getUserByUserId(int userId) {
        User user = null;

        String q = "select * from user where id=?";
        
        try {
            PreparedStatement pst = this.con.prepareStatement(q);
            pst.setInt(1, userId);
            ResultSet set = pst.executeQuery();
            if (set.next()) {
                user = new User();

                //data from db
                String name = set.getString("name");

                //set to user object
                user.setName(name);
                user.setEmail(set.getString("email"));
                user.setId(set.getInt("id"));
                user.setPassword(set.getString("password"));
                user.setGender(set.getString("gender"));
                user.setAbout(set.getString("about"));
                user.setRdate(set.getTimestamp("rdate"));
                user.setProfile(set.getString("profile"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
