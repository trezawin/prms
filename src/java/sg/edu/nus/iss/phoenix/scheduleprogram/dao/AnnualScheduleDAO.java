/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.scheduleprogram.dao;

import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.AnnualSchedule;

/**
 *
 * @author kmb
 */
public interface AnnualScheduleDAO {
    boolean isExisting(int year)throws SQLException;
    void create(AnnualSchedule annualSchedule)throws SQLException;
}
