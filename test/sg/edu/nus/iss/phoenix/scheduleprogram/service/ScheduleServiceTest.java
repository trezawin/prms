/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.scheduleprogram.service;

import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import sg.edu.nus.iss.phoenix.core.exceptions.DuplicateProgramSlot;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;

/**
 *
 * @author treza
 */
public class ScheduleServiceTest {
    ScheduleService instance;
    
    public ScheduleServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new ScheduleService();
    }
    
    @After
    public void tearDown() {
    }
    
    private ProgramSlot initializeObject() throws Exception{
        System.out.println("test create");
        ProgramSlot rp = new ProgramSlot();
        rp.setProgramName("noose");
        rp.setDuration(new Date());
        rp.setDateOfProgram(new Date());
        rp.setPresenterId("dilbert");
        rp.setProducerId("dilbert");
        rp.setAssignedBy("pointyhead");
        return rp;
    }

    /**
     * Test of create method, of class ScheduleService.
     */
//    @org.junit.Test
    public void testCreate() throws Exception {
        instance.create(initializeObject());
        assertTrue(true);
    }
    
    /**
     * Test of retrieveAll method, of class ScheduleService.
     */
//    @org.junit.Test
    public void testRetrieveAll() throws Exception {
        ProgramSlot ps = null;
        ArrayList<ProgramSlot> result = instance.retrieveAll();
        
        assertEquals(3, result.size());
    }

    /**
     * Test of retrieveBy method, of class ScheduleService.
     */
//    @org.junit.Test
    public void testRetrieveBy() throws Exception {
        int id = 16;
        ProgramSlot result = instance.retrieveBy(id);
        
        assertNotNull(result);
        assertEquals(16, result.getId());
    }

    /**
     * Test of update method, of class ScheduleService.
     */
//    @org.junit.Test
    public void testUpdate() throws Exception {
        instance.create(initializeObject());
        
        ArrayList<ProgramSlot> prs = instance.retrieveAll();
        prs.get(prs.size() - 1).setDuration(new Date());
        
        instance.update(prs.get(prs.size() - 1));
        assertTrue(true);
    }
    
    @Test
    public void testCopy() throws Exception{
        ArrayList<ProgramSlot> prs = instance.retrieveAll();
        
        ProgramSlot copyPS = prs.get(0);
        copyPS.setId(0);
        try {
            instance.create(copyPS);
            fail();
        } catch (DuplicateProgramSlot e) {
            assertTrue(true);
        }
        
    }

    /**
     * Test of delete method, of class ScheduleService.
     */
//    @org.junit.Test
    public void testDelete() throws Exception {
        instance.delete(24);
        
        try {
            ProgramSlot result = instance.retrieveBy(21);
            Integer i = result.getId();
            fail();
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }
    
}
