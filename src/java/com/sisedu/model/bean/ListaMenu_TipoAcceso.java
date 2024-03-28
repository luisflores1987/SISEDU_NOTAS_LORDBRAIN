package com.sisedu.model.bean;

public class ListaMenu_TipoAcceso {

    int nIdListaMenuTipoAcceso;
    ListaMenu listaMenu;
    TipoAcceso TipoAcceso;
    //transient
    String nivelMenu;

    public ListaMenu_TipoAcceso(int nIdListaMenuTipoAcceso, ListaMenu listaMenu, TipoAcceso TipoAcceso, String nivelMenu) {
        this.nIdListaMenuTipoAcceso = nIdListaMenuTipoAcceso;
        this.listaMenu = listaMenu;
        this.TipoAcceso = TipoAcceso;
        this.nivelMenu = nivelMenu;
    }

    public ListaMenu getlistaMenu() {
        return listaMenu;
    }

    public TipoAcceso getTipoAcceso() {
        return TipoAcceso;
    }

    public int getnIdListaMenuTipoAcceso() {
        return nIdListaMenuTipoAcceso;
    }

    public String getNivelMenu() {
        return nivelMenu;
    }
    
    
}
