package br.com.luizacode.wishlist.controller;

import br.com.luizacode.wishlist.entity.Cliente;
import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.entity.Wishlist;
import br.com.luizacode.wishlist.service.ClienteService;
import br.com.luizacode.wishlist.service.WishlistService;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.nio.file.Path;
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

    //mostrar todos os produtos
    @GetMapping("/wishlist/{id}/produtos")
    public List<Produto> mostrarProdutos(@PathVariable long id) {
        return wishlistService.mostrarProdutos(id);
    }

    //buscar produtos
    @GetMapping ("/wishlist/{id}/buscar")
    public ResponseEntity<List<?>> buscarProdutos (@PathVariable long id, Produto produto) {
        try {
            produto = produto.buscarProduto(Id);
            if (produto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                Object produtos;
                List <Produto> listaProdutos = produtos.getProdutoList();
                return new ResponseEntity<> (listaProdutos, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //atualizar lista para apagar produto
    @PutMapping(value = "wishlist")
    public ResponseEntity<?> apagarProdutos(@PathVariable long id) {
        try {

            Wishlist wishlist = wishlistService.mostrarProdutos(Long id); //erro?
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
