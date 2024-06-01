package appcajero;
import java.io.BufferedWriter; //leer archivos
import java.io.FileWriter; // leer archivos
import java.io.IOException; //manejar excepciones de E/S
import java.util.ArrayList; // usar arrayList
import java.util.List; //usar interfaz List
import java.util.Scanner;

public class Cliente {
    //declaracion de variables de instancia
    private String numeroCuenta; //Almacena el numero de cuenta
    private String contrasena; // almacena la contraseña
    private double saldo; // Almacena el saldo
    private List<Cuenta> cuentas; // lista de cuentas asociadas al cliente
    
    
    //constructor
    public Cliente(String numeroCuenta, String contrasena, double saldo){
        this.setNumeroCuenta(numeroCuenta); //inicializa numero de cuenta
        this.setContrasena(contrasena); //inicializa contraseña
        this.setSaldo(saldo); //inicialisa saldo
        this.cuentas = new ArrayList<>(); //inicializa lista de cuentas usando ArrayList
        //añadir cuentas a la lista
        cuentas.add(new Cuenta(saldo, true, numeroCuenta)); //añadir la cuenta principal
        cuentas.add(new CuentaHija(0, false, this, numeroCuenta + "-1")); //añadir primer cuenta hija
        cuentas.add(new CuentaHija(0, false, this, numeroCuenta + "-2")); //añadir segunda cuenta hija
        
    }
    //setter y getters
    public void setNumeroCuenta(String numeroCuenta){
        this.numeroCuenta = numeroCuenta;
    }// fin metodo
    public String getNumeroCuenta() {
        return numeroCuenta;
    }// fin metodo
    
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }// fin metodo
    public String getContrasena() {
        return contrasena;
    }// fin metodo
    
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }// fin metodo
    public double getSaldo() {
        return saldo;
    } // fin metodo
    
    // Método setter para establecer la lista de cuentas
    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas; // Asigna el valor del parámetro cuentas a la variable de instancia cuentas
    } // fin metodo
    
    //metodo retirarDinero
    public void retirarDinero(Scanner scanner){
        System.out.println("-------------------------------------");
        System.out.print("Ingrese el monto a retirar: ");
        double monto = scanner.nextDouble();
        System.out.println("-------------------------------------");
        
        
        //validacion de cuanto saldo tiene
        if(monto > this.getSaldo()){
            System.out.println("-------------------------------------");
            System.out.println("Saldo insuficiente");
            System.out.println("-------------------------------------");
        }
        else{
            saldo -= monto; //reducir saldo al retirar
            System.out.println("-------------------------------------");
            System.out.println("Por favor, retire su dinero....");
            System.out.println("-------------------------------------");
        }
    }// fin metodo 
    
    //metodo consultarSaldo
    public void consultarSaldo(){
        System.out.println("-------------------------------------");
        System.out.println("Saldo actual: $"+getSaldo());
        System.out.println("-------------------------------------");
    }// fin metodo
    
    //metodo ingresarDinero
    public void ingresarDinero(Scanner scanner){
        System.out.println("-------------------------------------");
        System.out.print("Ingrese el monto a depositar: ");
        double monto = scanner.nextDouble();
        System.out.println("-------------------------------------");
        
        
        //validacion de consignacion
        if(monto > 1000000){
            System.out.println("----------------------------------------------");
            System.out.println("Invalido.\nNo puede realizar consignaciones de mas de $1.000.000 por transaccion");
            System.out.println("----------------------------------------------");
        }
        else{
            saldo += monto; //incrementa el saldo por el deposito
            System.out.println("-------------------------------------");
            System.out.println("Consignacion exitosa");
            System.out.println("-------------------------------------");
        }
    }// fin metodo
    
    //metodo enviarDinero
    public void enviarDinero(Scanner scanner){
        System.out.println("-------------------------------------");
        System.out.println("Seleccione la cuenta de destino: ");
        System.out.println("-------------------------------------");
        for (int i = 0; i < cuentas.size(); i++){
            Cuenta cuenta = cuentas.get(i);
            //validar si es una cuenta hija
            if(cuenta.isHija()){
                System.out.println((i + 1) + ". Cuenta hija (" + cuenta.getSaldo() + ")");
                System.out.println("-------------------------------------");
            }
        }// fin for
        
        int opcion = scanner.nextInt();
        if(opcion < 1 || opcion > cuentas.size() || !cuentas.get(opcion - 1).isHija()){
            System.out.println("-------------------------------------");
            System.out.println("Opcion invalida");
            System.out.println("-------------------------------------");
            return;
        }
        Cuenta cuentaDestino = cuentas.get(opcion - 1); //obtiene la cuenta de destino
        
        System.out.println("------------------------------------------");
        System.out.print("Ingrese el monto que desea transferir: ");
        double monto = scanner.nextDouble();
        System.out.println("------------------------------------------");
        
        
        //validacion de saldo suficiente
        if(monto > getSaldo()){
            System.out.println("-------------------------------------");
            System.out.println("saldo Insuficiente");
            System.out.println("-------------------------------------");
        }
        else{
            saldo -= monto; //reduce el saldo por la transferencia
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
            System.out.println("-------------------------------------");
            System.out.println("Transferencia exitosa");
            System.out.println("-------------------------------------");
        }
    }// fin metodo
    
    //metodo descargarMovimientos()
    public void descargarMovimientos(){
        String ruta = "C:\\Users\\Cafel\\Documents\\NetBeansProjects\\AppCajero\\AppCajero\\src\\appcajero\\movimientos.txt"; // o la ruta completa si lo sabes
        //System.out.println("Ruta del archivo: " + ruta);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ruta)) ){
            bw.write("Movimientos de " + getNumeroCuenta() + ":");
            bw.newLine();
            bw.write("Saldo: $" + getSaldo());
            bw.newLine();

            for (Cuenta cuenta : cuentas) {
                bw.write("Cuenta " + cuenta.getNumeroCuenta() + ": $" + cuenta.getSaldo());
                bw.newLine();
            }
            System.out.println("---------------------------------------------");
            System.out.println("Movimientos descargados en movimientos.txt");
            System.out.println("---------------------------------------------");
        }catch (IOException e){
            e.printStackTrace();
        }
    }//fin metodo
    
    //metodo operaciones
    public void menuOperaciones (Scanner scanner){
        while(true){
            //proeso
            System.out.println("-------------------------------------");
            System.out.println("Seleccione una opcion:");
            System.out.println("-------------------------------------");
            System.out.println("1. Retirar dinero");
            System.out.println("2. Consultar saldo");
            System.out.println("3. Consignar dinero");
            System.out.println("4. Consignar dinero a otra cuenta");
            System.out.println("5. Descargar movimientos");
            System.out.println("6. Cerrar sesion");
            System.out.println("-------------------------------------");
            //delcaracion de variable para almacenar la opcion
            int opcion = scanner.nextInt();
            
            switch(opcion){
                case 1:
                    retirarDinero(scanner);
                    break;
                case 2:
                    consultarSaldo();
                    break;
                case 3:
                    ingresarDinero(scanner);
                    break;
                case 4:
                    enviarDinero(scanner);
                    break;
                case 5:
                    descargarMovimientos();
                    break;
                case 6:
                    System.out.println("-------------------------------------");
                    System.out.println("Cerrando Sesion...");
                    System.out.println("-------------------------------------");
                    return;
                default:
                    System.out.println("-------------------------------------");
                    System.out.println("Opcion Invalida");
                    System.out.println("-------------------------------------");
            }// fin switch
            
        }// fin while
    }// fin metodo 
}// fin clase
