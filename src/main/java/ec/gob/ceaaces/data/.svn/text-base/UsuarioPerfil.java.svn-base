package ec.gob.ceaaces.data;

import java.io.Serializable;

public class UsuarioPerfil implements Serializable, Comparable<UsuarioPerfil> {

    private static final long serialVersionUID = -6524917274736411265L;

    private Long id;
    private Usuario usuario;
    private Perfil perfil;

    public UsuarioPerfil() {

    }

    public UsuarioPerfil(Long id, Perfil perfil) {
        this.id = id;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int compareTo(UsuarioPerfil o) {
        return this.id.compareTo(o.getId());
    }

}