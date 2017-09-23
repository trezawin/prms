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
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.AnnualSchedule;

/**
 *
 * @author kmb
 */
public class AnnualScheduledDAOImpl implements AnnualScheduleDAO{

    Connection connection;

    public AnnualScheduledDAOImpl() {
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
    
    @Override
    public boolean isExisting(int year) throws SQLException {
        String sqlQuery = "select * from `annual-schedule` where year=" + year;
        PreparedStatement preparedStatement = connection.prepareCall(sqlQuery);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }

    @Override
    public void create(AnnualSchedule annualSchedule) throws SQLException {
        String sqlQuery = "insert into `annual-schedule` (year, assignedBy) values ( ?, ?)";
        PreparedStatement preparedStatement = connection.prepareCall(sqlQuery);
        preparedStatement.setInt(1, annualSchedule.getId());
        preparedStatement.setString(2, annualSchedule.getAssignedBy());
        preparedStatement.executeUpdate();
    }
    
}
