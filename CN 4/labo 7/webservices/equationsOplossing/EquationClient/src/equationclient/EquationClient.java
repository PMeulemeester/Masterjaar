package equationclient;

/**
 * @author wijnand.schepens@hogent.be
 */
public class EquationClient {

    public static void main(String[] args) {

        for (double root : solveQuadratic(1, -5, 6)) {
            System.out.println(root);
        }
    }

    private static java.util.List<java.lang.Double> solveQuadratic(double a, double b, double c) {
        client.EquationService_Service service = new client.EquationService_Service();
        client.EquationService port = service.getEquationServicePort();
        return port.solveQuadratic(a, b, c);
    }

}
