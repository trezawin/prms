package sg.edu.nus.iss.phoenix.core.dao;

import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.RoleDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAOImpl;
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.AnnualScheduleDAO;
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.ScheduleDao;
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.WeeklyDAO;
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.impl.AnnualScheduledDAOImpl;
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.impl.ScheduleDaoImpl;
import sg.edu.nus.iss.phoenix.scheduleprogram.dao.impl.WeeklyScheduleDAOImpl;

public class DAOFactoryImpl implements DAOFactory {
    private UserDao userDAO = new UserDaoImpl();
    private RoleDao roleDAO = new RoleDaoImpl();
    private ProgramDAO rpdao = new ProgramDAOImpl();
    private ScheduleDao scheduledao = new ScheduleDaoImpl();
    private WeeklyDAO weeklyDao = new WeeklyScheduleDAOImpl();
    private AnnualScheduleDAO annualScheduleDao = new AnnualScheduledDAOImpl();

    @Override
    public UserDao getUserDAO() {
            // TODO Auto-generated method stub
            return userDAO;
    }

    @Override
    public RoleDao getRoleDAO() {
            // TODO Auto-generated method stub
            return roleDAO;
    }

    @Override
    public ProgramDAO getProgramDAO() {
            // TODO Auto-generated method stub
            return rpdao;
    }

    @Override
    public ScheduleDao getSchedeuleDAO() {
        return scheduledao;
    }

    @Override
    public WeeklyDAO getWeeklyDAO() {
        return weeklyDao;
    }

    @Override
    public AnnualScheduleDAO getAnnualScheduleDAO() {
        return annualScheduleDao;
    }

}
