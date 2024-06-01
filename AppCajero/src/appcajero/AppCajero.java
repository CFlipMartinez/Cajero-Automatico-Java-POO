package appcajero;

public class AppCajero {

    public static void main(String[] args) {
        //instanciar objetos
        Banco banco = new Banco();
        MenuCajero menuCajero = new MenuCajero(banco);
        
        menuCajero.ejecutarMenuPrincipal();
    }
    
}
