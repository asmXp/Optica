/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.ClasesAuxiliares;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dd
 */
public class filtroUrl implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext= event.getFacesContext();
        
        //Capturamos el nombre de la pagina actual
        String currentPage=facesContext.getViewRoot().getViewId();
        
        //Creamos una variable boleana para comprovar si en la pagina de login lo que se capturo
        boolean isPageLogin= currentPage.lastIndexOf("login.xhtml") > -1 ? true : false;
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        //Recuperamos un objeto del string que se guardo, para ello se toma la session al usuario que se
        //definio en lo loginBean
        Object usuario=session.getAttribute("usuario");
        
        if(!isPageLogin && usuario==null){// si no la pagina de logueo y el usuarioes nulo, lo redirigimos ala pagina
            NavigationHandler nHandler= facesContext.getApplication().getNavigationHandler();
            nHandler.handleNavigation(facesContext, null, "/login.xhtml");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW; 
    }
    
}
