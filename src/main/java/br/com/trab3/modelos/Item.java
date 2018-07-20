package br.com.trab3.modelos;

import java.util.ArrayList;

public class Item {
    private Integer idItem;
    private String titulo;
    private String descricao;
    private ArrayList<String> links;
    private String dataHoraCriacao;
    private String dataHoraUltimaAtualizacao;
    private Integer idUsuarioProprietario;
    private Integer quantidadeLinks;

    public Item(Integer idItem, String titulo, String descricao, String dataHoraCriacao, String dataHoraUltimaAtualizacao, Integer idUsuarioProprietario) {
        this.idItem = idItem;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
        this.idUsuarioProprietario = idUsuarioProprietario;
        this.quantidadeLinks = 0;
    }

    public Item(Integer idItem, String titulo, String descricao, ArrayList<String> links, String dataHoraCriacao, String dataHoraUltimaAtualizacao, Integer idUsuarioProprietario) {
        this.idItem = idItem;
        this.titulo = titulo;
        this.descricao = descricao;
        this.links = links;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
        this.idUsuarioProprietario = idUsuarioProprietario;
        this.quantidadeLinks = 0;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }

    public String getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(String dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public String getDataHoraUltimaAtualizacao() {
        return dataHoraUltimaAtualizacao;
    }

    public void setDataHoraUltimaAtualizacao(String dataHoraUltimaAtualizacao) {
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
    }

    public Integer getIdUsuarioProprietario() {
        return idUsuarioProprietario;
    }

    public void setIdUsuarioProprietario(Integer idUsuarioProprietario) {
        this.idUsuarioProprietario = idUsuarioProprietario;
    }

      public Integer getQuantidadeLinks() {
        return quantidadeLinks;
    }

    public void setQuantidadeLinks(Integer quantidadeLinks) {
        this.quantidadeLinks = quantidadeLinks;
    }
}
