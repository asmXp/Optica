/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.DAO.vendedorDao;
import sys.model.Cliente;
import sys.model.Vendedor;
import sys.util.HibernateUtil;

/**
 *
 * @author Unitec
 */
public class vendedorDaoImp implements vendedorDao {

    @Override
    public List<Vendedor> listarVendedor() {
        List<Vendedor> lista=null;
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction t= session.beginTransaction();
        String hql="FROM Vendedor";

        try{
         lista=session.createQuery(hql).list();
         t.commit();
         session.close();
        }catch(Exception e){
         t.rollback();
        }
        return lista;
    }

    @Override
    public void newVendedor(Vendedor vendedor) {
        Session session=null;

        try{
         session=HibernateUtil.getSessionFactory().openSession();
         session.beginTransaction();
         session.save(vendedor);
         session.getTransaction().commit();
        }catch(Exception e){
         System.out.println("e.getMessage");
         session.getTransaction().rollback();
        }finally{
        
            if(session!=null){
                  session.close();
             }
        }
    }

    @Override
    public void updateVendedor(Vendedor vendedor) {
        Session session=null;

        try{
         session=HibernateUtil.getSessionFactory().openSession();
         session.beginTransaction();
         session.update(vendedor);
         session.getTransaction().commit();
        }catch(Exception e){
         System.out.println("e.getMessage");
         session.getTransaction().rollback();
        }finally{
        
            if(session!=null){
                  session.close();
             }
        }
    }

    @Override
    public void deleteVendedor(Vendedor vendedor) {
        Session session=null;

        try{
         session=HibernateUtil.getSessionFactory().openSession();
         session.beginTransaction();
         session.delete(vendedor);
         session.getTransaction().commit();
        }catch(Exception e){
         System.out.println("e.getMessage");
         session.getTransaction().rollback();
        }finally{
        
            if(session!=null){
                  session.close();
             }
        }
    }
    
}
