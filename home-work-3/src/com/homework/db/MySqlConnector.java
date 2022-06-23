package com.homework.db;

import com.homework.constant.MySQLConstant;
import com.homework.entity.Traveler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySqlConnector {
    Connection conn = null;

    public MySqlConnector(String drive, String dbUrl, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(drive);
        System.out.println("连接数据库...");
        conn = DriverManager.getConnection(dbUrl, username, password);
        conn.setAutoCommit(false);

    }

    public List<String> countTitanicBySurvivedAndSex(Boolean survived, String sex) {
        String sql = "select * from traveler where survived=? and sex = ?;";
        List<String> result = new ArrayList<>();

        try {
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, survived ? 1 : 0);
            preparedStmt.setString(2, sex);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1) + " , " + rs.getString(2) + " , " + rs.getString(3) + " , " + rs.getString(4) + " , " + rs.getString(5) + " , " + rs.getString(6) + " , " + rs.getString(7) + " , " + rs.getString(8) + " , " + rs.getString(9) + " , " + rs.getString(10)+ " , " + rs.getString(11)+ " , " + rs.getString(12));
            }
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int addTravelerList(List<Traveler> travelers) throws SQLException {
        String sql = "insert into traveler (passengerId,survived,pclass,name,sex,age,sibSp,parch,ticket,fare,cabin,embarked) values (?, ?, ?, ?, ? , ?, ?, ?, ?, ? , ?, ?) on DUPLICATE KEY UPDATE passengerId=VALUES(passengerId) ;";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        System.out.println("traveler:" + travelers.size());
        travelers.stream().forEach(traveler -> {
            try {
                preparedStmt.setLong(1, traveler.getId());
                preparedStmt.setInt(2, traveler.getAlive() ? 1 : 0);
                preparedStmt.setString(3, traveler.getTicketType());
                preparedStmt.setString(4, traveler.getTravelerName());
                preparedStmt.setString(5, traveler.getSex());
                preparedStmt.setObject(6, traveler.getAge());
                preparedStmt.setObject(7, traveler.getSiblingNum());
                preparedStmt.setObject(8, traveler.getParentNum());
                preparedStmt.setString(9, traveler.getTicketSeries());
                preparedStmt.setObject(10, traveler.getPrice());
                preparedStmt.setString(11, traveler.getBoardSeries());
                preparedStmt.setString(12, traveler.getPort());
                preparedStmt.addBatch();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] updateCounts = preparedStmt.executeBatch();
        conn.commit();
        return updateCounts.length;
    }

    public void closeDB() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
