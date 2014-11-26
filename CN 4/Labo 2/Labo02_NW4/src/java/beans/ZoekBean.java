/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import be.ugent.tiwi.immo.interfaces.IImmoKantoor;
import be.ugent.tiwi.immo.interfaces.IPand;
import be.ugent.tiwi.immo.interfaces.ImmoException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author pieterm
 */
public class ZoekBean {
    private int type;
    private ResourceBundle labels;
    private double min,max;
    private boolean nietsGevonden;
    private List<IPand> panden;
    private IImmoKantoor immoData;

    public ZoekBean() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ResourceBundle getLabels() {
        return labels;
    }

    public void setLabels(ResourceBundle labels) {
        this.labels = labels;
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

    public boolean isNietsGevonden() {
        return nietsGevonden;
    }

    public void setNietsGevonden(boolean nietsGevonden) {
        this.nietsGevonden = nietsGevonden;
    }

    public List<IPand> getPanden() {
        return panden;
    }

    public void setPanden(List<IPand> panden) {
        this.panden = panden;
    }

    public IImmoKantoor getImmoData() {
        return immoData;
    }

    public void setImmoData(IImmoKantoor immoData) {
        this.immoData = immoData;
    }
    public String zoek(){
        String resultaat="nietsGevonden";
        nietsGevonden=true;
        try {
            panden=immoData.getPanden(getType(), getMin(), getMax());
            if(!panden.isEmpty()){
                resultaat="pandenGevonden";
                nietsGevonden=false;
            }
        } catch (ImmoException ex) {
            Logger.getLogger(ZoekBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultaat;
    }
    public String back(){
        return "nietsGevonden";
    }
    public void isGroter(FacesContext context, UIComponent component, Object value){
        double maximum = ((Double)value).doubleValue();
        UIInput invoerMinimum = (UIInput)component.findComponent("minimum");
        double minimum = ((Double)invoerMinimum.getValue()).doubleValue();
        if(maximum <= minimum){
            ((UIInput)component).setValid(false);
            context.addMessage(component.getClientId(), new FacesMessage(labels.getString("txt_foutMinMax")));
        }
    }
}
