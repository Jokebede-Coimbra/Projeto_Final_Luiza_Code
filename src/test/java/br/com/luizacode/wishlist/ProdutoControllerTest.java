package br.com.luizacode.wishlist;


import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;


@SpringBootTest
@Transactional
public class ProdutoControllerTest {

    @Autowired
    ProdutoService produtoService;

    @Test
    public void adicionarProdutoNoBanco() {

        BigDecimal valor = new BigDecimal(2.0);
        Produto produto = new Produto("Caneta", valor, "Caneta preta", "nada");

        Produto produtoAdicionado = produtoService.adicionarProduto(produto);

        assertThat(produtoAdicionado).isNotNull();
    }

//    @Test
//    public void listaProdutosDoBanco() {
//
//        BigDecimal valor = new BigDecimal(2.0);
//        Produto produto = new Produto("Caneta", valor, "Caneta preta", "nada");
//        Produto produto1 = new Produto("Garrafa", valor, "garrafa termica", "nada");
//        Produto produto2 = new Produto("Livro", valor, "livro infantil", "nada");
//
//        Produto listaDeProdutos = produtoService.listarProdutos();
//
//        assertThat(listaDeProdutos).asList();
//    }
}
