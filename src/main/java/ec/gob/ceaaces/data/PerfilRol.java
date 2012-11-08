package ec.gob.ceaaces.data;

import java.io.Serializable;

public class PerfilRol implements Serializable, Comparable<PerfilRol> {

    private static final long serialVersionUID = 2929423536357777698L;

    private Long id;
    private Perfil perfil;
    private AplicacionRol rol;

    public PerfilRol() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public AplicacionRol getRol() {
        return rol;
    }

    public void setRol(AplicacionRol rol) {
        this.rol = rol;
    }

    @Override
    public int compareTo(PerfilRol o) {
        return this.id.compareTo(o.getId());
    }

}