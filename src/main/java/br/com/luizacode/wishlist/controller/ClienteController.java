package br.com.luizacode.wishlist.controller;


import br.com.luizacode.wishlist.entity.Cliente;
import br.com.luizacode.wishlist.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //ADICIONAR cliente no banco de dados
    @PostMapping("/clientes")
    public Cliente addCliente (@RequestBody Cliente cliente){
        return clienteService.adicionarCliente(cliente);
    }

    //BUSCAR cliente no banco de dados
    @GetMapping("/clientes/{id}")
    public Optional<Cliente> buscarCliente(@PathVariable Long id){
        return clienteService.buscarCliente(id);
    }

    //ATUALIZAR CLIENTE NO BANCO DE DADOS
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
