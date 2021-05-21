package br.com.luizacode.wishlist;


import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ProdutoControllerTest {

    @Autowired
    ProdutoService produtoService;

    @Test
    public void adicionarProdutoNoBanco() {
        Produto produto = new Produto("Caneta", new BigDecimal("2.00"), "Caneta preta", "nada");

        Produto produtoAdicionado = produtoService.adicionarProduto(produto);

        assertThat(produtoAdicionado).isNotNull();
        assertThat(produtoAdicionado.getNome()).isEqualTo(produtoAdicionado.getNome());
        assertThat(produtoAdicionado.getDescricao()).isEqualTo(produtoAdicionado.getDescricao());
        assertThat(produtoAdicionado.getImagem()).isEqualTo(produtoAdicionado.getImagem());
        assertThat(produtoAdicionado.getValor()).isEqualTo(produtoAdicionado.getValor());
    }

    @Test
    public void listaProdutosDoBanco() {
        Produto produto1 = new Produto("Caneta", new BigDecimal("1.10"), "Caneta preta", "url1");
        Produto produto2 = new Produto("Garrafa", new BigDecimal("2.02"), "garrafa termica", "url2");
        Produto produto3 = new Produto("Livro", new BigDecimal("3.33"), "livro infantil", "url3");

        produtoService.adicionarProduto(produto1);
        produtoService.adicionarProduto(produto2);
        produtoService.adicionarProduto(produto3);
        List<Produto> produtos = produtoService.listarProdutos();

        assertThat(produtos).isNotEmpty();
        assertThat(produtos).contains(produto1);
        assertThat(produtos).contains(produto2);
        assertThat(produtos).contains(produto3);
    }
}
