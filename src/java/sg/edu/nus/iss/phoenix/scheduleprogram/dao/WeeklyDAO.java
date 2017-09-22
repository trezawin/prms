package sg.edu.nus.iss.phoenix.scheduleprogram.dao;

import java.sql.SQLException;
import java.util.Date;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.WeeklySchedule;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kmb
 */
public interface WeeklyDAO {
    boolean isExisting(Date startDate)throws SQLException;
    void create(WeeklySchedule weeklySchedule)throws SQLException;
}
