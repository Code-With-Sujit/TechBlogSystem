package com.tech.blog.dao;

import java.sql.*;

public class LikeDao {

    Connection con;

    public LikeDao(Connection con) {
        this.con = con;
    }

    public boolean insertLike(int pid, int uid) {
        boolean f = false;
        try {

            String q = "insert into liked(pid,uid)values(?,?);";
            PreparedStatement pst = this.con.prepareStatement(q);
            pst.setInt(1, pid);
            pst.setInt(2, uid);
            pst.executeUpdate();
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public int countLikeOnPost(int pid) {
        int count = 0;
        String q = "select count(*) from liked where pid=?";
        try {

            PreparedStatement pst = this.con.prepareStatement(q);
            pst.setInt(1, pid);
            ResultSet set = pst.executeQuery();
            if (set.next()) {
                count = set.getInt("count(*)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public boolean isLikedByUser(int pid, int uid) {
        boolean f = false;
        try {
            PreparedStatement pst = this.con.prepareStatement("select * from liked where pid=? and uid=?");
            pst.setInt(1, pid);
            pst.setInt(2, uid);
            ResultSet set = pst.executeQuery();
            if (set.next()) {
               f=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
    
    public boolean deleteLike(int pid,int uid)
    {
        boolean f=false;
        
        try {
            
            PreparedStatement pst=this.con.prepareStatement("delete from liked where pid=? and uid=?");
            pst.setInt(1, pid);
            pst.setInt(2, uid);
            pst.executeUpdate();
            f=true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
