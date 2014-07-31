/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInput;

import Entities.DataDefinitions;
import Entities.DataValues;
import Entities.Nodes;
import Entities.Projects;
import Session.DataDefinitionsFacade;
import Session.DataValuesFacade;
import Session.NodesFacade;
import Session.ProjectsFacade;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import TestInput.MarkerCode;
import TestInput.MarkerCode.DataPairs;
import TestInput.MarkerCode.Marker;
import TestInput.MarkerCode.Variable;
import java.util.Collection;
/**
 *
 * @author t_sedgman
 */
@SessionScoped
public class Bean {
    @EJB
    private DataValuesFacade dataValuesFacade;
    @EJB
    private NodesFacade nodesFacade;
    @EJB
    private ProjectsFacade projectsFacade;
    @EJB
    private DataDefinitionsFacade dataDefinitionsFacade;
    @EJB
    private PersistedVariables.PProjects projectList;
    @EJB
    private PersistedVariables.PProject currProject;
    @EJB
    private PersistedVariables.PNodes currentNodes;
    @EJB
    private PersistedVariables.PCoordinates coordinates;
    
  private String fileContent;
  private String fileContent2;
  private ArrayList<String> fileContentArray = new ArrayList<>();;
  private int counter = 0;


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public ArrayList<String> getFileContentArray() {
        return fileContentArray;
    }

    public void setFileContentArray(ArrayList<String> fileContentArray) {
        this.fileContentArray = fileContentArray;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
    
    public String getFileContent2() {
        return fileContent2;
    }

    public void setFileContent2(String fileContent2) {
        this.fileContent2 = fileContent2;
    }
  
  public void upload() {

    File file = new File("/Users/t_sedgman/Desktop/FinalProject/test_output_data.rtf");
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;
    ArrayList<String> tempArray = new ArrayList<>();
    try
    {
      fis = new FileInputStream(file);
      // Here BufferedInputStream is added for fast reading.
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);

      // dis.available() returns 0 if the file does not have more lines.
      while (dis.available() != 0) 
      {

      // this statement reads the line from the file and print it to
        // the console.
        tempArray.add(dis.readLine());
      }
      // dispose all the resources after using them.
      
      fis.close();
      bis.close();
      dis.close();
      setFileContentArray(tempArray);
      String tempString = tempArray.get(tempArray.size()-3);
      List<String> Record = Arrays.asList(tempString.split(","));  
      dataValuesFacade.RecordData(Record);
      for (int i = 8; i < tempArray.size();i++) //FIXME hack because of input headders;
      {
        fileContent = tempArray.get(i);
      }

    } catch (FileNotFoundException e) 
    {
        e.printStackTrace();
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    }

  }

  public void next()
  {
     List returnedData = dataValuesFacade.findVariables(null, 0, 8);
     fileContent2 = returnedData.get(counter).toString();
     counter++;
  }
    public void loadProject(String username)
  {
      List projects = projectsFacade.findProjectsByUser(username);
      projectList.setProjects(projects);
  }
    
  public void loadNodes(int index)
  {
      List projects = projectList.getProjects();
      int i = index;
      Projects project = (Projects) projects.get(i);
      currProject.setCurrentProject(project);
      List<Nodes> nodes = nodesFacade.nodesList(project);
      currentNodes.setCurrentNodes(nodes);    
  }
  public String GPSLat()
  {
    ArrayList gPSLat = new ArrayList();
    List<Nodes> nodes = currentNodes.getCurrentNodes();
    String returnArray = "";

    for (int i = 0; i < nodes.size();i++)
    {
        BigDecimal temp = nodes.get(i).getgPSLat();
        gPSLat.add(temp);
        returnArray = returnArray.concat(temp.toPlainString());
        if (i != (nodes.size()-1))
        {    
        returnArray = returnArray.concat(",");
        }
    }
     coordinates.setGPSLat(gPSLat);
     return returnArray;
  }
   public String GPSLong()
  {
    ArrayList gPSLong = new ArrayList();
    List<Nodes> nodes = currentNodes.getCurrentNodes();
    String returnArray = "";
    for (int i = 0; i < nodes.size();i++)
    {
        BigDecimal temp = nodes.get(i).getgPSLong();
        gPSLong.add(temp);
        returnArray = returnArray.concat(temp.toPlainString());
        if (i != (nodes.size()-1))
        { 
        returnArray = returnArray.concat(","); 
        }
    }
     coordinates.setGPSLong(gPSLong);
     return returnArray;
  }
   
   public String NodeName()
  {
    List<Nodes> nodes = currentNodes.getCurrentNodes();
    String returnArray = "";
    for (int i = 0; i < nodes.size();i++)
    {
        String temp = nodes.get(i).getnTIdentifier();
        returnArray = returnArray.concat(temp);
        if (i != (nodes.size()-1))
        { 
        returnArray = returnArray.concat(","); 
        }
    }
     return returnArray;
  }
   
   public String getVariables()
   {
       
       String returnString = "";
       
       for (int i = 0; i < currentNodes.getCurrentNodes().size();i++)
    {
        String tempString = "";
        List<DataDefinitions> definitions = (List<DataDefinitions>) currentNodes.getCurrentNodes().get(i).getNodeType().getDataDefinitions();
        for (int j = 0; j<definitions.size();j++)
        {
            String temp = definitions.get(j).getdDName();
            tempString = tempString.concat(temp);
            if (j != (definitions.size()-1))
            { 
                tempString = tempString.concat(","); 
            }
        }
            returnString = returnString.concat(tempString);
            if (i != (definitions.size()-1))
            { 
                returnString = returnString.concat("//"); 
            }
    }
       
       return returnString;
   }
  public String getData(int positionId, String nodeIdentifier)
  {
      String data = "";
      int i = 0;
      Nodes selectedNode;
      do
      {
          selectedNode = currentNodes.getCurrentNodes().get(i);
      }
      while (selectedNode.getnTIdentifier() == null ? (nodeIdentifier) != null : !selectedNode.getnTIdentifier().equals(nodeIdentifier));   
      List<DataValues> dataList = dataValuesFacade.findVariable(selectedNode, positionId);
      for (int j = 0; j < dataList.size();j++)
        {
            String temp = dataList.get(j).getdVariable();
            data = data.concat(temp);
            if (j != (dataList.size()-1))
            { 
                data = data.concat(","); 
            }
        }
      return data;
  }
    public String CentrePoint()
    {
        List gpsLat = coordinates.getGPSLat();
        List gpsLong = coordinates.getGPSLong();
        Double centreLat = average(gpsLat);
        Double centreLong = average(gpsLong);
        String centre = centreLat.toString();
        centre = centre.concat(" , ");
        centre = centre.concat(centreLong.toString());
        return centre;
    }
   
    public static Double average(List<BigDecimal> list) 
    {
        // 'average' is undefined if there are no elements in the list.
        if (list == null || list.isEmpty())
            return 0.0;
        // Calculate the summation of the elements in the list
        Double sum = 0.0;
        int n = list.size();
        // Iterating manually is faster than using an enhanced for loop.
        for (int i = 0; i < n; i++)
        {
            Double temp = list.get(i).doubleValue();
            sum += temp;
        }
        // We don't want to perform an integer division, so the cast is mandatory.
        return ((Double) sum) / n;
    }
    
    public String makeJson()
    {
        Gson gson = new Gson();
        ArrayList markers = new ArrayList();
        
        for (int i =0;i<currentNodes.getCurrentNodes().size();i++)
        {
            markers.add(jsonNode(i));
        }
        String json = gson.toJson(markers);
        
        return json;
    }
    public Marker jsonNode(int i)
    {
        Nodes currentNode = currentNodes.getCurrentNodes().get(i);
        Marker marker = new Marker();
        marker.setName(currentNode.getnTIdentifier().toString());
        marker.setgPSLat(currentNode.getgPSLat().floatValue());
        marker.setgPSLong(currentNode.getgPSLong().floatValue());
        marker.setStatic(currentNode.isIsStatic());
        ArrayList<Variable> variables = new ArrayList();
        for (int j = 0 ; j < currentNode.getNodeType().getDataDefinitions().size() ; j++)   
        {
            Variable variable = variables(currentNode, j);
            variables.add(variable);
        }
        marker.setVariable(variables);
        return marker;
    }
    public Variable variables(Nodes node, int i)
    {
        Variable variable = new Variable();
        Collection currentDataDefinitions =  node.getNodeType().getDataDefinitions();
        ArrayList cDD = new ArrayList();
        cDD.addAll(currentDataDefinitions);
        DataDefinitions data = (DataDefinitions) cDD.get(i);
        variable.setVariableName(data.getdDName());
        variable.setVariableUnit(data.getdDUnit());
        
        DataPairs dataPairs = new DataPairs();
        ArrayList timestamp = new ArrayList();
        timestamp.addAll(dataValuesFacade.findVariable(node, 0));
        dataPairs.setTimestamp(timestamp);
        dataPairs.setVariable((ArrayList<Float>) dataValuesFacade.findVariable(node,i).get(counter));
        dataPairs.setVariableOffset(null);// need to fill with data from website
        
        variable.setData(dataPairs);
        
        return variable;
    }
}

