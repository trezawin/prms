/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.scheduleprogram.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.WeeklyDAO;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.WeeklySchedule;

/**
 *
 * @author kmb
 */
public class WeeklyScheduleDAOImpl implements WeeklyDAO{

    Connection connection;

    public WeeklyScheduleDAOImpl() {
        connection = openConnection();
    }
    
    private Connection openConnection() {
        Connection conn = null;
        try {
                Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
                conn = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/phoenix", "phoenix",
                                "password");
        } catch (SQLException e) {
        }
        return conn;
    }
    
    private long getIdFromStartDate(Date startDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }
    
    @Override
    public boolean isExisting(Date startDate)throws SQLException {
        String sqlQuery = "select * from `weekly-schedule` where id = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(sqlQuery);
        prepareStatement.setLong(1, getIdFromStartDate(startDate));
        ResultSet rs = prepareStatement.executeQuery();
        return rs.next();
    }

    @Override
    public void create(WeeklySchedule weeklySchedule)throws SQLException {
        String sqlQuery = "insert into `weekly-schedule`(id, startDate, assignedBy, endDate) values(?, ?, ?, ?)";
        PreparedStatement prepareStatement = connection.prepareStatement(sqlQuery);
        prepareStatement.setLong(1, getIdFromStartDate(weeklySchedule.getStartDate()));
        prepareStatement.setDate(2, new java.sql.Date(weeklySchedule.getStartDate().getTime()));
        prepareStatement.setString(3, weeklySchedule.getAssignedBy());
        prepareStatement.setDate(4, new java.sql.Date(weeklySchedule.getEndDate().getTime()));
        prepareStatement.executeUpdate();
    }
    
}
