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
        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
        //verifica se cliente existe
        if (clienteBuscado.isPresent()) {
            return clienteBuscado;
        }
        throw new IllegalArgumentException("Cliente com id " + id + " não existe.");
    }

    public Cliente atualizarCliente(Cliente cliente, Long id) {
        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
        //verifica se cliente existe
        if (clienteBuscado.isPresent()) {
            cliente.setId(clienteBuscado.get().getId());
            return clienteRepository.save(cliente);
        }
        throw new IllegalArgumentException("Cliente com id " + id + " não existe.");

    }
}
