package br.com.luizacode.wishlist.service;

import br.com.luizacode.wishlist.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ClienteServiceTest {

    @Autowired
    ClienteService clienteService;

    @Test
    public void adicionarClienteNoBanco() {
        Cliente cliente = new Cliente("Gabriela","gabi@gabi.com", "123" );

        Cliente clienteSalvo = clienteService.adicionarCliente(cliente);

        assertThat(clienteSalvo).isNotNull();
        assertThat(clienteSalvo.getId()).isNotNull();
        assertThat(cliente.getNome()).isEqualTo(clienteSalvo.getNome());
        assertThat(cliente.getEmail()).isEqualTo(clienteSalvo.getEmail());
        assertThat(cliente.getSenha()).isEqualTo(clienteSalvo.getSenha());
    }

    @Test
    public void buscarClienteNoBancoPorId() {

        Cliente cliente = new Cliente("Gabriela","gabi@gabi.com", "123" );

        Cliente clienteSalvo = clienteService.adicionarCliente(cliente);
        Optional<Cliente> clienteBuscado = clienteService.buscarCliente(clienteSalvo.getId());

        assertThat(clienteBuscado.isPresent()).isTrue();
        assertThat(clienteBuscado.get().getId()).isEqualTo(clienteSalvo.getId());
    }

    @Test
    public void atualizarDadosCliente() {

        Cliente cliente = new Cliente("Gabriela","gabi@gabi.com", "123" );
        Cliente clienteSalvo = clienteService.adicionarCliente(cliente);

        clienteSalvo.setNome("Novo nome");
        clienteSalvo.setEmail("novo@email.com");
        clienteSalvo.setSenha("Nova senha");
        Cliente clienteAtualizado = clienteService.atualizarCliente(clienteSalvo, clienteSalvo.getId());

        assertThat(clienteAtualizado.getId()).isEqualTo(clienteSalvo.getId());
        assertThat(clienteAtualizado.getNome()).isEqualTo(clienteSalvo.getNome());
        assertThat(clienteAtualizado.getEmail()).isEqualTo(clienteSalvo.getEmail());
        assertThat(clienteAtualizado.getSenha()).isEqualTo(clienteSalvo.getSenha());

    }

}