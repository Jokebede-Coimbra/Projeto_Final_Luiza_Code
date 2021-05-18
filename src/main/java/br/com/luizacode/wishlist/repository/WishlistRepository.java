package br.com.luizacode.wishlist.repository;

import br.com.luizacode.wishlist.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {



    Wishlist save(Wishlist wishlist);
}
