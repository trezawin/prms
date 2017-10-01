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

    /**
     * Test of create method, of class ScheduleService.
     */
    @org.junit.Test
    public void testCreate() throws Exception {
        System.out.println("test create");
        ProgramSlot rp = new ProgramSlot();
        rp.setId(1001);
        rp.setProgramName("TEST PROGRAM NAME3");
        rp.setDuration(new Date());
        rp.setDateOfProgram(new Date());
        rp.setPresenterId("Presenter 1");
        rp.setPresneterName("Presenter 1");
        rp.setProducerId("Producer 1");
        rp.setProducerName("Producer 1");
              
        instance.create(rp);
        assertEquals("Producer 1", rp.getProducerName());
    }
    
    /**
     * Test of retrieveAll method, of class ScheduleService.
     */
    @org.junit.Test
    public void testRetrieveAll() throws Exception {
        System.out.println("test retrieveAll");
        
        ProgramSlot ps = null;
        ArrayList<ProgramSlot> result = instance.retrieveAll();
        for (ProgramSlot programSlot : result) {
            if(1000 == programSlot.getId()) {
                ps = programSlot;
            }
        }
        
        assertNotNull(ps);
        assertEquals("TEST PROGRAM NAME", ps.getProgramName());
    }

    /**
     * Test of retrieveBy method, of class ScheduleService.
     */
    @org.junit.Test
    public void testRetrieveBy() throws Exception {
        System.out.println("test retrieveBy");
        int id = 1000;
        ProgramSlot result = instance.retrieveBy(id);
        
        assertNotNull(result);
        assertEquals(1000, result.getId());
    }

    /**
     * Test of update method, of class ScheduleService.
     */
    @org.junit.Test
    public void testUpdate() throws Exception {
        System.out.println("test update");
        ProgramSlot rp = new ProgramSlot();
        rp.setId(1000);
        rp.setProgramName("TEST PROGRAM NAME 2");
        rp.setDateOfProgram(new Date());
        rp.setPresenterId("Presenter 2");
        rp.setPresneterName("Presenter 2");
        rp.setProducerId("Producer 2");
        rp.setProducerName("Producer 2");
        instance.update(rp);
        
        assertNotNull(rp);
        assertEquals("TEST PROGRAM NAME 2", rp.getProgramName());
       
    }

    /**
     * Test of delete method, of class ScheduleService.
     */
    @org.junit.Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = 1000;
        instance.delete(id);
        
        ProgramSlot result = instance.retrieveBy(id);
        assertNull(result);
    }
    
}
