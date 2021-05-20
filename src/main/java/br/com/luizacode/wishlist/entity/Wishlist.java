package br.com.luizacode.wishlist.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlist")
public class Wishlist implements Serializable {

    public static final long serialVersionUID = 1L;
    private static int TAMANHO_WISHLIST = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private Cliente cliente;

    //TODO limitar lista para até 20 produtos
    @OneToMany
    private List<Produto> produtos = new ArrayList<>(TAMANHO_WISHLIST);

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public boolean adicionarProduto(Produto produto) {
        if (produtos.size() < TAMANHO_WISHLIST) {
            return produtos.add(produto);
        }
        return false;
    }

    public void remove(Produto produto) {
    }

}
