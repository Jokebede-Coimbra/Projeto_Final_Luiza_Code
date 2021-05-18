package br.com.luizacode.wishlist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotNull
    private String nome;

    @Column(name = "valor")
    @NotNull
    private BigDecimal valor;

    @Column(name = "descricao")
    @NotNull
    private String descricao;

    // IMAGEM JPEG? precisa de coluna? //
    // A ideia é que seja a URL com um link para a imagem
    @Column(name = "imagem")
    private String imagem;

    public Produto(String nome, BigDecimal valor, String descricao, String imagem) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    @Deprecated
    protected Produto() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setId(Long id) {
        this.id = id;
}
