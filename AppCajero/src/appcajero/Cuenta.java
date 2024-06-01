
package appcajero;

public class Cuenta {
    //declaracion d evariables de instancia
    private double saldo; //almacena el saldo d ela cuenta
    private boolean principal; //indica si la cuenta es principal(padre) o secundaria (hija)
    private String numeroCuenta; //almacena el numero de cuenta
    
    //constructor
    public Cuenta(double saldo, boolean principal, String numeroCuenta){
        this.setSaldo(saldo); //inicializa el saldo 
        this.principal = principal; //inicializa la valiable principal
        this.setNumeroCuenta(numeroCuenta); //Inicializa el numero de cuenta
    }
    
    //setter y getter
    public void setSaldo(double saldo){
        this.saldo = saldo; //Asigna el valor del parámetro saldo a la variable de instancia saldo
    }
    
    public double getSaldo(){
        return saldo; //Retorna el valor del saldo de la cuenta
    }
    
    public void setNumeroCuenta(String numeroCuenta){
        this.numeroCuenta = numeroCuenta; //Asigna el valor del parámetro numeroCuenta a la variable de instancia numeroCuenta
    }
    
    public String getNumeroCuenta(){
        return numeroCuenta; //Retorna el valor del número de cuenta
    }
    
    public boolean isHija(){
        return !principal; //Retorna true si la cuenta no es principal (es secundaria)
 
    }
    
}
