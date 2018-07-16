package br.com.trab3.modelos;

public class Item {
    private Integer id;
    private String titulo;
    private String descricao;
    private String dataHoraCriacao;
    private String dataHoraUltimaAtualizacao;
    private Integer idUsuarioProprietario;

    public Item(String titulo, String descricao, String dataHoraCriacao, String dataHoraUltimaAtualizacao, Integer idUsuarioProprietario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
        this.idUsuarioProprietario = idUsuarioProprietario;
    }

    public Item(Integer id, String titulo, String descricao, String dataHoraCriacao, String dataHoraUltimaAtualizacao, Integer idUsuarioProprietario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
        this.idUsuarioProprietario = idUsuarioProprietario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    
}
