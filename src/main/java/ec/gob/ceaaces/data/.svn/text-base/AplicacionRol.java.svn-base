package ec.gob.ceaaces.data;

import java.io.Serializable;

public class AplicacionRol implements Serializable, Comparable<AplicacionRol> {

    private static final long serialVersionUID = -5203981034872326601L;

    private int numero;

    private Long id;
    private Long idTabla;
    private String nombre;
    private String descripcion;
    private Aplicacion aplicacion;

    private Boolean lectura = false;
    private Boolean escritura = false;

    private Boolean seleccionado = false;

    public AplicacionRol() {

    }

    public AplicacionRol(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion.toUpperCase();
    }

    public AplicacionRol(Long id, String nombre, String descripcion,
            Aplicacion aplicacion) {
        this.id = id;
        this.nombre = nombre.toUpperCase();
        this.descripcion = descripcion;
        this.aplicacion = aplicacion;
    }

    public Boolean getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Boolean getLectura() {
        return lectura;
    }

    public void setLectura(Boolean lectura) {
        this.lectura = lectura;
    }

    public Boolean getEscritura() {
        return escritura;
    }

    public void setEscritura(Boolean escritura) {
        this.escritura = escritura;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(Long idTabla) {
        this.idTabla = idTabla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Override
    public int compareTo(AplicacionRol o) {
        return this.nombre.compareTo(o.getNombre());
    }

}