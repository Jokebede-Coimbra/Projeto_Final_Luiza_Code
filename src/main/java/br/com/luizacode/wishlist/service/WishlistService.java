package br.com.luizacode.wishlist.service;
import br.com.luizacode.wishlist.entity.Cliente;
import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.entity.Wishlist;
import br.com.luizacode.wishlist.repository.ClienteRepository;
import br.com.luizacode.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    //Criar wishlist
    public Wishlist criarWishlist(Wishlist wishlist) {
        Cliente clienteWishlist = wishlist.getCliente();
        Optional<Cliente> clienteBuscado = clienteRepository.findById(clienteWishlist.getId());
        if (clienteBuscado.isPresent()) {
            wishlistRepository.save(wishlist);
            return wishlist;
        }
        return null;
    }


        //Adicionar produto
    public Wishlist adicionarProduto(Long id, Produto produto){
        Optional<Wishlist> wishlistBuscado = wishlistRepository.findById(id);
        if (wishlistBuscado.isPresent()){
            Wishlist wishlist = wishlistBuscado.get();
            wishlist.adicionarProduto(produto);
            return wishlistRepository.save(wishlist);
        }
        return null;
    }

    //Remover produto



    //Buscar produtos
    public List<Produto> mostrarProdutos(Long id) {
        Optional<Wishlist> wishlistBuscada = wishlistRepository.findById(id);
        if (wishlistBuscada.isPresent()) {
            //pega wishlista retornada pelo banco de dados
            Wishlist wishlist = wishlistBuscada.get();
            return wishlist.getProdutos();
        }
        //a wishlist passada não existe no banco de dados
        //retornar um erro 400 (Bad Request)
        return null;
    }


    //Mostrar Produtos da Wishlist
    public List<Produto> mostrarProduto(long id){
        Optional<Wishlist> wishlistMostrada = wishlistRepository.findById(id);
        if (wishlistMostrada.isPresent()){
            Wishlist wishlist = wishlistMostrada.get();
            return wishlist.getProdutos();
        }
        return null;
    }

}
