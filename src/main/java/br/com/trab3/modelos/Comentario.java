package br.com.trab3.modelos;

import java.util.ArrayList;

public class Comentario {
    private Integer idComentario;
    private String titulo;
    private String texto;
    private String dataHoraCriacao;
    private String dataHoraUltimaAtualizacao;
    private Integer idUsuarioProprietario;
    private Integer idItemComentado;
    private ArrayList<AvaliacaoComentario> avaliacoes;
    private Integer quantidadeAvaliacoesPositivas;
    private Integer quantidadeAvaliacoesNegativas;

    public Comentario(Integer idComentario, String titulo, String texto, String dataHoraCriacao, String dataHoraUltimaAtualizacao, Integer idUsuarioProprietario, Integer idItemComentado, Integer quantidadeAvaliacoesPositivas, Integer quantidadeAvaliacoesNegativas) {
        this.idComentario = idComentario;
        this.titulo = titulo;
        this.texto = texto;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
        this.idUsuarioProprietario = idUsuarioProprietario;
        this.idItemComentado = idItemComentado;
        this.quantidadeAvaliacoesPositivas = quantidadeAvaliacoesPositivas;
        this.quantidadeAvaliacoesNegativas = quantidadeAvaliacoesNegativas;
        this.avaliacoes = null;
    }

    public Comentario(Integer idComentario, String titulo, String texto, String dataHoraCriacao, String dataHoraUltimaAtualizacao, Integer idUsuarioProprietario, Integer idItemComentado, ArrayList<AvaliacaoComentario> avaliacoes) {
        this.idComentario = idComentario;
        this.titulo = titulo;
        this.texto = texto;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
        this.idUsuarioProprietario = idUsuarioProprietario;
        this.idItemComentado = idItemComentado;
        this.avaliacoes = avaliacoes;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Integer getIdUsuarioProprietario() {
        return idUsuarioProprietario;
    }

    public void setIdUsuarioProprietario(Integer idUsuarioProprietario) {
        this.idUsuarioProprietario = idUsuarioProprietario;
    }

    public Integer getIdItemComentado() {
        return idItemComentado;
    }

    public void setIdItemComentado(Integer idItemComentado) {
        this.idItemComentado = idItemComentado;
    }

    public ArrayList<AvaliacaoComentario> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(ArrayList<AvaliacaoComentario> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Integer getQuantidadeAvaliacoesPositivas() {
        if (avaliacoes == null) {
            return quantidadeAvaliacoesPositivas;
        } else {
            Integer resultado = 0;
            for (AvaliacaoComentario avaliacao : avaliacoes) {
                if (avaliacao.getAvaliacao() >= 1) {
                    resultado++;
                }
            }
            return resultado;
        }
    }

    public void setQuantidadeAvaliacoesPositivas(Integer quantidadeAvaliacoesPositivas) {
        this.quantidadeAvaliacoesPositivas = quantidadeAvaliacoesPositivas;
    }

    public Integer getQuantidadeAvaliacoesNegativas() {
        if (avaliacoes == null) {
            return quantidadeAvaliacoesNegativas;
        } else {
            Integer resultado = 0;
            for (AvaliacaoComentario avaliacao : avaliacoes) {
                if (avaliacao.getAvaliacao() <= -1) {
                    resultado++;
                }
            }
            return resultado;
        }
    }

    public void setQuantidadeAvaliacoesNegativas(Integer quantidadeAvaliacoesNegativas) {
        this.quantidadeAvaliacoesNegativas = quantidadeAvaliacoesNegativas;
    }
    
    public Integer calcularSomatorioDeAvaliacoes() {
        if (avaliacoes == null) {
            return quantidadeAvaliacoesPositivas - quantidadeAvaliacoesNegativas;
        } else {
            Integer resultado = 0;
            for (AvaliacaoComentario avaliacao : avaliacoes) {
                resultado += avaliacao.getAvaliacao();
            }
            return resultado;
        }
    }
}
