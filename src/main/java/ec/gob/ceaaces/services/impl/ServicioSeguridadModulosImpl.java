package ec.gob.ceaaces.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ec.gob.ceaaces.model.dtos.seguridades.AplicacionDTO;
import ec.gob.ceaaces.model.dtos.seguridades.AplicacionMenuDTO;
import ec.gob.ceaaces.services.SeguridadModulos;
import ec.gob.ceaaces.services.exceptions.ServicioException;
import ec.gob.ceaaces.services.remote.SeguridadServiceRemote;
import ec.gob.ceaaces.util.JBOSS7EJB3ContextHelper;

public class ServicioSeguridadModulosImpl implements SeguridadModulos {

    @Autowired
    private JBOSS7EJB3ContextHelper jboss7ejb3ContextHelper;

    @Override
    public Boolean registrarAplicacion(AplicacionDTO appDTO, Boolean conRoles)
            throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.registrarAplicacion(appDTO, conRoles);
    }

    @Override
    public Boolean registrarMenu(AplicacionMenuDTO appMenuDTO,
            Boolean conModuloRoles) throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.registrarMenu(appMenuDTO, conModuloRoles);
    }

    @Override
    public List<AplicacionDTO> obtenerListaAplicaciones()
            throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.obtenerAplicaciones();
    }

    @Override
    public AplicacionDTO obtenerAplicacionPorID(Long id)
            throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.obtenerAplicacion(id);
    }

    @Override
    public AplicacionDTO obtenerAplicacionTotal(Long id)
            throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.obtenerAplicacionDetallado(id);
    }

    @Override
    public Boolean borrarMenu(Long idMenu) throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.eliminarMenu(idMenu);
    }
}
