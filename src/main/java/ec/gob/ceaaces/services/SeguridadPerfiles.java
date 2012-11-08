package ec.gob.ceaaces.services;

import java.util.List;

import ec.gob.ceaaces.model.dtos.seguridades.PerfilDTO;
import ec.gob.ceaaces.services.exceptions.ServicioException;

public interface SeguridadPerfiles {

    List<PerfilDTO> obtenerPerfiles(Long idAplicacion) throws ServicioException;

    PerfilDTO obtenerPerfil(Long idPerfil) throws ServicioException;

    Boolean registrarPerfil(PerfilDTO perfilDTO) throws ServicioException;

    Boolean eliminarPerfil(Long idPerfil) throws ServicioException;
}
