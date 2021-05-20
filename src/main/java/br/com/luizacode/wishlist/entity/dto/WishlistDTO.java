package br.com.luizacode.wishlist.entity.dto;

import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.entity.Wishlist;

import java.util.List;

public class WishlistDTO {

    private Long id;

    private Long idCliente;

    private String nomeCliente;

    private List<Produto> produtos;

    public WishlistDTO(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.idCliente = wishlist.getCliente().getId();
        this.nomeCliente = wishlist.getCliente().getNome();
        this.produtos = wishlist.getProdutos();
    }

    public Long getId() {
        return id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public static WishlistDTO converter(Wishlist wishlist) {
        return new WishlistDTO(wishlist);
    }

    public int getTamanho() {
        return produtos.size();
    }

}
