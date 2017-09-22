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
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;

/**
 *
 * @author treza
 */
@Stateless
public class ScheduleService {
    
    DAOFactoryImpl factory;
    ScheduleDao rpdao;

    public ScheduleService() {
        super();
        factory = new DAOFactoryImpl();
        rpdao = factory.getSchedeuleDAO();
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
