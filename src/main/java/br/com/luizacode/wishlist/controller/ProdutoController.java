package br.com.luizacode.wishlist.controller;


import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    //adicionar produto ao banco de dados
    @ApiOperation(value = "Adicionar novo produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna o produto adicionado", response = Response.class),
            @ApiResponse(code = 401, message = "Voce não tem a permissão para realizar esse procedimento", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @PostMapping("/produtos")
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        try {
            return new ResponseEntity<>(produtoService.adicionarProduto(produto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //listar produto do banco de dados
    @ApiOperation(value = "Buscar todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna a lista de produtos", response = Response.class),
            @ApiResponse(code = 401, message = "Voce nao tem a permissão para realizar esse procedimento", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown", response = Response.class),
    })
    @GetMapping("/produtos")
    public  ResponseEntity<?> listarProdutos() {
        try {
            List<Produto> produtos = produtoService.listarProdutos();
            if(produtos == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(produtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
