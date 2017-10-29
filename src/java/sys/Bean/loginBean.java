/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.Bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import sys.model.Usuario;
import java.awt.event.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import sys.DAO.usuarioDao;
import sys.imp.usuarioDaoImp;

/**
 *
 * @author dd
 */
@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable {

    private Usuario usuario;
    private String nombreUsuario;     
    private String password;
    
    public loginBean() {
        this.usuario= new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
 
    
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        String ruta="";
         
        usuarioDao uDao=new usuarioDaoImp();
        this.usuario= uDao.login(this.usuario);
        
        if(this.usuario != null ) {
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",this.usuario.getNombreUsuario());
            
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.usuario.getNombreUsuario());
            ruta="/Beglocchi/faces/Views/bienvenido.xhtml";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de acceso!!!", "Usuario o Contraseña son incorrectos");
            this.usuario= new Usuario();
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn",loggedIn);
        context.addCallbackParam("ruta", ruta);
    }
    
    //Metodo para cerrar la secion
    
    public String cerrarSession(){
        this.nombreUsuario=null;
        this.password=null;
        
        HttpSession httpSession =(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate(); //para borrar la session
        return "/login";
    }
    
    
}
