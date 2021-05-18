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

    //criar uma wishlist (sem login do usuário)
    @PostMapping("/wishlist")
    public Wishlist adicionarWishlist(@RequestBody Wishlist wishlist) {
        return wishlistService.criarWishlist(wishlist);
    }

    //adicionar produtos
    @PostMapping("/wishlist/{id}/adicionar")
    public Wishlist adicionarProduto(@PathVariable long id, Produto produto) {
        return wishlistService.adicionarProduto(id, produto);
    }

    //listar todos os produtos da wishlist
    @GetMapping("/wishlist/{id}/produtos")
    public List<Produto> mostrarProdutos(@PathVariable long id) {
        return wishlistService.mostrarProduto(id);
    }

    //buscar produtos na wishlist
    @GetMapping ("/wishlist/{id}/buscar")
    public ResponseEntity<List<?>> buscarProdutos (@PathVariable Long id, Produto produto) {
        try {
            produto = wishlistService.buscarProduto(id, produto);
            if (produto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Object produtos = null;
                List <Produto> listaProdutos = ;
                return new ResponseEntity<> (listaProdutos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //atualizar lista para apagar produto
    @PutMapping(value = "wishlist/{id}")
    public ResponseEntity<?> apagarProdutos(@PathVariable Long id) {
        try {
            Wishlist wishlist = wishlistService.mostrarProduto(id);
            if (wishlist == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                List<Produto> wishlistWishlist = wishlist.getProdutos();
                for (Produto produto : wishlistWishlist) {
                    Object id_produto = new Object();
                    if (produto.getId().equals(id_produto)) {
                        wishlist.remove(produto);
                        wishlist.setWishlist(Wishlist);
                        return new ResponseEntity<>(WishlistProdutoRemovido, HttpStatus.OK);
                    }
                }

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    }

//a fazer:
//tratamento de erros
//verificar linhas 57 e 71
