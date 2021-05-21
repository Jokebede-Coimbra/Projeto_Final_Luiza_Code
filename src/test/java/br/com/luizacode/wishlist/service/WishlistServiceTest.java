package br.com.luizacode.wishlist.service;

import br.com.luizacode.wishlist.entity.Cliente;
import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.entity.Wishlist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class WishlistServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private WishlistService wishlistService;

    @Test
    public void criarWishlist() {
        Cliente cliente = criaCliente();
        Wishlist wishlist = new Wishlist();
        wishlist.setCliente(cliente);

        Wishlist wishlistCriada = wishlistService.criarWishlist(wishlist);
        assertThat(wishlistCriada).isNotNull();
        assertThat(wishlistCriada.getCliente().getId()).isEqualTo(cliente.getId());
    }

    @Test
    public void adicionarProdutoWishlist() {
        Cliente cliente = criaCliente();
        Wishlist wishlist = new Wishlist();
        wishlist.setCliente(cliente);

        Wishlist wishlistCriada = wishlistService.criarWishlist(wishlist);
        Produto produto = criaProduto();

        Wishlist wishlistComProduto = wishlistService.adicionarProduto(wishlistCriada.getId(), produto);
        assertThat(wishlistComProduto.getProdutos()).isNotEmpty();
        assertThat(wishlistComProduto.getProdutos()).contains(produto);
    }

    @Test
    public void removeProdutoWishlist() {
        Cliente cliente = criaCliente();
        Wishlist wishlist = new Wishlist();
        wishlist.setCliente(cliente);

        Wishlist wishlistCriada = wishlistService.criarWishlist(wishlist);
        Produto produto = criaProduto();

        // Adiciona produto na wishlist
        Wishlist wishlistPersistida = wishlistService.adicionarProduto(wishlistCriada.getId(), produto);
        assertThat(wishlistPersistida.getProdutos()).isNotEmpty();
        assertThat(wishlistPersistida.getProdutos()).contains(produto);

        // Remove produto da wishlist
        Produto produtoRemovido = wishlistService.removerProdutoWishlist(wishlistCriada.getId(), produto.getId());
        assertThat(produtoRemovido).isEqualTo(produto);
        assertThat(wishlistPersistida.getProdutos()).isEmpty();
    }

    private Cliente criaCliente() {
        Cliente cliente = new Cliente("Gabriela", "gabi@gabi.com", "123");
        return clienteService.adicionarCliente(cliente);
    }

    private Produto criaProduto() {
        Produto produto = new Produto("PlayStation 5", new BigDecimal("4699.00"), "Console", "url");
        return produtoService.adicionarProduto(produto);
    }
}
