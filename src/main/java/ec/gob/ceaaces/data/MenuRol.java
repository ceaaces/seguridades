package ec.gob.ceaaces.data;

import java.io.Serializable;

public class MenuRol implements Serializable {

    private static final long serialVersionUID = -5378173449878265035L;

    private Long id;
    private Long idTemp;

    private Boolean lectura = false;
    private Boolean escritura = false;

    private AplicacionMenu modulo;
    private AplicacionRol rol;

    public MenuRol() {

    }

    public MenuRol(Long id, AplicacionRol rol) {
        this.id = id;
        this.rol = rol;
    }

    public MenuRol(Long id, AplicacionMenu modulo, AplicacionRol rol) {
        this.id = id;
        this.modulo = modulo;
        this.rol = rol;
    }

    public Boolean getLectura() {
        return lectura;
    }

    public void setLectura(Boolean lectura) {
        this.lectura = lectura;
    }

    public Boolean getEscritura() {
        return escritura;
    }

    public void setEscritura(Boolean escritura) {
        this.escritura = escritura;
    }

    public Long getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(Long idTemp) {
        this.idTemp = idTemp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AplicacionMenu getModulo() {
        return modulo;
    }

    public void setModulo(AplicacionMenu modulo) {
        this.modulo = modulo;
    }

    public AplicacionRol getRol() {
        return rol;
    }

    public void setRol(AplicacionRol rol) {
        this.rol = rol;
    }

}