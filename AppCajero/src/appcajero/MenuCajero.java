package appcajero;
import java.util.Scanner;

public class MenuCajero {
    //delcaracion variables d einstancia
    private final Banco banco; //objeto banco para manejar las operaciones del banco
    private final Scanner scanner; //Objeto scanner para recibir la entrada del usuario
    private Cliente clienteActual; //cliente actual logueado
    
    //constructor
    public MenuCajero(Banco banco){
        this.banco = banco; //inicializar objeto Banco
        this.scanner = new Scanner(System.in); //inicializar objeto scanner
    }//fin constructor
    
    //metodo para mostrar el menu principal
    public void mostrarMenuPrincipal() {
        System.out.println("-------------------------------------");
        System.out.println("Bienvenido al cajero automatico");
        System.out.println("-------------------------------------");
        System.out.println("1. Iniciar sesion");
        System.out.println("2. Salir");
        System.out.println("-------------------------------------");
    }// fin metodo
    
    //metodo para ejecutar el menu principal
    public void ejecutarMenuPrincipal() {
        while (true) { //bucle infinito que mantiene el menu activo
            mostrarMenuPrincipal(); //muestra el menu principal
            int opcion = scanner.nextInt(); //lee la opcion ingresada por el usuario

            if (opcion == 1) {
                iniciarSesion(); //inicia sesion
            } else if (opcion == 2) {
                scanner.close(); //se cierra el scanner si la opcion es 2
                break; //sale dle bucle
            } else {
                System.out.println("-------------------------------------");
                System.out.println("Opcion invalida");
                System.out.println("-------------------------------------");
            }
        }
    }//fin metodo

    //metodo para iniciar sesion
    public void iniciarSesion() {
        System.out.print("-------------------------------------\n");
        System.out.print("Ingrese su numero de cuenta: ");
        String numeroCuenta = scanner.next(); //lee numero de cuenta ingresado
        System.out.print("Ingrese su contrasena: ");
        String contrasena = scanner.next(); //lee contrasena ingresada1
        System.out.print("-------------------------------------\n");
        

        clienteActual = banco.iniciarSesion(numeroCuenta, contrasena); //intentar iniciar sesion con el banco

        if (clienteActual != null) {
            clienteActual.menuOperaciones(scanner); //muestra el menu de operaciones si el cliente es valido
        } else {
            System.out.println("\n");
        }
    }

}
