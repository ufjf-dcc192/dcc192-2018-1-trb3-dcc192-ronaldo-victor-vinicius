package br.com.trab3.modelos;

public class Usuario {

    private Integer id;
    private String nome;
    private String login;
    private String email;
    private String senha;
    private Integer qtdAvaliacoesPositivas;
    private Integer qtdAvaliacoesNegativas;
    private Integer qtdComentarios;
    private Integer qtdComentariosAvaliadosNegativamente;

    public Usuario(String nome, String login, String email, String senha) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.qtdAvaliacoesPositivas = 0;
        this.qtdAvaliacoesNegativas = 0;
        this.qtdComentarios = 0;
        this.qtdComentariosAvaliadosNegativamente = 0;
    }

    public Usuario(Integer id, String nome, String login, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.qtdAvaliacoesPositivas = 0;
        this.qtdAvaliacoesNegativas = 0;
        this.qtdComentarios = 0;
        this.qtdComentariosAvaliadosNegativamente = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getQtdAvaliacoesPositivas() {
        return qtdAvaliacoesPositivas;
    }

    public void setQtdAvaliacoesPositivas(Integer qtdAvaliacoesPositivas) {
        this.qtdAvaliacoesPositivas = qtdAvaliacoesPositivas;
    }

    public Integer getQtdAvaliacoesNegativas() {
        return qtdAvaliacoesNegativas;
    }

    public void setQtdAvaliacoesNegativas(Integer qtdAvaliacoesNegativas) {
        this.qtdAvaliacoesNegativas = qtdAvaliacoesNegativas;
    }

    public Integer getQtdComentarios() {
        return qtdComentarios;
    }

    public void setQtdComentarios(Integer qtdComentarios) {
        this.qtdComentarios = qtdComentarios;
    }

    public Integer getQtdComentariosAvaliadosNegativamente() {
        return qtdComentariosAvaliadosNegativamente;
    }

    public void setQtdComentariosAvaliadosNegativamente(Integer qtdComentariosAvaliadosNegativamente) {
        this.qtdComentariosAvaliadosNegativamente = qtdComentariosAvaliadosNegativamente;
    }
}
