package br.com.trab3.modelos;

public class Link {

    private Integer idLink;
    private String link;
    private Integer idItemRelacionado;

    public Link(Integer idLink, String link, Integer idItemRelacionado) {
        this.idLink = idLink;
        this.link = link;
        this.idItemRelacionado = idItemRelacionado;
    }

    public Integer getIdLink() {
        return idLink;
    }

    public void setIdLink(Integer idLink) {
        this.idLink = idLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getIdItemRelacionado() {
        return idItemRelacionado;
    }

    public void setIdItemRelacionado(Integer idItemRelacionado) {
        this.idItemRelacionado = idItemRelacionado;
    }

}
