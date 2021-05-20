package br.com.luizacode.wishlist.controller;

import br.com.luizacode.wishlist.entity.dto.WishlistDTO;
import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.entity.Wishlist;
import br.com.luizacode.wishlist.service.WishlistService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
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

    //TODO adicionar anotações do Swagger

    //CRIAR uma wishlist
    @ApiOperation(value = "Adicionar uma Wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Wishlist criada", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 404, message = "Not Found", response = Response.class),
            @ApiResponse(code = 500, message = "Erro no servidor", response = Response.class),
    })
    @PostMapping("/wishlist")
    public ResponseEntity<?> adicionarWishlist(@RequestBody Wishlist wishlist) {
        try {
            Wishlist wishlistCriada = wishlistService.criarWishlist(wishlist);
            return new ResponseEntity<>(WishlistDTO.converter(wishlist),HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
      
    }

    //ADICIONAR produtos na wishlist
    @ApiOperation(value = "Adicionar um produto em uma Wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto adicionado", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 404, message = "O Produto e/ou a Wishlist não existe", response = Response.class),
            @ApiResponse(code = 500, message = "Erro no servidor", response = Response.class),
    })
    @PostMapping("/wishlist/{idWishlist}/adicionar")
    public ResponseEntity<?> adicionarProduto(@PathVariable Long idWishlist, @RequestBody Produto produto) {
        try {
            Wishlist wishlist = wishlistService.adicionarProduto(idWishlist, produto);
            return new ResponseEntity<>(WishlistDTO.converter(wishlist), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
      
    }

    //LISTAR todos os produtos da wishlist
    @ApiOperation(value = "Listar produtos em uma Wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produtos desta Wishlist", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 404, message = "O Produto e/ou a Wishlist não existe", response = Response.class),
            @ApiResponse(code = 500, message = "Erro no servidor", response = Response.class),
    })
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
    @ApiOperation(value = "Buscar um produto em uma Wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto encontrado", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 404, message = "O Produto e/ou a Wishlist não existe", response = Response.class),
            @ApiResponse(code = 500, message = "Erro no servidor", response = Response.class),
    })
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
    @ApiOperation(value = "Remover um produto em uma Wishlist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto removido da Wishlist", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 404, message = "O Produto e/ou a Wishlist não existe", response = Response.class),
            @ApiResponse(code = 500, message = "Erro no servidor", response = Response.class),
    })
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
