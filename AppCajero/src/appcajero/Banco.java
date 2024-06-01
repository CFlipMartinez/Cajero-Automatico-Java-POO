package appcajero;
import java.io.BufferedReader; //leer archivos
import java.io.FileReader; //leer archivos
import java.io.IOException; //manejar excepciones
import java.util.ArrayList; //usar ArrayList
import java.util.List; //usar interfaz List

public class Banco {
    //declaracion variables de instancia
    private final List<Cliente> clientes; //lista de clientes del banco
    private int intentosFallidos; //contador de intentos fallidos
    private static final int MAX_INTENTOS = 3; //numero maximo de intentos
    
    //constuctor
    public Banco(){
        this.clientes = new ArrayList<>(); //inicializa lista de clientes
        this.cargarClientes(); //llamada al metodo para cargar clientes desde un TXT
    }
    
    //metodo para cargar clientes por txt
    private void cargarClientes(){
        String ruta = "C:\\Users\\Cafel\\Documents\\NetBeansProjects\\AppCajero\\AppCajero\\src\\appcajero\\clientes.txt"; // o la ruta completa si lo sabes
        System.out.println("Ruta del archivo: " + ruta);
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) { //intentar abrir y leer archivo clientes.txt
            String linea; //variable para almacenar cada linea del archivo
            while ((linea = br.readLine()) != null) { // Lee cada línea del archivo hasta que no haya más líneas
                String[] datos = linea.split(","); // Divide la línea en partes usando la coma como delimitador
                String numeroCuenta = datos[0]; //primera parte de la linea: usuario
                String contrasena = datos[1]; // segunta parte d ela linea: contrasena
                double saldoInicial = Double.parseDouble(datos[2]); // Tercera parte de la línea: saldo inicial convertido a double
                Cliente cliente = new Cliente(numeroCuenta, contrasena, saldoInicial); // Crea un nuevo cliente usando los datos leídos
                clientes.add(cliente); // Añade el cliente a la lista de clientes
            }
        } catch (IOException e) {// Captura cualquier excepción de E/S
            e.printStackTrace(); // Imprime el seguimiento de errores
        }
    }//fin metodo
    
    //metodo para iniciar sesion
    public Cliente iniciarSesion(String numeroCuenta, String contrasena) {
        for (Cliente cliente : clientes) { //recorrer lista de clientes usando ForEach
            if (cliente.getNumeroCuenta().equals(numeroCuenta) && cliente.getContrasena().equals(contrasena)) {// Comprueba si el usuario y la contraseña coinciden
                this.intentosFallidos = 0;
                return cliente; // Retorna el cliente si las credenciales son correctas
            }
        }
        this.intentosFallidos++;
        if (this.intentosFallidos >= this.MAX_INTENTOS) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Has alcanzado el máximo de intentos de inicio de sesión");
            System.out.println("-------------------------------------------------------");
            return null;
        }
        System.out.println("-----------------------------------------");
        System.out.println("Número de cuenta o contraseña incorrectos");
        System.out.println("-----------------------------------------");
        return null; // Retorna null si no se encuentra un cliente con las credenciales dadas
    }//fin metodo
    
}//fin clase
