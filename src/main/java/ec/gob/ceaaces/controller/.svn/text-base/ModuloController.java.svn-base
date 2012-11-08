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

public class ModuloController {

    private String accionModulo;
    private String accionMenu;

    private Long idAplicacion;
    private Long idAplicacionMenu;
    private Long idPadre;

    private Aplicacion aplicacion;
    private AplicacionRol aplicacionRol;
    private AplicacionMenu aplicacionMenu;
    private MenuRol menuRol;

    private List<AplicacionMenu> padres = new ArrayList<AplicacionMenu>();
    private List<AplicacionMenu> arbolMenu = new ArrayList<AplicacionMenu>();
    private List<Aplicacion> listaAplicacion = new ArrayList<>();
    private List<AplicacionDTO> listaAplicacionDTO = new ArrayList<>();

    @Autowired
    private ServicioSeguridadModulosImpl servSeguridadMod;

    public ModuloController() {
        aplicacion = new Aplicacion();
        aplicacionRol = new AplicacionRol();
        aplicacionMenu = new AplicacionMenu();
        menuRol = new MenuRol();
    }

    public String getAccionMenu() {
        return accionMenu;
    }

    public void setAccionMenu(String accionMenu) {
        this.accionMenu = accionMenu;
    }

    public List<AplicacionMenu> getPadres() {
        return padres;
    }

    public void setPadres(List<AplicacionMenu> padres) {
        this.padres = padres;
    }

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

    public List<AplicacionMenu> getArbolMenu() {
        return arbolMenu;
    }

    public void setArbolMenu(List<AplicacionMenu> arbolMenu) {
        this.arbolMenu = arbolMenu;
    }

    public Long getIdAplicacionMenu() {
        return idAplicacionMenu;
    }

    public void setIdAplicacionMenu(Long idAplicacionMenu) {
        this.idAplicacionMenu = idAplicacionMenu;
    }

    public AplicacionRol getAplicacionRol() {
        return aplicacionRol;
    }

    public void setAplicacionRol(AplicacionRol aplicacionRol) {
        this.aplicacionRol = aplicacionRol;
    }

    public String getAccionModulo() {
        return accionModulo;
    }

    public void setAccionModulo(String accionModulo) {
        this.accionModulo = accionModulo;
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

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public AplicacionMenu getAplicacionMenu() {
        return aplicacionMenu;
    }

    public void setAplicacionMenu(AplicacionMenu aplicacionMenu) {
        this.aplicacionMenu = aplicacionMenu;
    }

    public MenuRol getmenuRol() {
        return menuRol;
    }

    public void setmenuRol(MenuRol menuRol) {
        this.menuRol = menuRol;
    }

    public List<Aplicacion> getListaAplicacion() {
        return listaAplicacion;
    }

    public void setListaAplicacion(List<Aplicacion> listaAplicacion) {
        this.listaAplicacion = listaAplicacion;
    }

    @PostConstruct
    public void traerAplicaciones() {
        this.listaAplicacionDTO.clear();
        this.listaAplicacion.clear();
        try {
            listaAplicacionDTO = servSeguridadMod.obtenerListaAplicaciones();
            cargarListaAplicacion(listaAplicacionDTO);
        } catch (ServicioException se) {
            msgError("No se pudo recuperar las aplicaciones");
        }
        this.idAplicacion = (long) 0;
    }

    // ok
    private void cargarListaAplicacion(List<AplicacionDTO> listaRetornoConsulta) {
        for (int i = 0; i < listaRetornoConsulta.size(); i++) {
            aplicacion = new Aplicacion();
            aplicacion.setId(listaRetornoConsulta.get(i).getId());
            aplicacion.setNombre(listaRetornoConsulta.get(i).getNombre());
            this.listaAplicacion.add(this.aplicacion);
        }
        this.listaAplicacion.add(new Aplicacion((long) 0, "Seleccionar..."));
        Collections.sort(this.listaAplicacion);
    }

    // ok
    public void cargarArbol(ValueChangeEvent event) {
        if (event.getNewValue() != null && (Long) event.getNewValue() > 0) {
            this.idAplicacion = (Long) event.getNewValue();
            try {
                aplicacion = new Aplicacion();
                this.aplicacion = appCompletaSinDTO(servSeguridadMod
                        .obtenerAplicacionTotal(this.idAplicacion));
            } catch (ServicioException se) {
                msgError("No se pudo recuperar los modulos!");
            }
        } else {
            this.aplicacion.getModulos().clear();
        }
    }

    // ok
    public void cargarRoles(ValueChangeEvent event) {
        if (event.getNewValue() != null && (Long) event.getNewValue() > 0) {
            this.idAplicacion = (Long) event.getNewValue();
            try {
                aplicacion = new Aplicacion();
                this.aplicacion = appCompletaSinDTO(servSeguridadMod
                        .obtenerAplicacionPorID(this.idAplicacion));
            } catch (ServicioException se) {
                msgError("No se pudo recuperar los roles!");
            }
        } else {
            this.aplicacion.getRoles().clear();
            this.aplicacionMenu.getMenuRoles().clear();
        }
    }

    // ok
    public String guardarAppMenu() {
        String salida = "";
        if (this.aplicacionMenu.getNombre() == null
                || this.aplicacionMenu.getNombre().length() < 4) {
            msgAdvertencia("El nombre del menu debe cambiar. Ej: Menu6");
        } else {
            if (this.aplicacionMenu.getId() == null) {
                if (existeMenuPorNombre(this.aplicacionMenu)) {
                    msgAdvertencia("Debe cambiar el nombre del menu, ese ya existe!");
                } else {
                    if (this.idPadre != null && this.idPadre > 0) {
                        AplicacionMenu apliMenuPadre = new AplicacionMenu();
                        apliMenuPadre.setId(this.idPadre);
                        this.aplicacionMenu.setPadre(apliMenuPadre);
                    } else {
                        this.aplicacionMenu.setPadre(null);
                    }
                    cargarmenuRoles();
                    try {
                        if (servSeguridadMod.registrarMenu(
                                appMenuConDTO(this.aplicacionMenu), true)) {
                            msgInfo("El menu: "
                                    + this.aplicacionMenu.getNombre()
                                    + " se guardo correctamente!");
                            salida = "seguridadModulo";
                        } else {
                            msgError("No se pudo guardar el menu!");
                        }
                    } catch (ServicioException se) {
                        msgError("No se pudo guardar el menu!");
                    }
                }
            } else {
                if (this.idPadre != null && this.idPadre > 0) {
                    AplicacionMenu apliMenuPadre = new AplicacionMenu();
                    apliMenuPadre.setId(this.idPadre);
                    this.aplicacionMenu.setPadre(apliMenuPadre);
                } else {
                    this.aplicacionMenu.setPadre(null);
                }
                cargarmenuRoles();
                try {
                    if (servSeguridadMod.registrarMenu(
                            appMenuConDTO(this.aplicacionMenu), true)) {
                        msgInfo("Los cambios del menu: "
                                + this.aplicacionMenu.getNombre()
                                + " se guardaron correctamente!");
                        salida = "seguridadModulo";
                    } else {
                        msgError("Los cambios no se guardaron!");
                    }
                } catch (ServicioException se) {
                    msgError("No se pudo guardar el menu!");
                }
            }
            falsearRoles();
            aplicacionMenu = new AplicacionMenu();
        }
        this.idAplicacion = (long) 0;
        return salida;
    }

    // ok
    // Transformacion en menuRol de los roles que han sido seleccionados para
    // un memu.
    private void cargarmenuRoles() {
        this.aplicacionMenu.getMenuRoles().clear();

        for (int i = 0; i < this.aplicacion.getRoles().size(); i++) {
            if (this.aplicacion.getRoles().get(i).getEscritura()) {
                menuRol = new MenuRol();
                aplicacionRol = new AplicacionRol();
                this.aplicacionRol.setId(this.aplicacion.getRoles().get(i)
                        .getId());
                this.menuRol.setRol(this.aplicacionRol);
                this.menuRol.setEscritura(true);
                this.menuRol.setLectura(true);
                this.aplicacionMenu.getMenuRoles().add(this.menuRol);
            } else if (this.aplicacion.getRoles().get(i).getLectura()
                    && !this.aplicacion.getRoles().get(i).getEscritura()) {
                menuRol = new MenuRol();
                aplicacionRol = new AplicacionRol();
                this.aplicacionRol.setId(this.aplicacion.getRoles().get(i)
                        .getId());
                this.menuRol.setRol(this.aplicacionRol);
                this.menuRol.setLectura(true);
                this.menuRol.setEscritura(false);
                this.aplicacionMenu.getMenuRoles().add(this.menuRol);
            }
        }
    }

    // Setear en FALSE todos los roles de la aplicacion para crear un nuevo
    // modulo.
    private void falsearRoles() {
        for (int i = 0; i < this.aplicacion.getRoles().size(); i++) {
            this.aplicacion.getRoles().get(i).setEscritura(false);
            this.aplicacion.getRoles().get(i).setLectura(false);
        }
    }

    // ok
    private boolean existeMenuPorNombre(AplicacionMenu apmenu) {
        for (AplicacionMenu apm : this.aplicacion.getModulos()) {
            if (apm.getNombre().equals(apmenu.getNombre())) {
                return true;
            }
        }
        return false;
    }

    // ok
    // Metodo usado para transformar una aplicacion recuperada de una consulta
    private Aplicacion appCompletaSinDTO(AplicacionDTO appDTO) {
        aplicacion = new Aplicacion();

        this.aplicacion.setId(appDTO.getId());
        this.aplicacion.setNombre(appDTO.getNombre());

        for (AplicacionRolDTO appRolDTO : appDTO.getRoles()) {
            aplicacionRol = new AplicacionRol();

            this.aplicacionRol.setId(appRolDTO.getId());
            this.aplicacionRol.setNombre(appRolDTO.getNombre());
            this.aplicacionRol.setDescripcion(appRolDTO.getDescripcion());

            this.aplicacion.getRoles().add(this.aplicacionRol);
        }

        for (AplicacionMenuDTO appMenuDTO : appDTO.getModulos()) {
            aplicacionMenu = new AplicacionMenu();

            int nivel = 0;
            nivel = appMenuDTO.getNivel();

            this.aplicacionMenu.setId(appMenuDTO.getId());
            this.aplicacionMenu.setNombre(appMenuDTO.getNombre());
            this.aplicacionMenu.setDescripcion(appMenuDTO.getDescripcion());
            this.aplicacionMenu.setDestino(appMenuDTO.getDestino());

            this.aplicacionMenu.setNivel(String.valueOf(nivel));

            if (appMenuDTO.getPadre() != null) {
                AplicacionMenu appMenuPadre = new AplicacionMenu();
                appMenuPadre.setId(appMenuDTO.getPadre().getId());
                this.aplicacionMenu.setPadre(appMenuPadre);
            } else {
                this.aplicacionMenu.setPadre(null);
            }

            for (MenuRolDTO mrDTO : appMenuDTO.getRoles()) {
                menuRol = new MenuRol();

                this.menuRol.setId(mrDTO.getId());
                this.menuRol.setEscritura(mrDTO.getEscritura());
                this.menuRol.setLectura(mrDTO.getLectura());

                aplicacionRol = new AplicacionRol();
                this.aplicacionRol.setId(mrDTO.getRol().getId());
                this.aplicacionRol.setNombre(mrDTO.getRol().getNombre());
                this.aplicacionRol.setDescripcion(mrDTO.getRol()
                        .getDescripcion());

                this.menuRol.setRol(this.aplicacionRol);
                this.aplicacionMenu.getMenuRoles().add(this.menuRol);
            }
            this.aplicacion.getModulos().add(this.aplicacionMenu);
        }
        padreConHijos(this.aplicacion.getModulos());
        return this.aplicacion;
    }

    // ok
    private void padreConHijos(List<AplicacionMenu> menus) {
        this.arbolMenu.clear();
        List<AplicacionMenu> soloHijos = new ArrayList<>();

        for (int i = 0; i < menus.size(); i++) {
            if (menus.get(i).getPadre() != null) {
                soloHijos.add(menus.get(i));
            }
        }

        for (int i = 0; i < menus.size(); i++) {
            if (menus.get(i).getNivel().equals("1")) {
                this.arbolMenu.add(menus.get(i));
            }
            for (int j = 0; j < soloHijos.size(); j++) {
                if (menus.get(i).getId()
                        .equals(soloHijos.get(j).getPadre().getId())) {
                    menus.get(i).getHijos().add(soloHijos.get(j));
                }
            }
        }
        Collections.sort(this.arbolMenu);
    }

    // ok
    private void cargarCheckboxes() {
        for (int i = 0; i < this.aplicacion.getRoles().size(); i++) {
            for (int j = 0; j < this.aplicacionMenu.getMenuRoles().size(); j++) {
                if (this.aplicacion
                        .getRoles()
                        .get(i)
                        .getId()
                        .equals(this.aplicacionMenu.getMenuRoles().get(j)
                                .getRol().getId())) {

                    if (this.aplicacionMenu.getMenuRoles().get(j)
                            .getEscritura()) {
                        this.aplicacion.getRoles().get(i).setEscritura(true);
                        this.aplicacion.getRoles().get(i).setLectura(false);
                    }

                    if (this.aplicacionMenu.getMenuRoles().get(j).getLectura()
                            && !this.aplicacionMenu.getMenuRoles().get(j)
                                    .getEscritura()) {
                        this.aplicacion.getRoles().get(i).setLectura(true);
                        this.aplicacion.getRoles().get(i).setEscritura(false);
                    }
                }
            }
        }
    }

    // ok
    private AplicacionMenu obtenerMenuActual() {
        for (int a = 0; a < this.aplicacion.getModulos().size(); a++) {
            if (this.idAplicacionMenu.equals(this.aplicacion.getModulos()
                    .get(a).getId())) {
                return this.aplicacion.getModulos().get(a);
            }
        }
        return null;
    }

    // ok
    private AplicacionMenuDTO appMenuConDTO(AplicacionMenu appMenu) {
        AplicacionMenuDTO appMenuDTO = new AplicacionMenuDTO();

        if (appMenu.getId() != null) {
            appMenuDTO.setId(appMenu.getId());
        }
        appMenuDTO.setNombre(appMenu.getNombre());
        appMenuDTO.setDescripcion(appMenu.getDescripcion());
        appMenuDTO.setDestino(appMenu.getDestino());

        AplicacionDTO appDTO = new AplicacionDTO();
        appDTO.setId(this.idAplicacion);
        appMenuDTO.setAplicacion(appDTO);

        if (appMenu.getPadre() != null) {
            AplicacionMenuDTO appMenuPadreDTO = new AplicacionMenuDTO();
            appMenuPadreDTO.setId(appMenu.getPadre().getId());
            appMenuDTO.setPadre(appMenuPadreDTO);
        }

        for (MenuRol mr : appMenu.getMenuRoles()) {
            MenuRolDTO mrDTO = new MenuRolDTO();
            AplicacionRolDTO appRolDTO = new AplicacionRolDTO();

            appRolDTO.setId(mr.getRol().getId());

            mrDTO.setRol(appRolDTO);
            mrDTO.setEscritura(mr.getEscritura());
            mrDTO.setLectura(mr.getLectura());

            appMenuDTO.getRoles().add(mrDTO);
        }

        return appMenuDTO;
    }

    // ok
    // Lista completa para el combo de padres
    private void cargarPadresNuevo() {
        for (int i = 0; i < this.aplicacion.getModulos().size(); i++) {
            this.padres.add(this.aplicacion.getModulos().get(i));
        }
    }

    // ok
    // Lista completa, excepto el propio menu, para el combo de padres
    private void cargarPadres(AplicacionMenu appMenu) {
        for (int i = 0; i < this.aplicacion.getModulos().size(); i++) {
            if (!this.aplicacion.getModulos().get(i).getId()
                    .equals(appMenu.getId())) {
                this.padres.add(this.aplicacion.getModulos().get(i));
            }
        }
    }

    // ?
    public String eliminarMenu() {
        String salida = "";
        try {
            if (tieneHijos(this.aplicacionMenu)) {
                msgAdvertencia("El menu no pudo ser eliminado ya que tiene submenus!");
            } else {
                if (servSeguridadMod.borrarMenu(this.idAplicacionMenu)) {
                    msgInfo("El menu se elimino exitosamente!");
                    salida = "seguridadModulo";
                    this.idAplicacion = (long) 0;
                } else {
                    msgError("No se pudo eliminar el menu!");
                }
            }
        } catch (ServicioException se) {
            msgError("No se pudo eliminar el menu!");
        }
        return salida;
    }

    // ok
    private boolean tieneHijos(AplicacionMenu appMenuSeleccionada) {
        if (!appMenuSeleccionada.getHijos().isEmpty()) {
            return true;
        }
        return false;
    }

    public String irNuevoModulo() {
        this.padres.clear();
        cargarPadresNuevo();
        falsearRoles();
        aplicacionMenu = new AplicacionMenu();
        this.idPadre = (long) -1;
        return "opModulo";
    }

    public String irOpModulo() {
        this.padres.clear();
        aplicacionMenu = new AplicacionMenu();
        this.aplicacionMenu = obtenerMenuActual();
        cargarPadres(this.aplicacionMenu);
        cargarCheckboxes();
        return "opModulo";
    }

    public String irOpModulo2() {
        this.padres.clear();
        aplicacionMenu = new AplicacionMenu();
        // this.aplicacionMenu = obtenerMenuActual();
        cargarPadres(this.aplicacionMenu);
        cargarCheckboxes();
        return "templateMenu";
    }

    public String regresar() {
        this.idAplicacion = (long) 0;
        return "seguridadModulo";
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
