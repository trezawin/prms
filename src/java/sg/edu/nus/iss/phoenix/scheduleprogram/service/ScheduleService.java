/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.scheduleprogram.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.Stateless;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.DuplicateProgramSlot;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.ScheduleDao;
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.WeeklyDAO;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.WeeklySchedule;

/**
 *
 * @author treza
 */
@Stateless
public class ScheduleService {
    
    DAOFactoryImpl factory;
    ScheduleDao rpdao;
    WeeklyDAO weeklyDao;

    public ScheduleService() {
        super();
        factory = new DAOFactoryImpl();
        rpdao = factory.getSchedeuleDAO();
        weeklyDao = factory.getWeeklyDAO();
    }

    public ArrayList<ProgramSlot> retrieveAll() throws Exception {
        return (ArrayList<ProgramSlot>) rpdao.retrieveAll();
    }

    public ProgramSlot retrieveBy(int id) {
        ProgramSlot ps = new ProgramSlot();

        try {
            ps = (ProgramSlot) rpdao.retrieveBy(id);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ps;
    }

    public void create(ProgramSlot rp)throws Exception {
        if(rpdao.isProgramSlotAssigned(rp.getDateOfProgram(), getProgramEndTime(rp), 0))
            throw new DuplicateProgramSlot("Program slot for " + rp.getProgramName() + " is already taken.");
        rpdao.create(rp);
        
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime(new Date(rp.getDateOfProgram().getTime()));
        int dayOfWeek = startDateCalendar.get(Calendar.DAY_OF_WEEK);
        startDateCalendar.add(Calendar.DAY_OF_MONTH, -dayOfWeek + 1);
        startDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startDateCalendar.set(Calendar.MINUTE, 0);
        startDateCalendar.set(Calendar.SECOND, 0);
        startDateCalendar.set(Calendar.MILLISECOND, 0);
        if(!weeklyDao.isExisting(rp.getDateOfProgram())){
            WeeklySchedule weeklySchedule = new WeeklySchedule();
            weeklySchedule.setStartDate(new Date(startDateCalendar.getTime().getTime()));
            
            startDateCalendar.add(Calendar.DAY_OF_MONTH, (7 - dayOfWeek) + 1);
            startDateCalendar.add(Calendar.SECOND, -1);
            weeklySchedule.setEndDate(startDateCalendar.getTime());
            weeklySchedule.setAssignedBy("");
            weeklyDao.create(weeklySchedule);
        }
    }

    public void update(ProgramSlot rp)throws Exception {
        if(rpdao.isProgramSlotAssigned(rp.getDateOfProgram(), getProgramEndTime(rp), rp.getId()))
            throw new DuplicateProgramSlot("Program slot for " + rp.getProgramName() + " is already taken.");
        rpdao.create(rp);
    }

    public void delete(int id)throws Exception {
        ProgramSlot rp = new ProgramSlot();
        rp.setId(id);
        rpdao.delete(rp);
    }
    
    private Date getProgramEndTime(ProgramSlot rp){
        Calendar calendarDuration = Calendar.getInstance();
        calendarDuration.setTime(rp.getDuration());

        Calendar calendarEndDateTime = Calendar.getInstance();
        calendarEndDateTime.setTime(new Date(rp.getDateOfProgram().getTime()));
        calendarEndDateTime.add(Calendar.HOUR_OF_DAY, calendarDuration.get(Calendar.HOUR_OF_DAY));
        calendarEndDateTime.add(Calendar.MINUTE, calendarDuration.get(Calendar.MINUTE));
        
        return calendarEndDateTime.getTime();
    }
}
