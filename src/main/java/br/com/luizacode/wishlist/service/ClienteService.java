package br.com.luizacode.wishlist.service;

import br.com.luizacode.wishlist.entity.Cliente;
import br.com.luizacode.wishlist.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente adicionarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarCliente(Long id) {
        return clienteRepository.findById(id.longValue());
    }

    public Cliente atualizarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

}
