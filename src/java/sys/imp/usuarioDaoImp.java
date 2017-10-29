/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.*;
import sys.ClasesAuxiliares.encriptarPassword;
import sys.DAO.usuarioDao;
import sys.model.Usuario;
import sys.util.HibernateUtil;

/**
 *
 * @author Unitec
 */
public class usuarioDaoImp implements usuarioDao{

    @Override
    public Usuario obtenerDatosPorUsuario(Usuario usuario) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Usuario WHERE nombreUsuario=:nombreUsuario";
        Query q=session.createQuery(hql);
        q.setParameter("nombreUsuario", usuario.getNombreUsuario());
        return (Usuario) q.uniqueResult();
    }

    @Override
    public Usuario login(Usuario usuario) {
        Usuario user=this.obtenerDatosPorUsuario(usuario);
        if (user!=null) {
            if (!user.getPassword().equals(encriptarPassword.sha512(usuario.getPassword()))) {
                user=null;
            }
        }
        return user;
    }
    
}
