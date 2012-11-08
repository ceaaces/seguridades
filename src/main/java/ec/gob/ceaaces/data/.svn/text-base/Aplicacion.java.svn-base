package ec.gob.ceaaces.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aplicacion implements Serializable, Comparable<Aplicacion> {

    private static final long serialVersionUID = 462158219444097304L;

    private Long id;
    private String nombre;
    private List<AplicacionRol> roles;
    private List<AplicacionMenu> modulos;

    public Aplicacion() {
        roles = new ArrayList<>();
        modulos = new ArrayList<>();
    }

    public Aplicacion(Long id, String nombre) {
        roles = new ArrayList<>();
        modulos = new ArrayList<>();
        this.id = id;
        this.nombre = nombre.toUpperCase();
    }

    public Aplicacion(Long id, String nombre, List<AplicacionRol> roles,
            List<AplicacionMenu> modulos) {
        roles = new ArrayList<>();
        modulos = new ArrayList<>();
        this.id = id;
        this.nombre = nombre.toUpperCase();
        this.roles = roles;
        this.modulos = modulos;
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

    public List<AplicacionRol> getRoles() {
        return roles;
    }

    public void setRoles(List<AplicacionRol> roles) {
        this.roles = roles;
    }

    public List<AplicacionMenu> getModulos() {
        return modulos;
    }

    public void setModulos(List<AplicacionMenu> modulos) {
        this.modulos = modulos;
    }

    @Override
    public int compareTo(Aplicacion o) {
        return this.nombre.compareTo(o.getNombre());
    }

}