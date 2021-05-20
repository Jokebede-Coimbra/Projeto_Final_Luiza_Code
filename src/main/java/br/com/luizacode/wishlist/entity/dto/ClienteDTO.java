package br.com.luizacode.wishlist.entity.dto;

import br.com.luizacode.wishlist.entity.Cliente;

public class ClienteDTO {

    private Long id;

    private String nome;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static ClienteDTO converter(Cliente cliente) {
        return new ClienteDTO(cliente);
    }
}
