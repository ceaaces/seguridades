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
import ec.gob.ceaaces.data.AplicacionMenu;
import ec.gob.ceaaces.data.AplicacionRol;
import ec.gob.ceaaces.data.MenuRol;
import ec.gob.ceaaces.model.dtos.seguridades.AplicacionDTO;
import ec.gob.ceaaces.model.dtos.seguridades.AplicacionMenuDTO;
import ec.gob.ceaaces.model.dtos.seguridades.AplicacionRolDTO;
import ec.gob.ceaaces.model.dtos.seguridades.MenuRolDTO;
import ec.gob.ceaaces.services.exceptions.ServicioException;
import ec.gob.ceaaces.services.impl.ServicioSeguridadModulosImpl;

public class AplicacionRolController {

    private boolean verComboAplicacion;
    private boolean verLinkDesmarcarRol;

    private String accionModulo;
    private String accionApp;

    private Long idAplicacion;

    private Aplicacion aplicacion;
    private AplicacionRol aplicacionRol;
    private AplicacionMenu aplicacionModulo;
    private MenuRol moduloRol;

    private List<Aplicacion> listaAplicacion = new ArrayList<>();
    private List<AplicacionDTO> listaAplicacionDTO = new ArrayList<>();
    private List<AplicacionRol> listaAplicacionRol = new ArrayList<>();
    private List<AplicacionMenu> listaAplicacionModulo = new ArrayList<>();

    @Autowired
    private ServicioSeguridadModulosImpl servPerSeguridad;

    public AplicacionRolController() {
        aplicacion = new Aplicacion();
        aplicacionModulo = new AplicacionMenu();
        aplicacionRol = new AplicacionRol();
        moduloRol = new MenuRol();
    }

    public boolean isVerLinkDesmarcarRol() {
        return verLinkDesmarcarRol;
    }

    public void setVerLinkDesmarcarRol(boolean verLinkDesmarcarRol) {
        this.verLinkDesmarcarRol = verLinkDesmarcarRol;
    }

    public boolean isVerComboAplicacion() {
        return verComboAplicacion;
    }

    public void setVerComboAplicacion(boolean verComboAplicacion) {
        this.verComboAplicacion = verComboAplicacion;
    }

    public List<AplicacionDTO> getListaAplicacionDTO() {
        return listaAplicacionDTO;
    }

    public void setListaAplicacionDTO(List<AplicacionDTO> listaAplicacionDTO) {
        this.listaAplicacionDTO = listaAplicacionDTO;
    }

    public String getAccionApp() {
        return accionApp;
    }

    public void setAccionApp(String accionApp) {
        this.accionApp = accionApp;
    }

    public List<Aplicacion> getListaAplicacion() {
        return listaAplicacion;
    }

    public void setListaAplicacion(List<Aplicacion> listaAplicacion) {
        this.listaAplicacion = listaAplicacion;
    }

    public Long getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(Long idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    public String getAccionModulo() {
        return accionModulo;
    }

    public void setAccionModulo(String accionModulo) {
        this.accionModulo = accionModulo;
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

    public AplicacionMenu getAplicacionModulo() {
        return aplicacionModulo;
    }

    public void setAplicacionModulo(AplicacionMenu aplicacionModulo) {
        this.aplicacionModulo = aplicacionModulo;
    }

    public MenuRol getModuloRol() {
        return moduloRol;
    }

    public void setModuloRol(MenuRol moduloRol) {
        this.moduloRol = moduloRol;
    }

    public List<AplicacionRol> getListaAplicacionRol() {
        return listaAplicacionRol;
    }

    public void setListaAplicacionRol(List<AplicacionRol> listaAplicacionRol) {
        this.listaAplicacionRol = listaAplicacionRol;
    }

    public List<AplicacionMenu> getListaAplicacionModulo() {
        return listaAplicacionModulo;
    }

    public void setListaAplicacionModulo(
            List<AplicacionMenu> listaAplicacionModulo) {
        this.listaAplicacionModulo = listaAplicacionModulo;
    }

    @PostConstruct
    public void traerAplicaciones() {
        this.listaAplicacionDTO.clear();
        this.listaAplicacion.clear();
        try {
            listaAplicacionDTO = servPerSeguridad.obtenerListaAplicaciones();
            cargarListaAplicacion(listaAplicacionDTO);
        } catch (ServicioException se) {
            msgError("No se pudo recuperar las aplicaciones");
        }
    }

    public void cargarListaAplicacion(List<AplicacionDTO> listaRetornoConsulta) {
        for (int i = 0; i < listaRetornoConsulta.size(); i++) {
            aplicacion = new Aplicacion();
            aplicacion.setId(listaRetornoConsulta.get(i).getId());
            aplicacion.setNombre(listaRetornoConsulta.get(i).getNombre());
            this.listaAplicacion.add(this.aplicacion);
        }
        ordenarAplicaciones();
    }

    // Metodo de carga de la aplicacion para poblar el jsf
    public String cargarApp() {
        try {
            aplicacion = appCompletoSinDTO(servPerSeguridad
                    .obtenerAplicacionTotal(this.idAplicacion));
            aplicacionRol = new AplicacionRol();
        } catch (ServicioException se) {
            msgError("Aplicacion no recuperada!");
        }
        return "opApp";
    }

    // Desde el boton CREAR de seguridadApp.xhtml
    public String nuevaApp() {
        aplicacion = new Aplicacion();
        aplicacionRol = new AplicacionRol();
        return "opApp";
    }

    // Metodo usado para transformar una aplicacion recuperada de una consultada
    private Aplicacion appCompletoSinDTO(AplicacionDTO appDTO) {
        aplicacion = new Aplicacion();

        aplicacion.setId(appDTO.getId());
        aplicacion.setNombre(appDTO.getNombre());

        for (AplicacionRolDTO appRolDTO : appDTO.getRoles()) {
            aplicacionRol = new AplicacionRol();
            Double idTabla = Math.random() * 100;
            aplicacionRol.setIdTabla(idTabla.longValue());
            aplicacionRol.setId(appRolDTO.getId());
            aplicacionRol.setNombre(appRolDTO.getNombre());
            aplicacionRol.setDescripcion(appRolDTO.getDescripcion());
            this.aplicacion.getRoles().add(this.aplicacionRol);
        }

        for (AplicacionMenuDTO appModDTO : appDTO.getModulos()) {
            aplicacionModulo = new AplicacionMenu();
            aplicacionModulo.setId(appModDTO.getId());
            aplicacionModulo.setNombre(appModDTO.getNombre());
            aplicacionModulo.setDescripcion(appModDTO.getDescripcion());

            for (MenuRolDTO mrDTO : appModDTO.getRoles()) {
                moduloRol = new MenuRol();
                moduloRol.setId(mrDTO.getId());

                aplicacionRol = new AplicacionRol();
                aplicacionRol.setId(mrDTO.getRol().getId());
                aplicacionRol.setNombre(mrDTO.getRol().getNombre());
                aplicacionRol.setDescripcion(mrDTO.getRol().getDescripcion());

                moduloRol.setRol(this.aplicacionRol);

                this.aplicacionModulo.getMenuRoles().add(this.moduloRol);
            }
            this.aplicacion.getModulos().add(this.aplicacionModulo);
        }
        asignarNumeroRol(this.aplicacion);
        return this.aplicacion;
    }

    // Metodo para asignar el numero de registro en la tabla
    private void asignarNumeroRol(Aplicacion app) {
        ordenarRoles();
        for (int i = 0; i < app.getRoles().size(); i++) {
            app.getRoles().get(i).setNumero(i + 1);
        }
    }

    // Metodo usado para transformar una aplicacion que se va ha editar
    private AplicacionDTO aplicacionConDTO(Aplicacion app) {
        AplicacionDTO appDTO = new AplicacionDTO();

        appDTO.setId(app.getId());
        appDTO.setNombre(app.getNombre());

        for (AplicacionRol apr : app.getRoles()) {
            AplicacionRolDTO appRolDTO = new AplicacionRolDTO();
            appRolDTO.setId(apr.getId());
            appRolDTO.setNombre(apr.getNombre());
            appRolDTO.setDescripcion(apr.getDescripcion());
            appDTO.getRoles().add(appRolDTO);
        }

        for (AplicacionMenu apm : app.getModulos()) {
            AplicacionMenuDTO appModuloDTO = new AplicacionMenuDTO();
            appModuloDTO.setId(apm.getId());
            appModuloDTO.setNombre(apm.getNombre());
            appModuloDTO.setDescripcion(apm.getDescripcion());

            for (MenuRol mr : apm.getMenuRoles()) {
                MenuRolDTO mrDTO = new MenuRolDTO();
                AplicacionRolDTO appRolDTO = new AplicacionRolDTO();

                appRolDTO.setId(mr.getRol().getId());
                appRolDTO.setNombre(mr.getRol().getNombre());
                appRolDTO.setDescripcion(mr.getRol().getDescripcion());

                mrDTO.setRol(appRolDTO);

                appModuloDTO.getRoles().add(mrDTO);
            }
            appDTO.getModulos().add(appModuloDTO);
        }

        return appDTO;
    }

    // Metodo que registra una aplicacion nueva
    private AplicacionDTO aplicacionConDTONueva(Aplicacion app) {
        AplicacionDTO appDTO = new AplicacionDTO();

        appDTO.setId(null);
        appDTO.setNombre(app.getNombre());

        for (AplicacionRol apr : app.getRoles()) {
            AplicacionRolDTO appRolDTO = new AplicacionRolDTO();
            appRolDTO.setId(null);
            appRolDTO.setNombre(apr.getNombre());
            appRolDTO.setDescripcion(apr.getDescripcion());
            appDTO.getRoles().add(appRolDTO);
        }
        return appDTO;
    }

    public void agregarAppRol() {
        if (this.aplicacionRol.getNombre() == null
                || this.aplicacionRol.getNombre().length() < 4) {
            msgAdvertencia("Debe cambiar el nombre del rol. Ej: Rol9");
            return;
        }

        if (existeAppRolPorId(this.aplicacionRol)) {
            for (int i = 0; i < this.aplicacion.getRoles().size(); i++) {
                if (this.aplicacion.getRoles().get(i).getIdTabla()
                        .equals(this.aplicacionRol.getIdTabla())) {
                    this.aplicacion.getRoles().remove(i);
                    this.aplicacion.getRoles().add(this.aplicacionRol);
                }
            }
        } else {
            if (!existeAppRolPorNombre(this.aplicacionRol)) {
                Double idTabla = Math.random() * 100;
                this.aplicacionRol.setIdTabla(idTabla.longValue());
                this.aplicacion.getRoles().add(this.aplicacionRol);
            }
        }
        asignarNumeroRol(this.aplicacion);
        this.aplicacionRol = new AplicacionRol();
    }

    private void ordenarRoles() {
        Collections.sort(this.aplicacion.getRoles());
    }

    private void ordenarAplicaciones() {
        Collections.sort(this.listaAplicacion);
    }

    private boolean existeAppRolPorId(AplicacionRol appRol) {
        if (appRol.getIdTabla() == null) {
            return false;
        }
        for (AplicacionRol apr : this.aplicacion.getRoles()) {
            if (appRol.getIdTabla().equals(apr.getIdTabla())) {
                return true;
            }
        }
        return false;
    }

    private boolean existeAppRolPorNombre(AplicacionRol appRol) {
        for (AplicacionRol apr : this.aplicacion.getRoles()) {
            if (appRol.getNombre().equals(apr.getNombre())) {
                return true;
            }
        }
        return false;
    }

    public void eliminarAppRol() {
        String referencias = rolesReferenciados(this.aplicacionRol);
        if (referencias.length() > 1) {
            msgError("No se pudo eliminar el rol porque se encontró referenciado en: "
                    + referencias);
            this.verLinkDesmarcarRol = true;
        } else {
            this.aplicacion.getRoles().remove(this.aplicacionRol);
            aplicacionRol = new AplicacionRol();
            this.verLinkDesmarcarRol = false;
            asignarNumeroRol(this.aplicacion);
        }
    }

    private String rolesReferenciados(AplicacionRol appRol) {
        String modulosReferenciados = "";
        for (int i = 0; i < this.aplicacion.getModulos().size(); i++) {
            for (int j = 0; j < this.aplicacion.getModulos().get(i)
                    .getMenuRoles().size(); j++) {
                if (this.aplicacion.getModulos().get(i).getMenuRoles().get(j)
                        .getRol().getId().equals(appRol.getId())) {
                    modulosReferenciados = modulosReferenciados + " "
                            + this.aplicacion.getModulos().get(i).getNombre();
                }
            }
        }
        return modulosReferenciados;
    }

    // Guardar una aplicacion (Aplicacion + Roles)
    public void guardarAppMasRoles() {
        if (!this.aplicacion.getNombre().equals("")
                && this.aplicacion.getNombre().length() > 3) {
            if (buscarExistenciaApp(this.aplicacion)) {
                try {
                    if (servPerSeguridad.registrarAplicacion(
                            aplicacionConDTO(this.aplicacion), true)) {
                        msgInfo("Los cambios se guardaron exitosamente!");
                    } else {
                        msgError("No se pudo guardar los cambios!");
                    }
                } catch (ServicioException se) {
                    msgError("No se pudo guardar la aplicacion!");
                }
            } else {
                try {
                    if (servPerSeguridad.registrarAplicacion(
                            aplicacionConDTONueva(this.aplicacion), true)) {
                        msgInfo("La aplicacion se creo exitosamente!");
                    } else {
                        msgError("No se pudo guardar la aplicacion!");
                    }
                } catch (ServicioException se) {
                    msgError("No se pudo guardar la aplicacion!");
                }
            }
        } else {
            msgAdvertencia("Debe cambiar el nombre de la aplicacion. Ej: App9");
        }
    }

    private boolean buscarExistenciaApp(Aplicacion apEditada) {
        List<AplicacionDTO> aplicacionesDTO = new ArrayList<>();
        try {
            aplicacionesDTO = servPerSeguridad.obtenerListaAplicaciones();
            for (AplicacionDTO appRecuperadaDTO : aplicacionesDTO) {
                if (appRecuperadaDTO.getId().equals(apEditada.getId())) {
                    return true;
                }
            }
        } catch (ServicioException se) {
            msgError("No se pudo recuperar aplicaciones buscadas!");
        }
        return false;
    }

    public void cargarRolesPorApp(ValueChangeEvent event) {
        idAplicacion = (Long) event.getNewValue();
        if (!event.getNewValue().equals(null)) {
            AplicacionDTO appDTO = new AplicacionDTO();
            try {
                appDTO = servPerSeguridad
                        .obtenerAplicacionTotal(this.idAplicacion);
                aplicacion = appCompletoSinDTO(appDTO);
            } catch (ServicioException se) {
                msgError("No se pudo recuperar los roles de la aplicacion!");
            }
        }
    }

    public String irOpModulo() {
        traerAplicaciones();
        aplicacionModulo = new AplicacionMenu();
        return "opModulo";
    }

    public String regresar() {
        traerAplicaciones();
        return "seguridadApp";
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

}
