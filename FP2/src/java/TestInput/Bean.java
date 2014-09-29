/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInput;

import Classes.util.JsfUtil;
import Entities.AcceptableDataTypes;
import Entities.DataDefinitions;
import Entities.Nodes;
import Entities.Projects;
import Entities.Users;
import Session.AcceptableDataTypesFacade;
import Session.DataDefinitionsFacade;
import Session.DataValuesFacade;
import Session.NodeTypesFacade;
import Session.NodesFacade;
import Session.ProjectsFacade;
import Session.UsersFacade;
import au.com.bytecode.opencsv.CSVReader;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;
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
    private NodeTypesFacade nodeTypesFacade;
    @EJB
    private ProjectsFacade projectsFacade;
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private DataDefinitionsFacade dataDefinitionsFacade;
    @EJB
    private PersistedVariables.PProjects projectsList;
    @EJB
    private PersistedVariables.PProject currProject;
    @EJB
    private PersistedVariables.PUser currUser;
    @EJB
    private PersistedVariables.PNodes currentNodes;
    @EJB
    private PersistedVariables.PCoordinates coordinates;
    @EJB
    private AcceptableDataTypesFacade acceptableDataTypes;
    
    public Projects returnProject()
    {
        return currProject.getCurrentProject();
    }
    public Users returnUser()
    {
        return currProject.getCurrentProject().getUser();
    }


//    public String getFileContent() {
//        return fileContent;
//    }
//
//    public void setFileContent(String fileContent) {
//        this.fileContent = fileContent;
//    }
//    
//    public String getFileContent2() {
//        return fileContent2;
//    }
//
//    public void setFileContent2(String fileContent2) {
//        this.fileContent2 = fileContent2;
//    }
  private Part fileUpload;

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }
  
  public void upload() throws IOException // takes user selected file and converts it into a List
  {

//    File file = new File("/Users/t_sedgman/Desktop/FinalProject/test_output_data.rtf");
      
      InputStream IS = fileUpload.getInputStream();
      InputStreamReader ISR = new InputStreamReader(IS);
      CSVReader reader  = new CSVReader(ISR);
      List<String[]> tempArray = reader.readAll();
   
//    byte[] buffer = new byte[4096];      
//    int bytesRead = 0;
//    while(true) 
//    {                        
//        bytesRead = inputStream.read(buffer);
//        if(bytesRead > 0) 
//        {
//            outputStream.write(buffer, 0, bytesRead);
//        }
//        else 
//        {
//            break;
//        }                       
//    }
    for (int i = 0;i<tempArray.size();i++) // loops over every line from the file 
    {
        String[] tempString = tempArray.get(i);
//        List<String> Record = tempString;  
        dataValuesFacade.RecordData(tempString);
    }
    
  }
    private static String getFilename(Part part) // identifies the location of the file to be uploaded
    {
        for (String cd : part.getHeader("content-disposition").split(";")) 
        {
            if (cd.trim().startsWith("filename")) 
            {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
     
    {
//    try
//    {
//      fis = new FileInputStream(fileUpload);
//      // Here BufferedInputStream is added for fast reading.
//      bis = new BufferedInputStream(fis);
//      dis = new DataInputStream(bis);
//
//      // dis.available() returns 0 if the file does not have more lines.
//      while (dis.available() != 0) 
//      {
//
//      // this statement reads the line from the file and print it to
//        // the console.
//        tempArray.add(dis.readLine());
//      }
//      // dispose all the resources after using them.
//      
//      fis.close();
//      bis.close();
//      dis.close();
    }
   
  
    public List getProjectNodeTypes()
    {
        return nodeTypesFacade.allNodeTypes(currProject.getCurrentProject());
                
    }
    
  private String username;
  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
private Projects project;

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }
private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public SelectItem[] projectList() // returns the contents for the select project dropdown menu on login screen
{
     if (username != null)
     {

        Users temp = usersFacade.findUser(username);
        currUser.setCurrentUser(temp);
        List projects = projectsFacade.findProjectsByUser(username);
        projectsList.setProjects(projects);
    }
     
     if (projectsList.getProjects() == null)
     {
        SelectItem[] returnData = null;
        return returnData;
     }
     else
     {
        SelectItem[] returnData = JsfUtil.getSelectItems(projectsList.getProjects(),true);
        return returnData;
     }
}
    public SelectItem[] dataType() // returns a list of acceptable data types when settin up a data definition
{
    
    SelectItem[] returnData = JsfUtil.getSelectItems(acceptableDataTypes.allDataTypes(),true);
    return returnData;
     
}
    public String initalise() // sets persisted variables for the project scope,
    {
        if (username != null)
        {
            project = projectsFacade.findProjectsByName(projectName,currUser.getCurrentUser().getUsername());
            currProject.setCurrentProject(project);
            currentNodes.setCurrentNodes(nodesFacade.allNodes(project));
            return "load";
        }
        return null;
        
    }
    
    
  public String GPSLat() // calculate the latitide of all the nodes and return it as a list(possible room for efficiencies)
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
   public String GPSLong() // calculate the longitude of all the nodes and return it as a list (possible room for efficiencies)
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
   
   public String NodeName()// returns a list of node names for the project, room to improve with a refined ArrayList of objects. possilb eroom to improeve, but only called once
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
   
   public String getVariables() // get a list of the variables of the nodes, room for efficienies, possible bug with one node presenting the list twice.
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
   private int node;
   private int variable;

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public int getVariable() {
        return variable;
    }

    public void setVariable(int variable) {
        this.variable = variable;
    }
   
   
   public List getGraphData() throws ParseException // get array of data to be plotted on the graph with the dDType prepended for efficiency.
   {
        List dataReturn = null;
        if (!currentNodes.getCurrentNodes().isEmpty())
        {
            dataReturn = dataValuesFacade.findOrderedData(currentNodes.getCurrentNodes().get(node), 0, variable);
            String dataType = dataDefinitionsFacade.getDataType(currProject.getCurrentProject().getProjectId(),variable);
            String dateFormat = null;
            if (!dataReturn.isEmpty())
            {
                dateFormat = determineDateFormat(dataReturn.get(0).toString());
                for (int i = 0;i<dataReturn.size();i++)
                {
                    if (i%2==0)// formats i==even as date.
                    {
                        String string = dataReturn.get(i).toString();
                        Date date = new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(string); 

//                        DateFormat originalFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
//                        DateFormat targetFormat = new SimpleDateFormat("dd/MM/yy-HH:mm:ss");
//                        Date date = originalFormat.parse(string);
//                        String formattedDate = targetFormat.format(date);  // 20120821
                        dataReturn.set(i, date);
                    }

                }
                dataReturn.add(0, dataType);
            }
        
        }
        
        
        
//        ArrayList <DataValues> dataValuesArray = new ArrayList();
//        Collection dataValues =  dataValuesFacade.findVariable(currentNodes.getCurrentNodes().get(node), 0); // get all records for a node at index 0
//        dataValuesArray.addAll(dataValues);
//        for (int j = dataValuesArray.size()-10;j< dataValuesArray.size();j++) // loop over array dataValuesArray.size()-10
//        {
//            Date d = dataValuesArray.get(j).getdTimeStamp(); // get timestamp of each item
//            Collection record = dataValuesFacade.findRecord(d);// get list of records where timestamp is as above
//            ArrayList<DataValues> recordArray = new ArrayList<>();
//            recordArray.addAll(record);
//            records = records.concat(j+"");//recordArray.get(0).getdVariable().toString()); // get timestamp, may need to later adapt to accept 
//            records = records.concat(","); 
//            records = records.concat(recordArray.get(variable).getdVariable().toString()); // get variable of interest
//            records = records.concat("|");     
//        }
        return dataReturn;
   }
   
   
   
   private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{ // author BalusC on Stack overflow: http://stackoverflow.com/questions/3389348/parse-any-date-in-java
    put("^\\d{8}$", "yyyyMMdd");
    put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
    put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
    put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
    put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
    put("^\\d{12}$", "yyyyMMddHHmm");
    put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
    put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
    put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
    put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "dd/MM/yyyy HH:mm");
    put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
    put("^\\d{14}$", "yyyyMMddHHmmss");
    put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
    put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
    put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
    put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd/MM/yyyy HH:mm:ss");
    put("^\\d{1,2}/\\d{1,2}/\\d{2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd/MM/yy HH:mm:ss");
    put("^\\d{1,2}/\\d{1,2}/\\d{2}-\\d{1,2}:\\d{2}:\\d{2}$", "dd/MM/yy-HH:mm:ss");
    put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
   }};

/**
 * Determine SimpleDateFormat pattern matching with the given date string. Returns null if
 * format is unknown. You can simply extend DateUtil with more formats if needed.
 * @param dateString The date string to determine the SimpleDateFormat pattern for.
 * @return The matching SimpleDateFormat pattern, or null if format is unknown.
 * @see SimpleDateFormat
 */
public static String determineDateFormat(String dateString) {
    for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
        if (dateString.toLowerCase().matches(regexp)) {
            return DATE_FORMAT_REGEXPS.get(regexp);
        }
    }
    return null; // Unknown format.
}
      
//  public String getData(int positionId, String nodeIdentifier)
//  {
//      String data = "";
//      int i = 0;
//      Nodes selectedNode;
//      do
//      {
//          selectedNode = currentNodes.getCurrentNodes().get(i);
//      }
//      while (selectedNode.getnTIdentifier() == null ? (nodeIdentifier) != null : !selectedNode.getnTIdentifier().equals(nodeIdentifier));   
//      List<DataValues> dataList = dataValuesFacade.findVariable(selectedNode, positionId);
//      for (int j = 0; j < dataList.size();j++)
//        {
//            String temp = dataList.get(j).getdVariable();
//            data = data.concat(temp);
//            if (j != (dataList.size()-1))
//            { 
//                data = data.concat(","); 
//            }
//        }
//      return data;
//  }
    public String CentrePoint() // calculates the centrepoint of the project, should replace with mapBounds, which will size to the project, rahter than this aproach and using a fixed zoom level.
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
   
    public static Double average(List<BigDecimal> list) // averages the numbers in a list and returns a single value (used in this case for GPS coords. possible bug when usign this aproach near the international date line.
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

    
}
    
//    public String makeJson()
//    {
//        Gson gson = new GsonBuilder().setDateFormat("dd/mm/yy-hh:mm:ss").create();
//        ArrayList markers = new ArrayList();
//        
//        for (int i =0;i<currentNodes.getCurrentNodes().size();i++)
//        {
//            markers.add(jsonNode(i));
//        }
//        String json = gson.toJson(markers);
//        
//        return json;
//    }
//    public Marker jsonNode(int i)
//    {
//        Nodes currentNode = currentNodes.getCurrentNodes().get(i);
//        Marker marker = new Marker();
//        marker.setName(currentNode.getnTIdentifier().toString());
//        marker.setgPSLat(currentNode.getgPSLat().floatValue());
//        marker.setgPSLong(currentNode.getgPSLong().floatValue());
//        marker.setStatic(currentNode.isIsStatic());
//        ArrayList<Variable> variables = new ArrayList();
//        for (int j = 0 ; j < currentNode.getNodeType().getDataDefinitions().size() ; j++)   
//        {
//            Variable variable = variables(currentNode, j);
//            variables.add(variable);
//        }
//        marker.setVariable(variables);
//        return marker;
//    }
//    public Variable variables(Nodes node, int i)
//    {
//        Variable variable = new Variable();
//        Collection currentDataDefinitions =  node.getNodeType().getDataDefinitions();
//        ArrayList cDD = new ArrayList();
//        cDD.addAll(currentDataDefinitions);
//        DataDefinitions data = (DataDefinitions) cDD.get(i);
//        variable.setVariableName(data.getdDName());
//        variable.setVariableUnit(data.getdDUnit());
//        
//        DataPair dataPairs = new DataPair();
//        ArrayList timestamp = new ArrayList();
//        ArrayList tempVariable = new ArrayList();
//        if(node.getDataValues().isEmpty())
//        {
//            dataPairs.setTimestamp(null);
//            dataPairs.setVariable(null);
//        }
//        else
//        {
//            timestamp.addAll(dataValuesFacade.findVariable(node, 0));
//            dataPairs.setTimestamp(timestamp);
//            tempVariable.addAll(dataValuesFacade.findVariable(node,i));
//            dataPairs.setVariable (tempVariable);
//        }
//        dataPairs.setVariableOffset(null);// need to fill with data from website
//        
//        variable.setData(dataPairs);
//        
//        return variable;
//    }
//    public Projects title()
//    {
//        Projects title = currProject.getCurrentProject();
//        return title;
//    }
//    


