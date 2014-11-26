/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.util.List;
import ws.Equation;
import ws.Equation_Service;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;

/**
 *
 * @author pieterm
 */
public class testKlasse {
    public static void main(String[] args) {
        
        List<Double> d=testKlasse.solveQuadratic(1, -5, 6);
        for(double dd:d){
            System.out.println(dd);
        }

    }

    private static java.util.List<java.lang.Double> solveQuadratic(double a, double b, double c) {
        ws.Equation_Service service = new ws.Equation_Service();
        ws.Equation port = service.getEquationPort();
        return port.solveQuadratic(a, b, c);
    }
}
