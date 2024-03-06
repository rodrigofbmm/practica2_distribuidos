import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TestClient {

    private static final String HOST = "localhost"; // Host del servidor
    private static final int PUERTO = 1100; // Puerto donde el servidor está escuchando

    @Test
    public void testCalculadora() {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PUERTO);
            Interfaz stub = (Interfaz) registry.lookup("Calculadora");

            // Test the methods
            float numero1 = 10;
            float numero2 = 5;

            assertEquals(15, stub.sumar(numero1, numero2), "Suma incorrecta");
            assertEquals(5, stub.restar(numero1, numero2), "Resta incorrecta");
            assertEquals(50, stub.multiplicar(numero1, numero2), "Multiplicación incorrecta");
            assertEquals(2, stub.dividir(numero1, numero2), "División incorrecta");
            assertEquals(3.1622777f, stub.raizCuadrada(numero1), 0.00001f, "Raíz cuadrada incorrecta");

            System.out.println("All calculations are correct.");
        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.toString());
            e.printStackTrace();
            fail("Error en el cliente: " + e.getMessage());
        }
    }
}
