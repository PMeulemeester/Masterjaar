/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gegevens;

import be.ugent.tiwi.immo.interfaces.ImmoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author pieterm
 */
@ManagedBean
@SessionScoped
public class GebruikersKeuze {

    protected static int gebouwtype;
    protected static double min,max;

    public String getMaxError() {
        return maxError;
    }

    public void setMaxError(String maxError) {
        this.maxError = maxError;
    }

    public String getMinError() {
        return minError;
    }

    public void setMinError(String minError) {
        this.minError = minError;
    }
    protected String maxError="",minError="";
    private Gegevens g;

    public int getGebouwtype() {
        return gebouwtype;
    }

    public void setGebouwtype(int gebouwtype) {
        this.gebouwtype = gebouwtype;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
    
    public GebruikersKeuze() {
    }
    
    public void ControleerMin(FacesContext context,
     UIComponent toValidate, Object value){
        try{
            Double.parseDouble((String) value);
            minError="";
            min=Double.parseDouble((String) value);
        }catch(NumberFormatException e){
            ((UIInput)toValidate).setValid(false);
            minError = "Getal invullen aub";
        }
    }
    public void ControleerMax(FacesContext context,
     UIComponent toValidate, Object value){
        try{
            Double.parseDouble((String) value);
            
            max=Double.parseDouble((String) value);
            if(max<min){
                ((UIInput)toValidate).setValid(false);
                maxError="Getal groter dan minimum!";
            }
            else{
                maxError="";
            }
        }catch(NumberFormatException e){
            ((UIInput)toValidate).setValid(false);
            maxError= "Getal invullen aub";
            
        }
    }

    
}
