package ec.gob.ceaaces.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable, Comparable<Usuario> {

    private static final long serialVersionUID = 6155321576760295607L;

    private int numero;

    private String username;
    private String clave;
    private String identificacion;
    private String nombresCompletos;
    private List<UsuarioPerfil> perfiles = new ArrayList<>();

    public Usuario() {

    }

    public Usuario(String username, String clave, String identificacion,
            String nombresCompletos) {
        this.username = username;
        this.clave = clave;
        this.identificacion = identificacion;
        this.nombresCompletos = nombresCompletos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }

    public List<UsuarioPerfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<UsuarioPerfil> perfiles) {
        this.perfiles = perfiles;
    }

    @Override
    public int compareTo(Usuario o) {
        return this.username.compareTo(o.getUsername());
    }
}