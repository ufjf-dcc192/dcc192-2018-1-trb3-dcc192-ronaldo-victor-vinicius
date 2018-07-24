package br.com.trab3.modelos;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private Integer idItem;
    private String titulo;
    private String descricao;
    private String dataHoraCriacao;
    private String dataHoraUltimaAtualizacao;
    private Integer idUsuarioProprietario;
    
   private ArrayList<Link> links;
    private ArrayList<Comentario> comentarios;
    private ArrayList<AvaliacaoItem> avaliacoes;
    
    private Integer quantidadeLinks;
    private Integer quantidadeComentarios;
    private Integer quantidadeAvaliacoesPositivas;
    private Integer quantidadeAvaliacoesNegativas;
    

    public Item(Integer idItem, String titulo, String descricao, String dataHoraCriacao, String dataHoraUltimaAtualizacao, Integer idUsuarioProprietario, Integer quantidadeLinks, Integer quantidadeComentarios, Integer quantidadeAvaliacoesPositivas, Integer quantidadeAvaliacoesNegativas) {
        this.idItem = idItem;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
        this.idUsuarioProprietario = idUsuarioProprietario;
        this.quantidadeLinks = quantidadeLinks;
        this.quantidadeComentarios = quantidadeComentarios;
        this.quantidadeAvaliacoesPositivas = quantidadeAvaliacoesPositivas;
        this.quantidadeAvaliacoesNegativas = quantidadeAvaliacoesNegativas;
        this.links = null;
        this.comentarios = null;
        this.avaliacoes = null;
    }

    public Item(Integer idItem, String titulo, String descricao, String dataHoraCriacao, String dataHoraUltimaAtualizacao, Integer idUsuarioProprietario, ArrayList<Link> links, ArrayList<Comentario> comentarios, ArrayList<AvaliacaoItem> avaliacoes) {
        this.idItem = idItem;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
        this.idUsuarioProprietario = idUsuarioProprietario;
        this.links = links;
        this.comentarios = comentarios;
        this.avaliacoes = avaliacoes;
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

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
    
    public List<Comentario> getPrincipaisComentarios() {
        if (comentarios == null) {
            return new ArrayList<>();
        } else {
            return (List<Comentario>) comentarios.subList(0, (comentarios.size() < 5 ? comentarios.size() : 5));
        }
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<AvaliacaoItem> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(ArrayList<AvaliacaoItem> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Integer getQuantidadeLinks() {
        if (links == null) {
            return quantidadeLinks;
        } else {
            return links.size();
        }
    }

    public void setQuantidadeLinks(Integer quantidadeLinks) {
        this.quantidadeLinks = quantidadeLinks;
    }

    public Integer getQuantidadeComentarios() {
        if (comentarios == null) {
            return quantidadeComentarios;
        } else {
            return comentarios.size();
        }
    }

    public void setQuantidadeComentarios(Integer quantidadeComentarios) {
        this.quantidadeComentarios = quantidadeComentarios;
    }

    public Integer getQuantidadeAvaliacoesPositivas() {
        if (avaliacoes == null) {
            return quantidadeAvaliacoesPositivas;
        } else {
            Integer resultado = 0;
            for (AvaliacaoItem avaliacao : avaliacoes) {
                if (avaliacao.getAvaliacao() > 0) {
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
            for (AvaliacaoItem avaliacao : avaliacoes) {
                if (avaliacao.getAvaliacao() < 0) {
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
            for (AvaliacaoItem avaliacao : avaliacoes) {
                resultado += avaliacao.getAvaliacao();
            }
            return resultado;
        }
    }
}
