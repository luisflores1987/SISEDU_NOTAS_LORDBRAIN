package com.sisedu.model.bean;

public class ListaMenu {

    int nIdListaMenu;
    String sDescripcion;
    ListaMenu ListaMenu;
    String sFormulario;

    public ListaMenu(int nIdListaMenu, String sDescripcion, ListaMenu ListaMenu, String sFormulario) {
        this.nIdListaMenu = nIdListaMenu;
        this.sDescripcion = sDescripcion;
        this.ListaMenu = ListaMenu;
        this.sFormulario = sFormulario;
    }

    public ListaMenu getListaMenu() {
        return ListaMenu;
    }

    public int getnIdListaMenu() {
        return nIdListaMenu;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public String getsFormulario() {
        return sFormulario;
    }    
}
