/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author t_sedgman
 */
@Named(value = "testbean")
@Dependent
public class Testbean {

    /**
     * Creates a new instance of Testbean
     */
    public Testbean() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader("Desktop/FinalProject/test_output_data.rtf")))
	{
            String NewLine;
            String[] Variables = null;
            while ((NewLine = br.readLine()) != null) 
            {
                int i = 0;
                i++;
                String Variable;
                Variables = NewLine.split(",");
                
            }
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
	} 
    }
}
