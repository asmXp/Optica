/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.Bean;

//import com.sun.xml.ws.client.RequestContext;
import sys.imp.facturaDaoImp;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.RowEditEvent;
import sys.DAO.clienteDao;
import sys.DAO.detalleFacturaDao;
import sys.DAO.facturaDao;
import sys.DAO.productoDao;
import sys.imp.clienteDaoImp;
import sys.imp.detalleFacturaDaoImp;
import sys.imp.productoDaoImp;
import sys.model.Cliente;
import sys.model.Detallefactura;
import sys.model.Factura;
import sys.model.Producto;
import sys.model.Vendedor;
import sys.util.HibernateUtil;

/**
 *
 * @author dd
 */
@Named(value = "facturaBean")
@SessionScoped
public class facturaBean implements Serializable {

    Session session = null;
    Transaction transaction = null;

    private Cliente cliente;
    private Integer codigoCliente;

    private Producto producto;
    private String codigoBarra;

    private List<Detallefactura> listaDetalleFactura;

    private String cantidadProducto;
    private String productoSeleccionado;
    private Factura factura;

    private String cantidadProducto2;
    
    private Long numeroFactura;
    
    private BigDecimal totalVentaFactura;
    
    private Vendedor vendedor;

    public facturaBean() {
        this.factura = new Factura();
        listaDetalleFactura = new ArrayList();
        this.vendedor= new Vendedor();
        this.cliente= new Cliente();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public List<Detallefactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public void setListaDetalleFactura(List<Detallefactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
    }

    public String getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(String cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(String productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getCantidadProducto2() {
        return cantidadProducto2;
    }

    public void setCantidadProducto2(String cantidadProducto2) {
        this.cantidadProducto2 = cantidadProducto2;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getTotalVentaFactura() {
        return totalVentaFactura;
    }

    public void setTotalVentaFactura(BigDecimal totalVentaFactura) {
        this.totalVentaFactura = totalVentaFactura;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    
    

    //Metodo para agregar los datos de los clientes por medio del dialogClientes
    public void agregarDatosCliente(Integer idCliente) {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();

            //Obtener los datos del cliente e la variable objeto cliente segun el id del cliente
            this.cliente = cDao.obtenerClientePorCodigo(this.session, idCliente);
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del cliente agregado"));

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }

    }

    //Metodo para agregar los datos de los clientes BUSCADO POR IDCLIENTE
    public void agregarDatosCliente2() {
        this.session = null;
        this.transaction = null;

        try {

            if (this.codigoCliente == null) {
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();

            //Obtener los datos del cliente e la variable objeto cliente segun el id del cliente
            this.cliente = cDao.obtenerClientePorCodigo(this.session, this.codigoCliente);

            if (this.cliente != null) {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del cliente agregado"));
            } else {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos del cliente no encontrados"));
            }

            this.transaction.commit();

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }

    }

    //Metodo para solicitar la cantidad de producto
    public void pedirCantidadProducto(String codBarra) {
        this.productoSeleccionado = codBarra;
    }

    //Metodo para agregar los datos de los Producto por medio del dialogProducto
    public void agregarDatosProducto() {

        this.session = null;
        this.transaction = null;

        try {

            if (!(this.cantidadProducto.matches("[0-9]*")) || this.cantidadProducto.equals("0") || this.cantidadProducto.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correcto", "Error, la cantidad es incorrecta"));

                this.cantidadProducto = "";

            } else {
                this.session = HibernateUtil.getSessionFactory().openSession();
                productoDao pDao = new productoDaoImp();
                this.transaction = this.session.beginTransaction();

                //Obtener los datos del producto e la variable objeto producto segun el codBarra
                this.producto = pDao.obtenerProductoPorCodBarra(this.session, this.productoSeleccionado);

                this.listaDetalleFactura.add(new Detallefactura(null, null, this.producto.getCodBarra(),
                        this.producto.getNombreProducto(), Integer.parseInt(this.cantidadProducto), this.producto.getPrecioVenta(),
                        BigDecimal.valueOf(Integer.parseInt(this.cantidadProducto) * this.producto.getPrecioVenta().floatValue())));

                this.transaction.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado ala detalle"));

                this.cantidadProducto = "";
                //Llamada al metodo calcular facturaVenta
                this.calcularTotalFacturaVenta();
            }

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }

    }

    //Metodo mostrar el dialogCantidadProducto2
    public void mostrarCantidadProducto2() {
        this.session = null;
        this.transaction = null;

        try {

            if (this.codigoBarra.equals("")) {
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pDao = new productoDaoImp();

            this.transaction = this.session.beginTransaction();

            //Obtener los datos del producto e la variable objeto producto segun el cod de barra
            this.producto = pDao.obtenerProductoPorCodBarra(this.session, codigoBarra);

            if (this.producto != null) {

                //Solicitar mostrar el dialog cantidadProducto2
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dialogCantidadProducto2').show();");

                this.codigoBarra = null;

            } else {
                this.codigoBarra = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Producto no encontrados"));

            }

            this.transaction.commit();

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }

    }
    //Metodo para agregar los datos de los productod BUSCADO POR IDbarra

    public void agregarDatosProducto2() {

        if (!(this.cantidadProducto2.matches("[0-9]*")) || this.cantidadProducto2.equals("0") || this.cantidadProducto2.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correcto", "Error, la cantidad es incorrecta"));

            this.cantidadProducto2 = "";

        } else {

            this.listaDetalleFactura.add(new Detallefactura(null, null, this.producto.getCodBarra(),
                    this.producto.getNombreProducto(), Integer.parseInt(this.cantidadProducto2), this.producto.getPrecioVenta(),
                    BigDecimal.valueOf(Integer.parseInt(this.cantidadProducto2) * this.producto.getPrecioVenta().floatValue())));
            this.cantidadProducto2 = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado al detalle"));

            //llamar al metodo calcular totalFactura
            this.calcularTotalFacturaVenta();

        }
    }

    //Metdo para calcular el total a vender en la factura
    public void calcularTotalFacturaVenta() {
        this.totalVentaFactura = new BigDecimal("0");
        try {
            for (Detallefactura item : listaDetalleFactura) {
                BigDecimal totalVentaPorProducto = item.getPrecioVenta().multiply(new BigDecimal(item.getCantidad()));
                item.setTotal(totalVentaPorProducto);
                totalVentaFactura = totalVentaFactura.add(totalVentaPorProducto);
            }
            this.factura.setTotalVenta(totalVentaFactura);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
        }
    }
    
    
    //Metodo para quitar un producto de la factura
    public void quitarProductoDetalleFactura(String codBarra, Integer filaSelecionada){
        try {
            int i=0;
            
            for (Detallefactura item : this.listaDetalleFactura) {
                if(item.getCodBarra().equals(codBarra)&& filaSelecionada.equals(i)){
                    this.listaDetalleFactura.remove(i);
                    break;
                }
                i++;               
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", "Se retiro el producto de la factura"));
            
            //invocamos al metodo calcular factura paraactualizar el total a vender
            this.calcularTotalFacturaVenta();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
        }
    }
    
    //Metodos para editar la cantidad de producto en la tabla factura
    
    public void onRowEdit(RowEditEvent event) {
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se modifico la cantidad"));
       //Invocar al metododo calcularTotalFactura para actilizar al vender
       this.calcularTotalFacturaVenta();
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "No se hiso ningun cambio"));
    }
    
    //Metodo para general el numero de factura
    public void numeracionFactura(){
        this.session=null;
        this.transaction=null;
        
        try {
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction= this.session.beginTransaction();
            
            facturaDao fDao= new facturaDaoImp();
            //Verificar si hay registros en la tabla factura de la base de datos
            this.numeroFactura= fDao.obtenerTotalRegistrosEnFactura(this.session);
            
            if (this.numeroFactura<=0 ||this.numeroFactura==null) {
                this.numeroFactura=Long.valueOf("1");
            }else{
                //recuperramos el ultimo registro que exista en la tabal factura de la bd
                this.factura= fDao.obtenerUltimoRegistro(this.session);
                this.numeroFactura=Long.valueOf(this.factura.getNumeroFactura()+1);
                
                //Limpiar la variable totalVentaFactura
                this.totalVentaFactura= new BigDecimal("0");
                
            }
            this.transaction.commit();
        } catch (Exception e) {
            if (this.transaction!=null) {
                this.transaction.rollback();
            }
            System.out.println(e.getMessage());
        }finally{
            if (this.session!=null) {
                this.session.close();
            }
        }
        
    }
    
    
    //Metodo para Limpiar la factura
    public void limpiarFactura(){
        this.cliente = new Cliente();
        this.factura= new Factura();
        this.listaDetalleFactura= new ArrayList<>();
        this.numeroFactura=null;
        this.totalVentaFactura=null;
        
        //invocar al metodo para desactivar botones
        this.disableButton();
        
    }
    
    //Metodo para guardar venta
    public void guardarVenta(){
        this.session=null;
        this.transaction=null;
        this.vendedor.setIdVendedor(2);
        try {
            this.session=HibernateUtil.getSessionFactory().openSession();
            productoDao pDao= new productoDaoImp();
            facturaDao fDao= new facturaDaoImp();
            detalleFacturaDao dFDao= new detalleFacturaDaoImp();
            
            this.transaction= this.session.beginTransaction();
            
            //datos para guardar en la tabla datosFactura de la db
            this.factura.setNumeroFactura(this.numeroFactura.intValue());
            this.factura.setCliente(this.cliente);
            this.factura.setVendedor(this.vendedor);
            
            //Hacemos el insert en la tabla factura de la db
            
            fDao.guardarVentaFactura(this.session, this.factura);
            
            //recuperar el ultimo registro de la tabla factura
            this.factura=fDao.obtenerUltimoRegistro(this.session);
            
            //recorremos el arraylist para guardar cada registro en la base de datos
            for (Detallefactura item : listaDetalleFactura) {
                this.producto= pDao.obtenerProductoPorCodBarra(this.session, item.getCodBarra());
                item.setFactura(this.factura);
                item.setProducto(this.producto);
                
                //hacemos el insert en la tabla detallefactura e la bd
                dFDao.guardarVentaDetalleFactura(this.session, item);
                
            }
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Venta registrada"));
            
            this.limpiarFactura();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (this.transaction!=null) {
                this.transaction.rollback();
            }
        }finally{
            if (this.session!=null) {
                this.session.close();
            }
        }
    }
    // metodos para activar o desactrivar los controles en la factura
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    
    
    public void enableButton(){
        enabled=true;
    }
    public void disableButton(){
        enabled=false;
        System.out.println("Estoy entrando al disbleButton: "+ enabled);
    }

}
