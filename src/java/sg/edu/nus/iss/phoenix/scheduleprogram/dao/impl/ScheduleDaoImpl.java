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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.ScheduleDao;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;

/**
 * Schedule Data Access Object (DAO). This class contains all database handling
 * that is needed to permanently store and retrieve ProgramSlot object instances.
 * 
 * @author treza
 */
public class ScheduleDaoImpl implements ScheduleDao {
    private static final Logger logger = Logger.getLogger(ScheduleDaoImpl.class.getName());

    Connection connection;

    public ScheduleDaoImpl() {
        super();
        connection = openConnection();
    }

    @Override
    public ProgramSlot retrieveBy(int id) throws NotFoundException, SQLException {
        
        String sql = "SELECT * FROM `program-slot` WHERE (id = ? ) ";
        PreparedStatement stmt = null;
        ProgramSlot prgSlot = new ProgramSlot();
        try {
                stmt = this.connection.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    prgSlot = this.resultSetToObject(rs);
                }
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        return prgSlot;
    }

    private ProgramSlot resultSetToObject(ResultSet rs)throws SQLException{
        ProgramSlot programSlot = new ProgramSlot();
        
        programSlot.setId(rs.getInt("id"));
        programSlot.setProgramName(rs.getString("program-name"));
        programSlot.setDateOfProgram(rs.getTimestamp("dateOfProgram"));
        programSlot.setDuration(rs.getTimestamp("duration"));
        programSlot.setPresenterId(rs.getString("presenterId"));
        programSlot.setProducerId(rs.getString("producerId"));
        return programSlot;
    }
    
    @Override
    public List<ProgramSlot> retrieveAll() throws SQLException {
        String sql = "SELECT * FROM `program-slot`";
        
        PreparedStatement stmt = null;
        
        List<ProgramSlot> prgSlotList = new ArrayList<>();
        try {
                stmt = this.connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    ProgramSlot programSlot = this.resultSetToObject(rs);
                    if(programSlot.getPresenterId() != null && !programSlot.getPresenterId().equals("")){
                        String userSql = "select * from user where id = ?";
                        PreparedStatement userStatement = this.connection.prepareCall(userSql);
                        userStatement.setString(1, programSlot.getPresenterId());
                        ResultSet userRS = userStatement.executeQuery();
                        userRS.next();
                        programSlot.setPresenterName(userRS.getString(3));
                    }
                    if(programSlot.getProducerId() != null && !programSlot.getProducerId().equals("")){
                        String userSql = "select * from user where id = ?";
                        PreparedStatement userStatement = this.connection.prepareCall(userSql);
                        userStatement.setString(1, programSlot.getProducerId());
                        ResultSet producerRS = userStatement.executeQuery();
                        producerRS.next();
                        programSlot.setProducerName(producerRS.getString(3));
                    }
                    prgSlotList.add(programSlot);
                }
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        return prgSlotList;
    }

    @Override
    public void create(ProgramSlot valueObject) throws SQLException {
        String sql = "";
	PreparedStatement stmt = null;
        try {
                sql = "INSERT INTO `program-slot` (duration, dateOfProgram, `program-name`, producerId, presenterId) VALUES (?, ?, ?, ?, ?) ";
                stmt = this.connection.prepareStatement(sql);

                stmt.setTimestamp(1, new Timestamp(valueObject.getDuration().getTime()));
                stmt.setTimestamp(2, new Timestamp(valueObject.getDateOfProgram().getTime()));
                stmt.setString(3, valueObject.getProgramName());
                stmt.setString(4, valueObject.getProducerId());
                stmt.setString(5, valueObject.getPresenterId());
                
                int rowcount = databaseUpdate(stmt);
                
                if (rowcount != 1) {
                    logger.info("PrimaryKey Error when updating DB!");
                        throw new SQLException("PrimaryKey Error when updating DB!");
                }

        } finally {
                if (stmt != null)
                        stmt.close();
        }
    }

    @Override
    public void update(ProgramSlot valueObject) throws NotFoundException, SQLException {

        String sql = "UPDATE `program-slot` SET `program-name` = ?, duration = ?, dateOfProgram = ?, producerId = ?, presenterId = ? "
                + "WHERE (id = ? ) ";
        PreparedStatement stmt = null;

        try {
                stmt = this.connection.prepareStatement(sql);
                stmt.setString(1, valueObject.getProgramName());
                stmt.setTimestamp(2, new Timestamp(valueObject.getDuration().getTime()));
                stmt.setTimestamp(3, new Timestamp(valueObject.getDateOfProgram().getTime()));
                stmt.setString(4, valueObject.getProducerId());
                stmt.setString(5, valueObject.getPresenterId());
                stmt.setInt(6, valueObject.getId());

                int rowcount = databaseUpdate(stmt);
                if (rowcount == 0) {
                        logger.info("Object could not be saved! (PrimaryKey not found)");
                        throw new NotFoundException(
                                        "Object could not be saved! (PrimaryKey not found)");
                }
                if (rowcount > 1) {
                        logger.info("PrimaryKey Error when updating DB! (Many objects were affected!)");
                        throw new SQLException(
                                        "PrimaryKey Error when updating DB! (Many objects were affected!)");
                }
        } finally {
                if (stmt != null)
                        stmt.close();
        }
    }

    @Override
    public void delete(ProgramSlot valueObject) throws NotFoundException, SQLException {
        
        String sql = "DELETE FROM `program-slot` WHERE (id = ? ) ";
        PreparedStatement stmt = null;

        try {
                stmt = this.connection.prepareStatement(sql);
                    stmt.setInt(1, valueObject.getId());

                int rowcount = databaseUpdate(stmt);
                if (rowcount == 0) {
                        // System.out.println("Object could not be deleted (PrimaryKey not found)");
                        throw new NotFoundException(
                                        "Object could not be deleted! (PrimaryKey not found)");
                }
                if (rowcount > 1) {
                        // System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                        throw new SQLException(
                                        "PrimaryKey Error when updating DB! (Many objects were deleted!)");
                }
        } finally {
                if (stmt != null)
                        stmt.close();
        }
    }

    @Override
    public void delete(Connection conn) throws SQLException {
        
        String sql = "DELETE FROM `program-slot`";
        PreparedStatement stmt = null;

        try {
                stmt = this.connection.prepareStatement(sql);
                int rowcount = databaseUpdate(stmt);
                logger.info("Deleted rows :" + rowcount);
        } finally {
                if (stmt != null)
                    stmt.close();
        }
    }
    
    /**
     * databaseUpdate-method. This method is a helper method for internal use.
     * It will execute all database handling that will change the information in
     * tables. SELECT queries will not be executed here however. The return
     * value indicates how many rows were affected. This method will also make
     * sure that if cache is used, it will reset when data changes.
     * 
     * @param stmt
     * This parameter contains the SQL statement to be executed.
     * @return 
     * @throws java.sql.SQLException
     */
    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {
        int result = stmt.executeUpdate();
        return result;
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
    public boolean isProgramSlotAssigned(Date startDateTime, Date duration, int id) throws SQLException {
        Calendar durationCalendar = Calendar.getInstance();
        durationCalendar.setTime(duration);
        durationCalendar.set(Calendar.DAY_OF_MONTH, 0);
        durationCalendar.set(Calendar.MONTH, 0);
        durationCalendar.set(Calendar.YEAR, 0);
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        StringBuilder sqlQuery = new StringBuilder("select * from `program-slot`");
        sqlQuery.append(" where (? between dateOfProgram and AddTime(dateOfProgram, duration))");
        sqlQuery.append(" or (AddTime(?, ?) between dateOfProgram and AddTime(dateOfProgram, duration))");
        
        
        if(id != 0)
            sqlQuery.append(" and id != ").append(id);
        PreparedStatement prepareStatement;
        try {
            String startDateStr = startDateFormat.format(startDateTime);
            prepareStatement = connection.prepareStatement(sqlQuery.toString());
            prepareStatement.setString(1, startDateStr);
            prepareStatement.setString(2, startDateStr);
            prepareStatement.setString(3, simpleDateFormat.format(duration));
            if(id != 0)
                prepareStatement.setInt(4, id);
            ResultSet rs = prepareStatement.executeQuery();
            boolean result = rs.next();
            
            return result;
        } catch (SQLException e) {
            throw e;
        }
    }
}