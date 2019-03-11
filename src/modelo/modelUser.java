
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class modelUser {
    
    private final pool conexion;
    
    public modelUser(){
        conexion=new pool();
    }
    
    
    
    
    public ArrayList<User> login(String user, String clave){
    
        
        Connection conectar=null;
        PreparedStatement pst;
        ResultSet rs ;
        User cuenta ;
        ArrayList list = new ArrayList();
        
        try{

            // obtenemos la conexion con la base de datos
            conectar = conexion.dataSource.getConnection();
            
            if(conectar != null){
                
                //SELECT CAST(AES_DECRYPT(Pass, "key")AS char(50)) FROM `usuarios` WHERE 1                
                
                String sql ="SELECT User, Pass FROM usuarios WHERE User =?   AND Pass=AES_ENCRYPT(?, 'key')";
                
                pst = conectar.prepareStatement(sql);
                pst.setString(1, user);
                pst.setString(2, clave);
                
                rs = pst.executeQuery();
                                
                if(rs.next()){
                    
                    cuenta = new User();
                    cuenta.setUser(rs.getString("User"));
                    cuenta.setPass(rs.getString("Pass"));                    
                    list.add(cuenta);

                }
                 
            }else{
                JOptionPane.showMessageDialog(null, "Hubo un error al realizar la operacion, intente mas tarde","ERROR",JOptionPane.ERROR_MESSAGE);
            }
                
        
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e , " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }finally{
        
            try{
                conectar.close();
            }catch(SQLException ex){
                System.out.println("error "+ex);
            }
        
        }
        
       return list;
         
    }    
   
    
    
    
    
}
