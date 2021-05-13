package br.com.luizacode.wishlist.service;

import br.com.luizacode.wishlist.entity.Cliente;
import br.com.luizacode.wishlist.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    // Criar um cliente
    public Object adicionarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Mostrar um cliente
    public Object buscarCliente(long id) {
        return clienteRepository.findById(id);
    }

    // Atualizar dados do cliente
    public Object atualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
