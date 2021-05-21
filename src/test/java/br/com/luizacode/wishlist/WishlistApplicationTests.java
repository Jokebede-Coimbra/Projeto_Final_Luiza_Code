package br.com.luizacode.wishlist;

import br.com.luizacode.wishlist.service.ClienteService;
import br.com.luizacode.wishlist.service.ProdutoService;
import br.com.luizacode.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
class WishlistApplicationTests {

	@Autowired
	private WishlistService wishlistService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProdutoService produtoService;

	@Test
	void contextLoads() {
	}

}
