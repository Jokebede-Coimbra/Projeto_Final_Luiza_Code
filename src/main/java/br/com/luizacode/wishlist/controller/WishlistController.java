package br.com.luizacode.wishlist.controller;

import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.entity.Wishlist;
import br.com.luizacode.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    private Object Wishlist;

    private Object WishlistProdutoRemovido;

    private Object Id;

    //CRIAR uma wishlist
    @PostMapping("/wishlist")
    public Wishlist adicionarWishlist(@RequestBody Wishlist wishlist) {
        //TODO criar DTO
        //TODO tratar quando o cliente não existe
        return wishlistService.criarWishlist(wishlist);
    }

    //ADICIONAR produtos na wishlist
    @PostMapping("/wishlist/{idWishlist}/adicionar")
    public ResponseEntity<?> adicionarProduto(@PathVariable Long idWishlist, @RequestBody Produto produto) {
        try {
            Wishlist wishlist = wishlistService.adicionarProduto(idWishlist, produto);
            return new ResponseEntity<>(wishlist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    //LISTAR todos os produtos da wishlist
    @GetMapping("/wishlist/{idWishlist}/produtos")
    public ResponseEntity<?> mostrarProdutos(@PathVariable Long idWishlist) {
        try {
            List<Produto> produtos = wishlistService.mostrarProduto(idWishlist);
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //BUSCAR produtos na wishlist
    @GetMapping ("/wishlist/{idWishlist}/produtos/{idProduto}")
    public ResponseEntity<?> buscarProdutosWishlist(@PathVariable Long idWishlist, @PathVariable Long idProduto) {
        try {
            Produto produtoWishlist = wishlistService.buscarProdutoWishlist(idWishlist, idProduto);
            if (produtoWishlist == null) {
                // produto existe, mas nao esta na wishlist
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                // produto existe e esta na wishlist
                return new ResponseEntity<>(produtoWishlist, HttpStatus.OK);
            }
        } catch (Exception e) {
            // Dois problemas possíveis na requisição
            // 1o) wishlist não existe
            // 2o) produto nao existe
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //REMOVER produto da wishlist
    @DeleteMapping(value = "wishlist/{idWishlist}/produtos/{idProduto}")
    public ResponseEntity<?> removerProdutoWishlist(@PathVariable Long idWishlist, @PathVariable Long idProduto) {
        try {
            Produto produtoRemovido = wishlistService.removerProdutoWishlist(idWishlist, idProduto);
            if (produtoRemovido == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                //produto removido com sucesso
                return new ResponseEntity<>(produtoRemovido, HttpStatus.OK);
            }
        } catch (Exception e) {
            // Dois problemas possíveis na requisição
            // 1o) wishlist não existe
            // 2o) produto nao existe
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
