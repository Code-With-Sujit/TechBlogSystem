package com.tech.blog.dao;

import com.tech.blog.entities.Category;
import java.sql.*;
import java.util.ArrayList;
import com.tech.blog.entities.Post;
import java.util.List;

public class PostDao {

    Connection con;

    public PostDao(Connection con) {
        this.con = con;
    }

    public ArrayList<Category> getAllCategories() {

        ArrayList<Category> list = new ArrayList<Category>();
        try {

            String q = "select * from categories";
            Statement st = this.con.createStatement();
            ResultSet set = st.executeQuery(q);

            while (set.next()) {
                int cid = set.getInt("cid");
                String name = set.getString("name");
                String description = set.getString("description");
                Category c = new Category(cid, name, description);
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean savePost(Post p) {
        boolean f = false;
        try {

            String q = "insert into posts(pTitle,pContent,pCode,pPic,catId,userId) values(?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, p.getpTitle());
            pst.setString(2, p.getpContent());
            pst.setString(3, p.getpCode());
            pst.setString(4, p.getpPic());
            pst.setInt(5, p.getCatId());
            pst.setInt(6, p.getUserId());

            pst.executeUpdate();
            f = true;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return f;
    }

//get all the posts
    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();

        //fetch all the posts
        try {
            String q = "select * from posts order by pid desc";
            PreparedStatement pst = con.prepareStatement(q);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pTitle = rs.getString("pTitle");
                String pContent = rs.getString("pContent");
                String pCode = rs.getString("pCode");
                String pPic = rs.getString("pPic");
                Timestamp pDate = rs.getTimestamp("pDate");
                int catId = rs.getInt("catId");
                int userId = rs.getInt("userId");
                Post p = new Post(pid, pTitle, pContent, pCode, pPic, pDate, catId, userId);
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Post> getPostByCatId(int catId) {
        List<Post> list = new ArrayList<>();

        //fetch all the posts by id
        try {
            String q = "select * from posts where catId=?";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setInt(1, catId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pTitle = rs.getString("pTitle");
                String pContent = rs.getString("pContent");
                String pCode = rs.getString("pCode");
                String pPic = rs.getString("pPic");
                Timestamp pDate = rs.getTimestamp("pDate");

                int userId = rs.getInt("userId");
                Post p = new Post(pid, pTitle, pContent, pCode, pPic, pDate, catId, userId);
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Post getPostByPostId(int postId) {
        Post p = null;
        String q = "select * from posts where pid=?";
        try {
            PreparedStatement pst = this.con.prepareStatement(q);
            pst.setInt(1, postId);
            ResultSet set = pst.executeQuery();
            if (set.next()) {

                int pid = set.getInt("pid");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp pDate = set.getTimestamp("pDate");
                int catId = set.getInt("catId");
                int userId = set.getInt("userId");
                p = new Post(pid, pTitle, pContent, pCode, pPic, pDate, catId, userId);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
