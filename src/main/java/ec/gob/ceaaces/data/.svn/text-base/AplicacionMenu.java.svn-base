package ec.gob.ceaaces.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AplicacionMenu implements Serializable, Comparable<AplicacionMenu> {

    private static final long serialVersionUID = -1121572673920532730L;

    private Long id;

    private String nombre;
    private String descripcion;
    private String destino = "#";
    private String nivel;

    private Aplicacion aplicacion;
    private AplicacionMenu padre;

    private List<AplicacionMenu> hijos = new ArrayList<AplicacionMenu>();
    private List<MenuRol> menuRoles = new ArrayList<>();

    public AplicacionMenu() {

    }

    public AplicacionMenu(Long id, String nombre, String descripcion,
            List<MenuRol> menuRoles) {
        this.id = id;
        this.nombre = nombre.toUpperCase();
        this.descripcion = descripcion;
        this.menuRoles = menuRoles;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public AplicacionMenu getPadre() {
        return padre;
    }

    public void setPadre(AplicacionMenu padre) {
        this.padre = padre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<AplicacionMenu> getHijos() {
        return hijos;
    }

    public void setHijos(List<AplicacionMenu> hijos) {
        this.hijos = hijos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public List<MenuRol> getMenuRoles() {
        return menuRoles;
    }

    public void setMenuRoles(List<MenuRol> menuRoles) {
        this.menuRoles = menuRoles;
    }

    @Override
    public int compareTo(AplicacionMenu o) {
        return this.nombre.compareTo(o.getNombre());
    }

}