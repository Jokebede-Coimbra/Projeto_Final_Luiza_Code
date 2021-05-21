//package br.com.luizacode.wishlist;
//
//
//import br.com.luizacode.wishlist.entity.Cliente;
//import br.com.luizacode.wishlist.entity.Wishlist;
//import br.com.luizacode.wishlist.service.WishlistService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Transactional
//public class WishlistControllerTest {
//
//
//    @Autowired
//    WishlistService wishlistService;
//
//
//
//    @Test
//    public void criarWishlist() {
//
//        Cliente cliente = new Cliente("Gabriela","gabi@gabi.com", "123" );
//
//        wishlistControllerTest.add(cliente);
//
//        Wishlist wishlist = new Wishlist();
//
//        wishlist.setId(1L);
//
//        Wishlist novaWishlist = wishlistService.criarWishlist(wishlist);
//
//        assertThat(novaWishlist).isNotNull();
//    }
//
////    @Test
////    public void adicionarProdutoNaWishlist() {
////        Wishlist wishlist = new Wishlist();
////
////        Wishlist produtoAdicionadoWishlist = wishlistService.adicionarProduto(1, "");
////    }
//}
