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

    //criar uma wishlist (sem login do usuário)
    public Wishlist criarWishlist(Wishlist wishlist) {
        Cliente clienteWishlist = wishlist.getCliente();
        Optional<Cliente> clienteBuscado = clienteRepository.findById(clienteWishlist.getId());
        if (clienteBuscado.isPresent()) {
            wishlistRepository.save(wishlist);
            return wishlist;
        }
        return null;
    }

    //adicionar produtos
    public Wishlist adicionarProduto(Long id, Produto produto) {
        Optional<Wishlist> wishlistBuscado = wishlistRepository.findById(id);
        if (wishlistBuscado.isPresent()) {
            Wishlist wishlist = wishlistBuscado.get();
            wishlist.adicionarProduto(produto);
            return wishlistRepository.save(wishlist);
        }
        return null;
    }

    //listar todos os produtos da wishlist
    public List<Produto> mostrarProduto(Long id) {
        //verificar se a wishlist existe
        Optional<Wishlist> wishlistBuscada = wishlistRepository.findById(id);
        if (wishlistBuscada.isPresent()) {
            Wishlist wishlist = wishlistBuscada.get();
            return wishlist.getProdutos();
        }
        return null;
    }

    //buscar produtos na wishlist
    public Produto buscarProduto(Long id, Produto produtoBuscado) {
        //verificar se a wishlist existe
        Optional<Wishlist> wishlistBuscada = wishlistRepository.findById(id);
        if (wishlistBuscada.isPresent()) {
            Wishlist wishlist = wishlistBuscada.get();
            List<Produto> produtosWishlist = wishlist.getProdutos();
            //buscar produtos dentro da wishlist
            // para cada produto p em produtos
            for (Produto produtoWishlist : produtosWishlist) {
                // verifica se o nome do produto p eh igual ao nome do produto recebido
                String nomeDoProdutoWishlist = produtoWishlist.getNome();
                String nomeDoProdutoBuscado = produtoWishlist.getNome();
                if (nomeDoProdutoWishlist.equalsIgnoreCase(nomeDoProdutoBuscado)) {
                    return produtoWishlist;
                }
            }
        } else {
            return null;
        }
        return null;
        //remover produto da wishlist

    }

}