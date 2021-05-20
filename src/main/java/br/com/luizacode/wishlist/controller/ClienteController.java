package br.com.luizacode.wishlist.controller;


import br.com.luizacode.wishlist.entity.Cliente;
import br.com.luizacode.wishlist.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // ADICIONAR CLIENTE NO BANCO DE DADOS //
    @ApiOperation(value = "Adicionar novo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the registered client.", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request.", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown.", response = Response.class),
    })
    @PostMapping("/clientes")
    public Cliente addCliente (@RequestBody Cliente cliente){
        return clienteService.adicionarCliente(cliente);
    }



    // PROCURA CLIENTE NO BANCO DE DADOS //
    @ApiOperation(value = "Buscar por cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the wanted client.", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request.", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown.", response = Response.class),
    })
    @GetMapping("/clientes/{id}")
    public Optional<Cliente> buscarCliente(@PathVariable Long id){
        return clienteService.buscarCliente(id);
    }



    // ATUALIZA INFORMACOES DE CLIENTE //
    @ApiOperation(value = "Atualizar dados de cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Returns the client new informations.", response = Response.class),
            @ApiResponse(code = 401, message = "You do not have permission to access this feature.", response = Response.class),
            @ApiResponse(code = 400, message = "Bad request.", response = Response.class),
            @ApiResponse(code = 500, message = "An exception was thrown.", response = Response.class),
    })
    @PutMapping("/clientes/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> clientePosBusca = clienteService.buscarCliente(id);
        if(clientePosBusca.isPresent()){
            cliente.setId(clientePosBusca.get().getId());
            return clienteService.atualizarCliente(cliente);
        }
        return null;
    }
}
