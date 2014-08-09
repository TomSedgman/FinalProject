/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestInput;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author t_sedgman
 */
public class MarkerCode {
    

public static class Marker 
    {
        private String name;
        private Float gPSLat;
        private Float gPSLong;
        private Boolean Static;
        private ArrayList<Variable> variables; 

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Float getgPSLat() {
            return gPSLat;
        }

        public void setgPSLat(Float gPSLat) {
            this.gPSLat = gPSLat;
        }

        public Float getgPSLong() {
            return gPSLong;
        }

        public void setgPSLong(Float gPSLong) {
            this.gPSLong = gPSLong;
        }

        public Boolean getStatic() {
            return Static;
        }

        public void setStatic(Boolean Static) {
            this.Static = Static;
        }

        public ArrayList<Variable> getVariable() {
            return variables;
        }

        public void setVariable(ArrayList<Variable> variables) {
            this.variables = variables;
        }
        
        
    }

    public static class Variable 
    {
        private String variableName;
        private String variableUnit;
        private DataPair data;

        public String getVariableName() {
            return variableName;
        }

        public void setVariableName(String variableName) {
            this.variableName = variableName;
        }

        public String getVariableUnit() {
            return variableUnit;
        }

        public void setVariableUnit(String variableUnit) {
            this.variableUnit = variableUnit;
        }

        public DataPair getData() {
            return data;
        }

        public void setData(DataPair data) {
            this.data = data;
        }

       
        
        
    }
    
    
    public static class DataPair 
    {
        private ArrayList<Date> timestamp;
        private ArrayList<Float> variable;
        private ArrayList<Float> variableOffset;

        public ArrayList<Date> getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(ArrayList<Date> timestamp) {
            this.timestamp = timestamp;
        }

        public ArrayList<Float> getVariable() {
            return variable;
        }

        public void setVariable(ArrayList<Float> variable) {
            this.variable = variable;
        }

        public ArrayList<Float> getVariableOffset() {
            return variableOffset;
        }

        public void setVariableOffset(ArrayList<Float> variableOffset) {
            this.variableOffset = variableOffset;
        }

        
        
        
    }
}