/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.scheduleprogram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;

/**
 *
 * @author treza
 */
public interface ScheduleDao {

	/**
	 * retrieveBy-method. This will load valueObject contents from database using
	 * Primary-Key as identifier. Upper layer should use this so that
	 * valueObject instance is created and only primary-key should be specified.
	 * Then call this method to complete other persistent information. This
	 * method will overwrite all other fields except primary-key and possible
	 * runtime variables. If load can not find matching row, NotFoundException
	 * will be thrown.
	 * 
	 * @param id
	 *            This parameter contains the class instance to be loaded.
	 *            Primary-key field must be set for this to work properly.
         * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
         * @throws java.sql.SQLException
	 */
	public abstract ProgramSlot retrieveBy(int id) throws NotFoundException, SQLException;

	/**
	 * retrieveAll-method. This will read all contents from database table and build
	 * a List containing valueObjects. Please note, that this method will
	 * consume huge amounts of resources if table has lot's of rows. This should
	 * only be used when target tables have only small amounts of data.
	 * 
         * @return 
         * @throws java.sql.SQLException
	 */
	public abstract List<ProgramSlot> retrieveAll() throws SQLException;

	/**
	 * create-method. This will create new row in database according to supplied
	 * valueObject contents. Make sure that values for all NOT NULL columns are
	 * correctly specified. Also, if this table does not use automatic
	 * surrogate-keys the primary-key must be specified. After INSERT command
	 * this method will read the generated primary-key back to valueObject if
	 * automatic surrogate-keys were used.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance to be created. If
	 *            automatic surrogate-keys are not used the Primary-key field
	 *            must be set for this to work properly.
         * @throws java.sql.SQLException
	 */
	public abstract void create(ProgramSlot valueObject) throws SQLException;

	/**
	 * update-method. This method will save the current state of valueObject to
	 * database. Save can not be used to create new instances in database, so
	 * upper layer must make sure that the primary-key is correctly specified.
	 * Primary-key will indicate which instance is going to be updated in
	 * database. If save can not find matching row, NotFoundException will be
	 * thrown.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance to be saved.
	 *            Primary-key field must be set for this to work properly.
         * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
         * @throws java.sql.SQLException
	 */
	public abstract void update(ProgramSlot valueObject) throws NotFoundException, SQLException;

	/**
	 * delete-method. This method will remove the information from database as
	 * identified by by primary-key in supplied valueObject. Once valueObject
	 * has been deleted it can not be restored by calling save. Restoring can
	 * only be done using create method but if database is using automatic
	 * surrogate-keys, the resulting object will have different primary-key than
	 * what it was in the deleted object. If delete can not find matching row,
	 * NotFoundException will be thrown.
	 * 
	 * @param valueObject
	 *            This parameter contains the class instance to be deleted.
	 *            Primary-key field must be set for this to work properly.
         * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
         * @throws java.sql.SQLException
	 */
	public abstract void delete(ProgramSlot valueObject) throws NotFoundException, SQLException;

	/**
	 * deleteAll-method. This method will remove all information from the table
	 * that matches this Dao and ValueObject couple. This should be the most
	 * efficient way to clear table. Once deleteAll has been called, no
	 * valueObject that has been created before can be restored by calling save.
	 * Restoring can only be done using create method but if database is using
	 * automatic surrogate-keys, the resulting object will have different
	 * primary-key than what it was in the deleted object. (Note, the
	 * implementation of this method should be different with different DB
	 * backends.)
	 * 
	 * @param conn
	 *            This method requires working database connection.
         * @throws java.sql.SQLException
	 */
	public abstract void delete(Connection conn) throws SQLException;

        public abstract boolean isProgramSlotAssigned(Date startDateTime, Date endDateTime, int id)throws SQLException;
}
