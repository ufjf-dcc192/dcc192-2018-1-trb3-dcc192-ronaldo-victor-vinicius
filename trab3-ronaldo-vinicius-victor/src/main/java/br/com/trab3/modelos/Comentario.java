package br.com.trab3.modelos;

public class Comentario {
    private Integer id;
    private Integer idUsuarioProprietario;
    private String texto;
    private String dataHoraCriacao;
    private String dataHoraUltimaAtualizacao;

    public Comentario(Integer idUsuarioProprietario, String texto, String dataHoraCriacao, String dataHoraUltimaAtualizacao) {
        this.idUsuarioProprietario = idUsuarioProprietario;
        this.texto = texto;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
    }

    public Comentario(Integer id, Integer idUsuarioProprietario, String texto, String dataHoraCriacao, String dataHoraUltimaAtualizacao) {
        this.id = id;
        this.idUsuarioProprietario = idUsuarioProprietario;
        this.texto = texto;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuarioProprietario() {
        return idUsuarioProprietario;
    }

    public void setIdUsuarioProprietario(Integer idUsuarioProprietario) {
        this.idUsuarioProprietario = idUsuarioProprietario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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
    
    

}
