
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import loginmvc.Inicio;
import loginmvc.login;
import modelo.User;
import modelo.modelUser;


public class controlador implements ActionListener{

    private final login view ;
    private final modelUser model=new modelUser();
    
    public final void events(){
        view.btn_enter.addActionListener(this);
        view.btn_exit.addActionListener(this);
        view.checkviewpass.addActionListener(this);
    }
    
    public controlador(login vista){
        this.view=vista;
        events();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object evt = e.getSource();
       
        if(evt.equals(view.btn_enter)){
            
            char clave[]=view.txt_pass.getPassword();
            String clavedef=new String(clave);
        
            if(clavedef.isEmpty() || view.txt_user.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Debe digitar un Usuario y una Contraseña", "Error en la Operación", JOptionPane.ERROR_MESSAGE);

            }else{
                
                ArrayList<User> list;
                list = model.login(view.txt_user.getText(), clavedef);
                
                if(list.size()>0){
                    
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema", "INFO.", JOptionPane.INFORMATION_MESSAGE);
                    view.dispose();
                    Inicio inicio = new Inicio();
                    inicio.setVisible(true);
                    
                }else{

                    JOptionPane.showMessageDialog(null, "Acceso denegado", "ERR.", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }
            
            
        }else if(evt.equals(view.btn_exit)){
            
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta Seguro Que Desea Salir Del Sistema?");
            
            if(confirmar==JOptionPane.YES_OPTION){
                System.exit(0);
            }
                        
        }else if(evt.equals(view.checkviewpass)){
            
            if(view.checkviewpass.isSelected()){
                view.txt_pass.setEchoChar((char)0);
            }else{
                view.txt_pass.setEchoChar('*');                
            }
            
        }

        
        
    }

    

    
}
