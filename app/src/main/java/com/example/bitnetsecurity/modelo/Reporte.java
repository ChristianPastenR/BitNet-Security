package com.example.bitnetsecurity.modelo;



public class Reporte {

    String id;
    String usuario;
    String fecha;

    String empresa;
    String faccion;
    String turno;

    String ggss;
    String supervisor;
    String reporte;



    public Reporte() {

    }

    public Reporte(String id, String usuario, String fecha, String empresa, String faccion, String turno, String ggss, String supervisor, String reporte) {
        this.id = id;
        this.usuario = usuario;
        this.fecha = fecha;
        this.empresa = empresa;
        this.faccion = faccion;
        this.turno = turno;
        this.ggss = ggss;
        this.supervisor = supervisor;
        this.reporte = reporte;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFaccion() {
        return faccion;
    }

    public void setFaccion(String faccion) {
        this.faccion = faccion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getGgss() {
        return ggss;
    }

    public void setGgss(String ggss) {
        this.ggss = ggss;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }
}
