package br.com.luizacode.wishlist.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long ID;

    @Column(name = "nome")
    @NotNull
    private String nomeProduto;

    @Column(name = "valor")
    @NotNull
    private BigDecimal valor;

    @Column(name = "descricao")
    @NotNull
    private String descricao;


    // IMAGEM JPEG? precisa de coluna? //
    @Column(name = "imagem")
    @NotNull
    private String imagem;
}
