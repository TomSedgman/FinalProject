/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistedVariables;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author t_sedgman
 */
@Named (value = "coordinates")
@Stateless
public class PCoordinates {

    /**
     * Creates a new instance of PCoordinates
     */
    public PCoordinates() {
    }
    private List<Float> GPSLat;
    private List<Float> GPSLong;
    private String Centrepoint;

    public List<Float> getGPSLat() {
        return GPSLat;
    }

    public void setGPSLat(List<Float> GPSLat) {
        this.GPSLat = GPSLat;
    }

    public List<Float> getGPSLong() {
        return GPSLong;
    }

    public void setGPSLong(List<Float> GPSLong) {
        this.GPSLong = GPSLong;
    }

    public String getCentrepoint() {
        return Centrepoint;
    }

    public void setCentrepoint(String Centrepoint) {
        this.Centrepoint = Centrepoint;
    }
    
}
