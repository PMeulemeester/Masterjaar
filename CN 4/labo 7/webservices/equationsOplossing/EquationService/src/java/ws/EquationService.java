/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Wijnand
 */
@WebService(serviceName = "EquationService")
public class EquationService {

    @WebMethod
    public double[] solveQuadratic(@WebParam(name = "a") double a,
            @WebParam(name = "b") double b,
            @WebParam(name = "c") double c) {

        // solve a x^2 + b x + c
        double discr = b * b - 4 * a * c;
        if (discr < 0) {
            return new double[0];
        } else if (Math.abs(discr) < 1e-10) {
            return new double[]{(-b + Math.sqrt(discr)) / (2 * a)};
        } else {
            return new double[]{
                (-b + Math.sqrt(discr)) / (2 * a),
                (-b - Math.sqrt(discr)) / (2 * a)
            };
        }
    }
}
