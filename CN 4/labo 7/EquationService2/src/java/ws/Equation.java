/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author pieterm
 */
@WebService(serviceName = "Equation")
public class Equation {

    /**
     * This is a sample web service operation
     */
    /**
     * Web service operation
     */
    @WebMethod(operationName = "solveQuadratic")
    public double[] solveQuadratic(@WebParam(name = "a") double a, @WebParam(name = "b") double b, @WebParam(name = "c") double c) {
        double d=b*b-4*a*c;
        double l1=(-b + Math.sqrt(d))/(2*a);
        double l2=(-b - Math.sqrt(d))/(2*a);
        double[] op;
        if(d>0){
            op=new double[2];
            op[0]=l1;
            op[1]=l2;
        }else if(d==0){
            op=new double[1];
            op[0]=l1;
        }else{
            op=new double[0];
        }
        
        return op;
    }
}
