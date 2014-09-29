/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInput;

import Entities.Projects;
import Entities.Users;
import Session.AcceptableDataTypesFacade;
import Session.DataDefinitionsFacade;
import Session.DataValuesFacade;
import Session.NodeTypesFacade;
import Session.NodesFacade;
import Session.ProjectsFacade;
import Session.UsersFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author t_sedgman
 */
public class BeanTest {
   
    public BeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() 
    {
       
        
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of getUsername method, of class Bean.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Bean instance = new Bean();
        String expResult = "ths28";
        instance.setUsername(expResult);
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setUsername method, of class Bean.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "ths28";
        Bean instance = new Bean();
        instance.setUsername(username);
        assertEquals(instance.getUsername(),username);
    }

    /**
     * Test of getProject method, of class Bean.
     */
    @Test
    public void testGetProject() {
        System.out.println("getProject");
        Bean instance = new Bean();
        String expResult = "Monitoring 1";
        instance.setUsername("ths28");
        SelectItem[] temp = instance.projectList();
        Projects temp1 = (Projects) temp[0].getValue();
        String result = temp1.getProjectName();
       
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    
    /**
     * Test of projectList method, of class Bean.
     */
    @Test
    public void testProjectList() {
        System.out.println("projectList");
        Bean instance = new Bean();
        SelectItem[] expResult = null;
        SelectItem[] result = instance.projectList();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of average method, of class Bean.
     */
    @Test
    public void testAverage() {
        System.out.println("average");
        
        ArrayList<BigDecimal> t1 = new ArrayList();
        for (int i = 0;i < 10;i++)
        {
            BigDecimal num = BigDecimal.valueOf(i);
            t1.add(num);
        }
        List<BigDecimal> list = t1;
        Double expResult = 4.5;
        Double result = Bean.average(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}