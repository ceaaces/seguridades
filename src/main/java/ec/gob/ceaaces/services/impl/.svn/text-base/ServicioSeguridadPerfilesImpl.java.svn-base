package ec.gob.ceaaces.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ec.gob.ceaaces.model.dtos.seguridades.PerfilDTO;
import ec.gob.ceaaces.services.SeguridadPerfiles;
import ec.gob.ceaaces.services.exceptions.ServicioException;
import ec.gob.ceaaces.services.remote.SeguridadServiceRemote;
import ec.gob.ceaaces.util.JBOSS7EJB3ContextHelper;

public class ServicioSeguridadPerfilesImpl implements SeguridadPerfiles {

    @Autowired
    private JBOSS7EJB3ContextHelper jboss7ejb3ContextHelper;

    @Override
    public List<PerfilDTO> obtenerPerfiles(Long idAplicacion)
            throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.obtenerPerfilesPorAplicacion(idAplicacion);
    }

    @Override
    public PerfilDTO obtenerPerfil(Long idPerfil) throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.obtenerPerfil(idPerfil);
    }

    @Override
    public Boolean registrarPerfil(PerfilDTO perfilDTO)
            throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.registrarPerfil(perfilDTO);
    }

    @Override
    public Boolean eliminarPerfil(Long idPerfil) throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.eliminarPerfil(idPerfil);
    }
}
