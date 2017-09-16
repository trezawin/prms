/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.scheduleprogram.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.Stateless;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
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

    public ArrayList<ProgramSlot> retrieveAll() {
        ArrayList<ProgramSlot> list = new ArrayList<ProgramSlot>();
        try {
                list = (ArrayList<ProgramSlot>) rpdao.retrieveAll();
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return list;
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

    public void create(ProgramSlot rp) {
        try {
            rpdao.create(rp);
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void update(ProgramSlot rp) {

        try {
                rpdao.create(rp);
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

    }

    public void delete(Long id) {

        try {
            ProgramSlot rp = new ProgramSlot();
            rp.setId(id);
            rpdao.delete(rp);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
