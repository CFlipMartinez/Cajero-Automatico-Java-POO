
package appcajero;

public class CuentaHija extends Cuenta{
    private Cliente clientePadre;
    
    public CuentaHija(double saldo, boolean principal, Cliente cientePadre, String numeroCuenta){
        super(saldo, principal, numeroCuenta);
        this.clientePadre = clientePadre;
        
        
    }
    //metodo para ajustar el saldo
    @Override
    public void setSaldo(double saldo){
        super.setSaldo(saldo); // Llama al método de la clase padre
    }
    
    
}
