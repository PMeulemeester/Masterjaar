/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Pieter
 */

public class Now extends Date{
    private static final long serialVersionUID = 1L;
    public String huidige_dag(){
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
}
