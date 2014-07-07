/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInput;

import Classes.NodesController;
import Entity.DataValues;
import Entity.Nodes;
import Session.DataValuesFacade;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

/**
 *
 * @author t_sedgman
 */
@SessionScoped
public class Bean {
    @EJB
    private DataValuesFacade dataValuesFacade;

    
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

    File file = new File("/Users/t_sedgman/Desktop/FinalProject/test_output_data_small.rtf");
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
      ArrayList<String> tempArray = new ArrayList<>();
      tempArray = getFileContentArray();
      int size = tempArray.size();
      if (counter <= size)
      {
        counter++;
        fileContent2 = tempArray.get(counter);
      }
      else
      {
          counter = 0;
      }
  }
}
