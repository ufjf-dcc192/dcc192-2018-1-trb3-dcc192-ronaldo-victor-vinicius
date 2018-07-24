package br.com.trab3.modelos;

public class AvaliacaoComentario {

    private Integer idAvaliacaoComentario;
    private Integer avaliacao;
    private Integer idComentario;
    private Integer idUsuario;

    public AvaliacaoComentario(Integer idAvaliacaoComentario, Integer avaliacao, Integer idComentario, Integer idUsuario) {
        this.idAvaliacaoComentario = idAvaliacaoComentario;
        this.avaliacao = avaliacao;
        this.idComentario = idComentario;
        this.idUsuario = idUsuario;
    }

    public Integer getIdAvaliacaoComentario() {
        return idAvaliacaoComentario;
    }

    public void setIdAvaliacaoComentario(Integer idAvaliacaoComentario) {
        this.idAvaliacaoComentario = idAvaliacaoComentario;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
