package ec.gob.ceaaces.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Perfil implements Serializable, Comparable<Perfil> {

    private static final long serialVersionUID = 6501176783783504380L;

    private Long id;
    private String nombre;
    private Boolean seleccionado;
    private Aplicacion aplicacion;

    private List<PerfilRol> roles = new ArrayList<>();

    public Perfil() {

    }

    public Perfil(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Boolean getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
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

    public List<PerfilRol> getRoles() {
        return roles;
    }

    public void setRoles(List<PerfilRol> roles) {
        this.roles = roles;
    }

    @Override
    public int compareTo(Perfil o) {
        return this.aplicacion.getId().compareTo(o.getAplicacion().getId());
    }

}