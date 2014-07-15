/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author t_sedgman
 */
@Named(value = "testbean")
@Dependent
public class Testbean {

    private String testString;
    /**
     * Creates a new instance of Testbean
     */
    public Testbean() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader("Desktop/FinalProject/test_output_data.rtf")))
	{
            
            String NewLine;
            String[] Variables;
            do
            {
                NewLine = br.readLine();
                if (NewLine != null)
                {
                    Variables = NewLine.split(",");
                    testString = Variables.toString();
                    
                }
                else
                {
                    br.close();
                }
            }
            while (NewLine !=null);
        }    
        catch (IOException e) 
        {
            e.printStackTrace();
	} 
        
    }
    public void nextChar()
    {
        
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }
    
}
