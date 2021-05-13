package br.com.luizacode.wishlist.repository;

import br.com.luizacode.wishlist.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findById(long id);
}
