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
     * Test of upload method, of class Bean.
     */
    @Test
    public void testUpload() throws Exception {
        System.out.println("upload");
        Bean instance = new Bean();
        instance.upload();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProjectNodeTypes method, of class Bean.
     */
    @Test
    public void testGetProjectNodeTypes() {
        System.out.println("getProjectNodeTypes");
        Bean instance = new Bean();
        List expResult = null;
        List result = instance.getProjectNodeTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of setProject method, of class Bean.
     */
    @Test
    public void testSetProject() {
        System.out.println("setProject");
        Projects project = null;
        Bean instance = new Bean();
        instance.setProject(project);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProjectName method, of class Bean.
     */
    @Test
    public void testGetProjectName() {
        System.out.println("getProjectName");
        Bean instance = new Bean();
        String expResult = "";
        String result = instance.getProjectName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProjectName method, of class Bean.
     */
    @Test
    public void testSetProjectName() {
        System.out.println("setProjectName");
        String projectName = "";
        Bean instance = new Bean();
        instance.setProjectName(projectName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of dataType method, of class Bean.
     */
    @Test
    public void testDataType() {
        System.out.println("dataType");
        Bean instance = new Bean();
        SelectItem[] expResult = null;
        SelectItem[] result = instance.dataType();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initalise method, of class Bean.
     */
    @Test
    public void testInitalise() {
        System.out.println("initalise");
        Bean instance = new Bean();
        String expResult = "";
        String result = instance.initalise();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GPSLat method, of class Bean.
     */
    @Test
    public void testGPSLat() {
        System.out.println("GPSLat");
        Bean instance = new Bean();
        String expResult = "";
        String result = instance.GPSLat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GPSLong method, of class Bean.
     */
    @Test
    public void testGPSLong() {
        System.out.println("GPSLong");
        Bean instance = new Bean();
        String expResult = "";
        String result = instance.GPSLong();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of NodeName method, of class Bean.
     */
    @Test
    public void testNodeName() {
        System.out.println("NodeName");
        Bean instance = new Bean();
        String expResult = "";
        String result = instance.NodeName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVariables method, of class Bean.
     */
    @Test
    public void testGetVariables() {
        System.out.println("getVariables");
        Bean instance = new Bean();
        String expResult = "";
        String result = instance.getVariables();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNode method, of class Bean.
     */
    @Test
    public void testGetNode() {
        System.out.println("getNode");
        Bean instance = new Bean();
        int expResult = 0;
        int result = instance.getNode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNode method, of class Bean.
     */
    @Test
    public void testSetNode() {
        System.out.println("setNode");
        int node = 0;
        Bean instance = new Bean();
        instance.setNode(node);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVariable method, of class Bean.
     */
    @Test
    public void testGetVariable() {
        System.out.println("getVariable");
        Bean instance = new Bean();
        int expResult = 0;
        int result = instance.getVariable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVariable method, of class Bean.
     */
    @Test
    public void testSetVariable() {
        System.out.println("setVariable");
        int variable = 0;
        Bean instance = new Bean();
        instance.setVariable(variable);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGraphData method, of class Bean.
     */
    @Test
    public void testGetGraphData() throws Exception {
        System.out.println("getGraphData");
        Bean instance = new Bean();
        List expResult = null;
        List result = instance.getGraphData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CentrePoint method, of class Bean.
     */
    @Test
    public void testCentrePoint() {
        System.out.println("CentrePoint");
        Bean instance = new Bean();
        String expResult = "";
        String result = instance.CentrePoint();
        assertEquals(expResult, result);
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