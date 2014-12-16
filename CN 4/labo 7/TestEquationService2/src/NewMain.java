
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pieterm
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Double> op=solveQuadratic(5, 1, 2);
        for(double d:op){
            System.out.println(d);
        }
    }

    private static java.util.List<java.lang.Double> solveQuadratic(double a, double b, double c) {
        ws.Equation_Service service = new ws.Equation_Service();
        ws.Equation port = service.getEquationPort();
        return port.solveQuadratic(a, b, c);
    }
    
}
