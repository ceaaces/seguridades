package ec.gob.ceaaces.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ec.gob.ceaaces.model.dtos.seguridades.UsuarioDTO;
import ec.gob.ceaaces.services.SeguridadUsuarios;
import ec.gob.ceaaces.services.exceptions.ServicioException;
import ec.gob.ceaaces.services.remote.SeguridadServiceRemote;
import ec.gob.ceaaces.util.JBOSS7EJB3ContextHelper;

public class ServicioSeguridadUsuariosImpl implements SeguridadUsuarios {

    @Autowired
    private JBOSS7EJB3ContextHelper jboss7ejb3ContextHelper;

    @Override
    public List<UsuarioDTO> obtenerListaUsuarios() throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.obtenerUsuarios();
    }

    @Override
    public UsuarioDTO obtenerUsuario(String username) throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.obtenerUsuario(username);
    }

    @Override
    public Boolean registrarUsuario(UsuarioDTO usuarioDTO)
            throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.registrarUsuario(usuarioDTO);
    }

    @Override
    public Boolean eliminarUsuario(String username) throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.eliminarUsuario(username);
    }

    @Override
    public Boolean verificarUsuario(String username, String password)
            throws ServicioException {
        SeguridadServiceRemote seguridadServicio = (SeguridadServiceRemote) jboss7ejb3ContextHelper
                .getServicioPorNombre("seguridadService");
        return seguridadServicio.verificarUsuario(username, password);
    }
}
