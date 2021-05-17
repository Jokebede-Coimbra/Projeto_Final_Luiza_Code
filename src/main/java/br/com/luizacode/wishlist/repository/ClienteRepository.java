package br.com.luizacode.wishlist.repository;

import br.com.luizacode.wishlist.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findById(long id);

    Cliente save(Cliente cliente);

    long count();

}
