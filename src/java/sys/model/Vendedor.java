package sys.model;
// Generated 26/07/2017 12:53:06 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Vendedor generated by hbm2java
 */
public class Vendedor  implements java.io.Serializable {


     private Integer idVendedor;
     private String nombre;
     private String apellidos;
     private String dui;
     private String celular;
     private String direccion;
     private Set<Factura> facturas = new HashSet<Factura>(0);
     private Set<Usuario> usuarios = new HashSet<Usuario>(0);

    public Vendedor() {
    }

	
    public Vendedor(String nombre, String apellidos, String dui, String celular, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dui = dui;
        this.celular = celular;
        this.direccion = direccion;
    }
    public Vendedor(String nombre, String apellidos, String dui, String celular, String direccion, Set<Factura> facturas, Set<Usuario> usuarios) {
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.dui = dui;
       this.celular = celular;
       this.direccion = direccion;
       this.facturas = facturas;
       this.usuarios = usuarios;
    }
   
    public Integer getIdVendedor() {
        return this.idVendedor;
    }
    
    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDui() {
        return this.dui;
    }
    
    public void setDui(String dui) {
        this.dui = dui;
    }
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Set<Factura> getFacturas() {
        return this.facturas;
    }
    
    public void setFacturas(Set<Factura> facturas) {
        this.facturas = facturas;
    }
    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }




}


