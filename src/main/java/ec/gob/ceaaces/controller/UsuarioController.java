package ec.gob.ceaaces.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import ec.gob.ceaaces.data.Aplicacion;
import ec.gob.ceaaces.data.AplicacionRol;
import ec.gob.ceaaces.data.Perfil;
import ec.gob.ceaaces.data.PerfilRol;
import ec.gob.ceaaces.data.Usuario;
import ec.gob.ceaaces.data.UsuarioPerfil;
import ec.gob.ceaaces.model.dtos.seguridades.AplicacionDTO;
import ec.gob.ceaaces.model.dtos.seguridades.PerfilDTO;
import ec.gob.ceaaces.model.dtos.seguridades.PerfilRolDTO;
import ec.gob.ceaaces.model.dtos.seguridades.UsuarioDTO;
import ec.gob.ceaaces.model.dtos.seguridades.UsuarioPerfilDTO;
import ec.gob.ceaaces.services.exceptions.ServicioException;
import ec.gob.ceaaces.services.impl.ServicioSeguridadModulosImpl;
import ec.gob.ceaaces.services.impl.ServicioSeguridadPerfilesImpl;
import ec.gob.ceaaces.services.impl.ServicioSeguridadUsuariosImpl;

public class UsuarioController {

    private Long indicador;
    private Boolean verUsername;
    private String accionUsuario;

    private Long idPerfil;
    private Long idAplicacion;
    private String idUsuario;

    private Aplicacion aplicacion;
    private PerfilRol perfilRol;
    private Perfil perfil;
    private Usuario usuario;
    private UsuarioPerfil usuarioPerfil;

    private List<Usuario> listaUsuario = new ArrayList<>();
    private List<Perfil> listaPerfil = new ArrayList<>();
    private List<UsuarioPerfil> perfilesDeUsuario = new ArrayList<>();
    private List<UsuarioPerfil> listaUsuarioPerfil = new ArrayList<>();
    private List<Aplicacion> listaAplicacion = new ArrayList<>();

    private List<AplicacionDTO> listaAplicacionDTO = new ArrayList<>();

    @Autowired
    private ServicioSeguridadModulosImpl servAppSeguridad;

    @Autowired
    private ServicioSeguridadPerfilesImpl servPerfilSeguridad;

    @Autowired
    private ServicioSeguridadUsuariosImpl servUsuarioSeguridad;

    public UsuarioController() {
        aplicacion = new Aplicacion();
        perfilRol = new PerfilRol();
        perfil = new Perfil();
        usuario = new Usuario();
        usuarioPerfil = new UsuarioPerfil();
    }

    public Long getIndicador() {
        return indicador;
    }

    public void setIndicador(Long indicador) {
        this.indicador = indicador;
    }

    public List<UsuarioPerfil> getPerfilesDeUsuario() {
        return perfilesDeUsuario;
    }

    public void setPerfilesDeUsuario(List<UsuarioPerfil> perfilesDeUsuario) {
        this.perfilesDeUsuario = perfilesDeUsuario;
    }

    public Boolean getVerUsername() {
        return verUsername;
    }

    public void setVerUsername(Boolean verUsername) {
        this.verUsername = verUsername;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public List<Perfil> getListaPerfil() {
        return listaPerfil;
    }

    public void setListaPerfil(List<Perfil> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public List<Aplicacion> getListaAplicacion() {
        return listaAplicacion;
    }

    public void setListaAplicacion(List<Aplicacion> listaAplicacion) {
        this.listaAplicacion = listaAplicacion;
    }

    public String getAccionUsuario() {
        return accionUsuario;
    }

    public void setAccionUsuario(String accionUsuario) {
        this.accionUsuario = accionUsuario;
    }

    public Long getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(Long idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public PerfilRol getPerfilRol() {
        return perfilRol;
    }

    public void setPerfilRol(PerfilRol perfilRol) {
        this.perfilRol = perfilRol;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioPerfil getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
    }

    public List<UsuarioPerfil> getListaUsuarioPerfil() {
        return listaUsuarioPerfil;
    }

    public void setListaUsuarioPerfil(List<UsuarioPerfil> listaUsuarioPerfil) {
        this.listaUsuarioPerfil = listaUsuarioPerfil;
    }

    public List<AplicacionDTO> getListaAplicacionDTO() {
        return listaAplicacionDTO;
    }

    public void setListaAplicacionDTO(List<AplicacionDTO> listaAplicacionDTO) {
        this.listaAplicacionDTO = listaAplicacionDTO;
    }

    // ok
    @PostConstruct
    public void cargarUsuarios() {
        List<UsuarioDTO> listaUsuarioDTO = new ArrayList<>();
        try {
            listaUsuarioDTO = servUsuarioSeguridad.obtenerListaUsuarios();
            transformarListaUsuarioDTO(listaUsuarioDTO);
        } catch (ServicioException se) {
            msgError("No se pudo recuperar usuarios!");
        }
    }

    // ok
    private void transformarListaUsuarioDTO(List<UsuarioDTO> listaUsuarioDTO) {
        this.listaUsuario.clear();
        for (UsuarioDTO usuDTO : listaUsuarioDTO) {
            this.listaUsuario.add(usuarioSimpleSinDTO(usuDTO));
        }
        cargarNumeroUsuario();
    }

    // ok
    private void cargarListaAplicacion(List<AplicacionDTO> listaRetornoConsulta) {
        for (int i = 0; i < listaRetornoConsulta.size(); i++) {
            Aplicacion app = new Aplicacion();
            app.setId(listaRetornoConsulta.get(i).getId());
            app.setNombre(listaRetornoConsulta.get(i).getNombre());
            this.listaAplicacion.add(app);
        }
        Collections.sort(this.listaAplicacion);
    }

    // ok
    private void traerAplicaciones() {
        this.listaAplicacionDTO.clear();
        this.listaAplicacion.clear();
        try {
            listaAplicacionDTO = servAppSeguridad.obtenerListaAplicaciones();
            cargarListaAplicacion(listaAplicacionDTO);
        } catch (ServicioException se) {
            msgError("No se pudo recuperar las aplicaciones");
        }
        this.idAplicacion = (long) 0;
    }

    // ok
    private void cargarTodosLosPerfiles() {
        this.listaPerfil.clear();
        List<Perfil> perfiles = new ArrayList<>();
        for (int i = 0; i < this.listaAplicacion.size(); i++) {
            try {
                perfiles = transformarListaPerfilDTO(servPerfilSeguridad
                        .obtenerPerfiles(this.listaAplicacion.get(i).getId()));
                cargarPerfilesPorAplicacion(perfiles);
            } catch (ServicioException se) {
                msgError("Los perfiles no se pudieron recuperar!");
            }
        }
    }

    // ok
    private void cargarPerfilesPorAplicacion(List<Perfil> perfiles) {
        for (int j = 0; j < perfiles.size(); j++) {
            this.listaPerfil.add(perfiles.get(j));
        }
        cargarCheckboxes();
    }

    // ok
    private Usuario usuarioSimpleSinDTO(UsuarioDTO usuDTO) {
        Usuario user = new Usuario();
        user.setUsername(usuDTO.getUsuario());
        return user;
    }

    // ok
    private Usuario usuarioCompletoSinDTO(UsuarioDTO usuDTO) {
        Usuario user = new Usuario();

        user.setUsername(usuDTO.getUsuario());
        user.setNombresCompletos(usuDTO.getNombresCompletos());
        user.setIdentificacion(usuDTO.getIdentificacion());

        for (UsuarioPerfilDTO upDTO : usuDTO.getPerfiles()) {

            UsuarioPerfil up = new UsuarioPerfil();
            up.setId(upDTO.getId());

            Perfil perf = new Perfil();
            perf.setId(upDTO.getPerfil().getId());

            Aplicacion app = new Aplicacion();
            app.setId(upDTO.getPerfil().getAplicacion().getId());

            perf.setAplicacion(app);
            up.setPerfil(perf);

            user.getPerfiles().add(up);
        }
        return user;
    }

    // ok
    private UsuarioDTO usuarioCompletoConDTO(Usuario usu) {
        UsuarioDTO usuDTO = new UsuarioDTO();

        usuDTO.setUsuario(usu.getUsername());
        usuDTO.setIdentificacion(usu.getIdentificacion());
        usuDTO.setNombresCompletos(usu.getNombresCompletos());

        for (UsuarioPerfil up : usu.getPerfiles()) {
            UsuarioPerfilDTO upDTO = new UsuarioPerfilDTO();
            upDTO.setId(up.getId());

            PerfilDTO perfDTO = new PerfilDTO();
            perfDTO.setId(up.getPerfil().getId());

            AplicacionDTO appDTO = new AplicacionDTO();
            appDTO.setId(up.getPerfil().getAplicacion().getId());

            perfDTO.setAplicacion(appDTO);
            upDTO.setPerfil(perfDTO);

            usuDTO.getPerfiles().add(upDTO);
        }
        return usuDTO;
    }

    // ok
    private List<Perfil> transformarListaPerfilDTO(
            List<PerfilDTO> listaPerfilDTO) {
        List<Perfil> perfiles = new ArrayList<>();
        for (PerfilDTO ppDTO : listaPerfilDTO) {
            perfiles.add(perfilSinDTO(ppDTO));
        }
        return perfiles;
    }

    // ok
    public void eliminarPerfil() {
        if (!rolesReferenciados()) {
            try {
                if (servPerfilSeguridad.eliminarPerfil(this.idPerfil)) {
                    msgInfo("El perfil fue eliminado correctamente!");
                    this.idAplicacion = (long) 0;
                } else {
                    msgError("El perfil no pudo ser eliminado!");
                }
            } catch (ServicioException se) {
                msgError("El perfil no pudo ser eliminado!");
            }
        } else {
            msgError("El perfil no pudo ser eliminado, ya que esta referenciado en otro sitio!");
        }
    }

    // ok
    private boolean rolesReferenciados() {
        for (int i = 0; i < this.listaUsuario.size(); i++) {
            Usuario user = new Usuario();
            try {
                user = usuarioCompletoSinDTO(servUsuarioSeguridad
                        .obtenerUsuario(this.listaUsuario.get(i).getUsername()));
                if (buscarPerfilesReferenciados(user, this.idPerfil)) {
                    return true;
                }
            } catch (ServicioException se) {
                msgError("...");
            }
        }
        return false;
    }

    // ok
    private boolean buscarPerfilesReferenciados(Usuario user, Long idPerf) {
        Perfil perf = new Perfil();
        try {
            perf = perfilCompletoSinDTO(servPerfilSeguridad
                    .obtenerPerfil(idPerf));
            for (int i = 0; i < user.getPerfiles().size(); i++) {
                if (idPerf
                        .equals(user.getPerfiles().get(i).getPerfil().getId())
                        || perf.getRoles().size() > 0) {
                    return true;
                }
            }
        } catch (ServicioException se) {
            msgError("...");
        }
        return false;
    }

    // ok
    private Perfil perfilCompletoSinDTO(PerfilDTO perfilDTO) {
        Perfil perf = new Perfil();

        perf.setId(perfilDTO.getId());
        perf.setNombre(perfilDTO.getNombre());

        Aplicacion apli = new Aplicacion();
        apli.setId(perfilDTO.getAplicacion().getId());

        perf.setAplicacion(apli);

        for (PerfilRolDTO perfilRolDTO : perfilDTO.getRoles()) {
            PerfilRol perfilRol = new PerfilRol();

            perfilRol.setId(perfilRolDTO.getId());

            AplicacionRol apliRol = new AplicacionRol();

            apliRol.setId(perfilRolDTO.getRol().getId());
            apliRol.setNombre(perfilRolDTO.getRol().getNombre());
            apliRol.setDescripcion(perfilRolDTO.getRol().getDescripcion());

            perfilRol.setRol(apliRol);

            perf.getRoles().add(perfilRol);
        }

        return perf;
    }

    // ok
    private Perfil perfilSinDTO(PerfilDTO perfilDTO) {
        Perfil pp = new Perfil();
        pp.setId(perfilDTO.getId());
        pp.setNombre(perfilDTO.getNombre());

        Aplicacion app = new Aplicacion();
        app.setId(perfilDTO.getAplicacion().getId());
        app.setNombre(perfilDTO.getAplicacion().getNombre());

        pp.setAplicacion(app);
        return pp;
    }

    // ok
    private void cargarCheckboxes() {
        for (int i = 0; i < this.listaPerfil.size(); i++) {
            for (int j = 0; j < this.usuario.getPerfiles().size(); j++) {
                if (this.usuario.getPerfiles().get(j).getPerfil().getId()
                        .equals(this.listaPerfil.get(i).getId())) {
                    this.listaPerfil.get(i).setSeleccionado(true);
                }
            }
        }
    }

    // ok
    private void cargarPerfilesSeleccionados() {
        this.listaUsuarioPerfil.clear();
        for (int i = 0; i < this.listaPerfil.size(); i++) {
            if (this.listaPerfil.get(i).getSeleccionado()) {
                UsuarioPerfil up = new UsuarioPerfil();
                up.setPerfil(this.listaPerfil.get(i));
                this.listaUsuarioPerfil.add(up);
            }
        }
    }

    // ok
    private void falsearPerfiles() {
        for (int i = 0; i < this.listaPerfil.size(); i++) {
            this.listaPerfil.get(i).setSeleccionado(false);
        }
    }

    // ?
    private boolean existeUsuario(Usuario usu) {
        for (Usuario u : this.listaUsuario) {
            if (u.getUsername().equals(usu.getUsername())) {
                return true;
            }
        }
        return false;
    }

    // ok
    public String guardarUsuario() {
        String salida = "";
        if (this.usuario.getUsername() == null
                || this.usuario.getUsername().equals("")
                || this.usuario.getIdentificacion() == null
                || this.usuario.getIdentificacion().equals("")
                || this.usuario.getNombresCompletos() == null
                || this.usuario.getNombresCompletos().equals("")) {
            msgAdvertencia("Faltan datos que completar!");
        } else {
            if (this.indicador == 1) {
                if (existeUsuario(this.usuario)) {
                    msgAdvertencia("Cambie el nombre de usuario, ese ya existe!");
                } else {
                    try {
                        cargarPerfilesSeleccionados();
                        this.usuario.setPerfiles(this.listaUsuarioPerfil);
                        if (servUsuarioSeguridad
                                .registrarUsuario(usuarioCompletoConDTO(this.usuario))) {
                            salida = "seguridadUsuario";
                        } else {
                            msgError("El usuario no pudo ser creado!");
                        }
                    } catch (ServicioException se) {
                        msgError("El usuario no pudo ser creado!");
                    }
                }
            } else {
                try {
                    cargarPerfilesSeleccionados();
                    this.usuario.setPerfiles(this.listaUsuarioPerfil);
                    if (servUsuarioSeguridad
                            .registrarUsuario(usuarioCompletoConDTO(this.usuario))) {
                        salida = "seguridadUsuario";
                    } else {
                        msgError("El usuario no pudo ser creado!");
                    }
                } catch (ServicioException se) {
                    msgError("El usuario no pudo ser creado!");
                }
            }
        }
        cargarUsuarios();
        return salida;
    }

    // ok
    private void cargarNumeroUsuario() {
        Collections.sort(this.listaUsuario);
        for (int i = 0; i < this.listaUsuario.size(); i++) {
            this.listaUsuario.get(i).setNumero(i + 1);
        }
    }

    // ok
    private void recuperarUsuario() {
        usuario = new Usuario();
        try {
            this.usuario = usuarioCompletoSinDTO(servUsuarioSeguridad
                    .obtenerUsuario(this.idUsuario));
        } catch (ServicioException se) {
            msgError("No se pudo recuperar al usuario seleccionado!");
        }
    }

    // ok
    public String regresar() {
        cargarUsuarios();
        return "seguridadUsuario";
    }

    // ok
    public String irNuevoUsuario() {
        this.listaUsuarioPerfil.clear();
        traerAplicaciones();
        cargarTodosLosPerfiles();
        falsearPerfiles();
        usuario = new Usuario();
        return "opUsuario";
    }

    // ok
    public String editar() {
        this.listaUsuarioPerfil.clear();
        falsearPerfiles();
        recuperarUsuario();
        traerAplicaciones();
        cargarTodosLosPerfiles();
        return "opUsuario";
    }

    // ok
    public void eliminar() {
        try {
            if (servUsuarioSeguridad.eliminarUsuario(this.idUsuario)) {
                msgInfo("El usuario fue eliminado exitosamente!");
            } else {
                msgError("No se pudo elimar el usuario seleccionado!");
            }
        } catch (ServicioException se) {
            msgError("No se pudo elimar el usuario seleccionado!");
        }
        cargarUsuarios();
    }

    private static void msgAdvertencia(String msg) {
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg,
                null);
        FacesContext.getCurrentInstance().addMessage(null, fmsg);
    }

    private static void msgInfo(String msg) {
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,
                null);
        FacesContext.getCurrentInstance().addMessage(null, fmsg);
    }

    private static void msgError(String msg) {
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,
                null);
        FacesContext.getCurrentInstance().addMessage(null, fmsg);
    }
}
