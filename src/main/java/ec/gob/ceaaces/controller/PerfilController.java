package ec.gob.ceaaces.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;

import ec.gob.ceaaces.data.Aplicacion;
import ec.gob.ceaaces.data.AplicacionRol;
import ec.gob.ceaaces.data.Perfil;
import ec.gob.ceaaces.data.PerfilRol;
import ec.gob.ceaaces.model.dtos.seguridades.AplicacionDTO;
import ec.gob.ceaaces.model.dtos.seguridades.AplicacionRolDTO;
import ec.gob.ceaaces.model.dtos.seguridades.PerfilDTO;
import ec.gob.ceaaces.model.dtos.seguridades.PerfilRolDTO;
import ec.gob.ceaaces.services.exceptions.ServicioException;
import ec.gob.ceaaces.services.impl.ServicioSeguridadModulosImpl;
import ec.gob.ceaaces.services.impl.ServicioSeguridadPerfilesImpl;
import ec.gob.ceaaces.services.impl.ServicioSeguridadUsuariosImpl;

public class PerfilController {

    private Long idAplicacion;
    private Long idPerfil;

    private String accionPerfil;

    private Aplicacion aplicacion;
    private AplicacionRol aplicacionRol;
    private PerfilRol perfilRol;
    private Perfil perfil;

    private List<Aplicacion> listaAplicacion = new ArrayList<>();
    private List<Perfil> listaPerfil = new ArrayList<>();

    private List<AplicacionDTO> listaAplicacionDTO = new ArrayList<>();
    private List<PerfilRol> listaPerfilRol = new ArrayList<>();
    private List<PerfilDTO> listaPerfiles = new ArrayList<PerfilDTO>();

    @Autowired
    private ServicioSeguridadModulosImpl servAppSeguridad;

    @Autowired
    private ServicioSeguridadPerfilesImpl servPerfilSeguridad;

    @Autowired
    private ServicioSeguridadUsuariosImpl servUsuarioSeguridad;

    public PerfilController() {
        aplicacion = new Aplicacion();
        aplicacionRol = new AplicacionRol();
        perfilRol = new PerfilRol();
        perfil = new Perfil();
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public List<Perfil> getListaPerfil() {
        return listaPerfil;
    }

    public void setListaPerfil(List<Perfil> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    public List<Aplicacion> getListaAplicacion() {
        return listaAplicacion;
    }

    public void setListaAplicacion(List<Aplicacion> listaAplicacion) {
        this.listaAplicacion = listaAplicacion;
    }

    public List<AplicacionDTO> getListaAplicacionDTO() {
        return listaAplicacionDTO;
    }

    public void setListaAplicacionDTO(List<AplicacionDTO> listaAplicacionDTO) {
        this.listaAplicacionDTO = listaAplicacionDTO;
    }

    public Long getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(Long idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getAccionPerfil() {
        return accionPerfil;
    }

    public void setAccionPerfil(String accionPerfil) {
        this.accionPerfil = accionPerfil;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public AplicacionRol getAplicacionRol() {
        return aplicacionRol;
    }

    public void setAplicacionRol(AplicacionRol aplicacionRol) {
        this.aplicacionRol = aplicacionRol;
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

    public List<PerfilRol> getListaPerfilRol() {
        return listaPerfilRol;
    }

    public void setListaPerfilRol(List<PerfilRol> listaPerfilRol) {
        this.listaPerfilRol = listaPerfilRol;
    }

    // ok
    @PostConstruct
    public void traerAplicaciones() {
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
    private void cargarListaAplicacion(List<AplicacionDTO> listaRetornoConsulta) {
        for (int i = 0; i < listaRetornoConsulta.size(); i++) {
            Aplicacion app = new Aplicacion();
            app.setId(listaRetornoConsulta.get(i).getId());
            app.setNombre(listaRetornoConsulta.get(i).getNombre());
            this.listaAplicacion.add(app);
        }
        this.listaAplicacion.add(new Aplicacion((long) 0, "Seleccionar..."));
        Collections.sort(this.listaAplicacion);
    }

    // ok
    public void cargarRoles() {
        try {
            aplicacion = new Aplicacion();
            this.aplicacion = appRolesSinDTO(servAppSeguridad
                    .obtenerAplicacionTotal(this.idAplicacion));
        } catch (ServicioException se) {
            msgError("No se pudo recuperar los roles!");
        }
    }

    // ok
    private Aplicacion appRolesSinDTO(AplicacionDTO appDTO) {
        aplicacion = new Aplicacion();

        this.aplicacion.setId(appDTO.getId());
        this.aplicacion.setNombre(appDTO.getNombre());

        for (AplicacionRolDTO appRolDTO : appDTO.getRoles()) {
            aplicacionRol = new AplicacionRol();
            this.aplicacionRol.setId(appRolDTO.getId());
            this.aplicacionRol.setNombre(appRolDTO.getNombre());
            this.aplicacionRol.setDescripcion(appRolDTO.getDescripcion());
            Aplicacion app = new Aplicacion();
            app.setId(appRolDTO.getAplicacion().getId());
            app.setNombre(appRolDTO.getAplicacion().getNombre());
            this.aplicacionRol.setAplicacion(app);
            this.aplicacion.getRoles().add(this.aplicacionRol);
        }
        return this.aplicacion;
    }

    // ok
    private void cargarRolesSeleccionados() {
        this.perfil.getRoles().clear();
        for (int i = 0; i < this.aplicacion.getRoles().size(); i++) {
            if (this.aplicacion.getRoles().get(i).getSeleccionado()) {
                PerfilRol pr = new PerfilRol();
                pr.setRol(this.aplicacion.getRoles().get(i));
                this.perfil.getRoles().add(pr);
            }
        }
    }

    // ok
    public void transformarListaDTO(List<PerfilDTO> listaPerfilDTO) {
        this.listaPerfil.clear();
        for (PerfilDTO ppDTO : listaPerfilDTO) {
            this.listaPerfil.add(perfilSinDTO(ppDTO));
        }
        Collections.sort(this.listaPerfil);
    }

    // ok
    private void cargarCheckboxes() {
        for (int i = 0; i < this.aplicacion.getRoles().size(); i++) {
            for (int j = 0; j < this.perfil.getRoles().size(); j++) {
                if (this.aplicacion.getRoles().get(i).getId()
                        .equals(this.perfil.getRoles().get(j).getRol().getId())) {
                    this.aplicacion.getRoles().get(i).setSeleccionado(true);
                }
            }
        }
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

    private PerfilDTO perfilConDTO(Perfil perfil) {
        PerfilDTO ppDTO = new PerfilDTO();

        if (perfil.getId() != null) {
            ppDTO.setId(perfil.getId());
        }
        ppDTO.setNombre(perfil.getNombre());

        AplicacionDTO apliDTO = new AplicacionDTO();
        apliDTO.setId(this.idAplicacion);

        ppDTO.setAplicacion(apliDTO);

        for (PerfilRol ppRol : perfil.getRoles()) {
            PerfilRolDTO ppRolDTO = new PerfilRolDTO();

            ppRolDTO.setId(ppRol.getId());

            AplicacionRolDTO appRolDTO = new AplicacionRolDTO();

            appRolDTO.setId(ppRol.getRol().getId());
            appRolDTO.setNombre(ppRol.getRol().getNombre());
            appRolDTO.setDescripcion(ppRol.getRol().getDescripcion());

            AplicacionDTO appDTO = new AplicacionDTO();

            appDTO.setId(ppRol.getRol().getAplicacion().getId());
            appDTO.setNombre(ppRol.getRol().getAplicacion().getNombre());

            appRolDTO.setAplicacion(appDTO);

            ppRolDTO.setRol(appRolDTO);

            ppDTO.getRoles().add(ppRolDTO);
        }

        return ppDTO;
    }

    // ok
    private void recuperarPerfilPorId() {
        try {
            this.perfil = perfilCompletoSinDTO(servPerfilSeguridad
                    .obtenerPerfil(this.idPerfil));
        } catch (ServicioException se) {
            msgError("No se pudo recuperar el perfil seleccionado!");
        }
    }

    // ok
    public void cargarPerfiles(ValueChangeEvent event) {
        this.idAplicacion = (Long) event.getNewValue();
        if (null != event.getNewValue() && this.idAplicacion > 0) {
            List<PerfilDTO> listaPerfilDTO = new ArrayList<>();
            try {
                listaPerfilDTO = servPerfilSeguridad
                        .obtenerPerfiles(this.idAplicacion);
                System.out.println("Lista: " + listaPerfilDTO);
                transformarListaDTO(listaPerfilDTO);
                cargarRoles();
            } catch (ServicioException se) {
                msgError("No se pudo recuperar los perfiles!");
            }
        } else {
            this.listaPerfil.clear();
        }
    }

    // ok
    private boolean existeNombrePerfil(Perfil perfil) {
        for (Perfil perf : this.listaPerfil) {
            if (perf.getNombre().equals(perfil.getNombre())) {
                return true;
            }
        }
        return false;
    }

    // ok
    public String guardarPerfil() {
        String salida = "";
        if (this.perfil.getNombre() == null
                || this.perfil.getNombre().length() < 4) {
            msgAdvertencia("El nombre del perfil debe cambiar. Ej: Perfil3");
        } else {
            try {
                if (this.perfil.getId() == null) {
                    if (existeNombrePerfil(this.perfil)) {
                        msgAdvertencia("Debe cambiar el nombre del perfil, ese ya existe!");
                    } else {
                        cargarRolesSeleccionados();
                        if (servPerfilSeguridad
                                .registrarPerfil(perfilConDTO(this.perfil))) {
                            msgInfo("El perfil se creo exitosamente!");
                            falsearRoles();
                        } else {
                            msgError("No se pudo crear el perfil!");
                        }
                    }
                } else {
                    cargarRolesSeleccionados();
                    if (servPerfilSeguridad
                            .registrarPerfil(perfilConDTO(this.perfil))) {
                        msgInfo("Los cambios se guardaron exitosamente!");
                        falsearRoles();
                        salida = "seguridadPerfil";
                    } else {
                        msgError("No se pudieron guardar los cambios del perfil!");
                    }
                }
            } catch (ServicioException se) {
                msgError("No se pudieron guardar los cambios del perfil!");
            }
        }
        perfil = new Perfil();
        return salida;
    }

    // ok
    private void falsearRoles() {
        for (int i = 0; i < this.aplicacion.getRoles().size(); i++) {
            this.aplicacion.getRoles().get(i).setSeleccionado(false);
        }
    }

    // ok
    public String irOpPerfil() {
        System.out.println("----METODO irOpPerfil----");
        perfil = new Perfil();
        recuperarPerfilPorId();
        cargarCheckboxes();
        return "opPerfil";
    }

    // ok
    public String irNuevoPerfil() {
        falsearRoles();
        perfil = new Perfil();
        this.perfil.getRoles().clear();
        return "opPerfil";
    }

    // ok
    public String regresar() {
        this.listaPerfil.clear();
        this.idAplicacion = (long) 0;
        return "seguridadPerfil";
    }

    private static void msgInfo(String msg) {
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,
                null);
        FacesContext.getCurrentInstance().addMessage(null, fmsg);
    }

    private static void msgAdvertencia(String msg) {
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg,
                null);
        FacesContext.getCurrentInstance().addMessage(null, fmsg);
    }

    private static void msgError(String msg) {
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,
                null);
        FacesContext.getCurrentInstance().addMessage(null, fmsg);
    }

    public List<PerfilDTO> getListaPerfiles() {
        System.out.println("-----LISTA PERFILES-----");
        List<PerfilDTO> listaPerfilDTO = new ArrayList<>();
        System.out.println("ID APLICACION: " + this.idAplicacion);
        try {
            listaPerfilDTO = servPerfilSeguridad.obtenerPerfiles((long) 5);
            System.out.println("Lista: " + listaPerfilDTO);
            // transformarListaDTO(listaPerfilDTO);
            // cargarRoles();
            listaPerfiles = listaPerfilDTO;
        } catch (ServicioException se) {
            msgError("No se pudo recuperar los perfiles!");
        }
        return listaPerfiles;
    }

    public void setListaPerfiles(List<PerfilDTO> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

}
