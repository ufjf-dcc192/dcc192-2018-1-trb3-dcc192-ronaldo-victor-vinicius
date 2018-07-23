package br.com.trab3.modelos;

public class AvaliacaoItem {
    private Integer idAvaliacaoItem;
    private Integer avaliacao;
    private Integer idItem;
    private Integer idUsuario;

    public AvaliacaoItem(Integer idAvaliacaoItem, Integer avaliacao, Integer idItem, Integer idUsuario) {
        this.idAvaliacaoItem = idAvaliacaoItem;
        this.avaliacao = avaliacao;
        this.idItem = idItem;
        this.idUsuario = idUsuario;
    }

    public Integer getIdAvaliacaoItem() {
        return idAvaliacaoItem;
    }

    public void setIdAvaliacaoItem(Integer idAvaliacaoItem) {
        this.idAvaliacaoItem = idAvaliacaoItem;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
