package br.com.luizacode.wishlist;


import br.com.luizacode.wishlist.entity.Cliente;
import br.com.luizacode.wishlist.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ClienteControllerTest {

    @Autowired
    ClienteService clienteService;

    @Test
    public void adicionarClienteNoBanco() {
        Cliente cliente = new Cliente("Gabriela","gabi@gabi.com", "123" );

        Cliente clienteSalvo = clienteService.adicionarCliente(cliente);

        assertThat(clienteSalvo).isNotNull();
    }

    @Test
    public void buscarClienteNoBancoPorId() {

        Cliente cliente = new Cliente("Gabriela","gabi@gabi.com", "123" );

        Cliente clienteSalvo = clienteService.adicionarCliente(cliente);

        assertThat(clienteSalvo.getId()).isEqualTo(2L);
    }

    @Test
    public void atualizarDadosCliente() {

        Cliente cliente = new Cliente("Gabriela","gabi@gabi.com", "123" );


    }

}
