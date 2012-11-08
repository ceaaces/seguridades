package ec.gob.ceaaces.services;

import java.util.List;

import ec.gob.ceaaces.model.dtos.seguridades.AplicacionDTO;
import ec.gob.ceaaces.model.dtos.seguridades.AplicacionMenuDTO;
import ec.gob.ceaaces.services.exceptions.ServicioException;

public interface SeguridadModulos {

    public Boolean registrarAplicacion(AplicacionDTO aplicacionDTO,
            Boolean conRoles) throws ServicioException;

    public Boolean registrarMenu(AplicacionMenuDTO appMenuDTO,
            Boolean conModuloRoles) throws ServicioException;

    public List<AplicacionDTO> obtenerListaAplicaciones()
            throws ServicioException;

    public AplicacionDTO obtenerAplicacionPorID(Long id)
            throws ServicioException;

    public AplicacionDTO obtenerAplicacionTotal(Long id)
            throws ServicioException;

    public Boolean borrarMenu(Long idMenu) throws ServicioException;
}
