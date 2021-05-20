package br.com.luizacode.wishlist.controller;


import br.com.luizacode.wishlist.entity.Cliente;
import br.com.luizacode.wishlist.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //ADICIONAR cliente no banco de dados
    @ApiOperation(value = "Adicionar novo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the registered client.", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request.", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown.", response = Response.class),
    })
    @PostMapping("/clientes")
    public Cliente addCliente(@RequestBody Cliente cliente) {
        return clienteService.adicionarCliente(cliente);
    }

    //BUSCAR cliente no banco de dados
    @ApiOperation(value = "Buscar cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the wanted client.", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request.", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown.", response = Response.class),
    })
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable Long id) {
        try {
            Optional<Cliente> clienteBuscado = clienteService.buscarCliente(id);
            return new ResponseEntity<>(clienteBuscado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //ATUALIZAR cliente no banco de dados
    @ApiOperation(value = "Atualizar dados de cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the client new informations.", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request.", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown.", response = Response.class),
    })
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteAtualizado = clienteService.atualizarCliente(cliente, id);
            return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

