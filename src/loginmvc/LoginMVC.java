
package loginmvc;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginMVC {


    public static void main(String[] args) {
        
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(UnsupportedLookAndFeelException ex){
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LoginMVC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        login vista = new login();
        vista.setVisible(true);
    }
    
}
