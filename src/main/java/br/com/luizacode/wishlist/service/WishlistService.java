package br.com.luizacode.wishlist.service;
import br.com.luizacode.wishlist.entity.Cliente;
import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.entity.Wishlist;
import br.com.luizacode.wishlist.repository.ClienteRepository;
import br.com.luizacode.wishlist.repository.ProdutoRepository;
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

    @Autowired
    private ProdutoRepository produtoRepository;

    //criar uma wishlist (sem login do usuário)
    public Wishlist criarWishlist(Wishlist wishlist) {
        Cliente clienteWishlist = wishlist.getCliente();
        Optional<Cliente> clienteBuscado = clienteRepository.findById(clienteWishlist.getId());
        if (clienteBuscado.isPresent()) {
            wishlistRepository.save(wishlist);
            return wishlist;
        }
        //TODO cliente da wishlist não existe, retornar erro
        return null;
    }

    //adicionar produtos
    public Wishlist adicionarProduto(Long idWishlist, Produto produto) {
        // verificar se a wishlist existe
        Wishlist wishlist = buscarWishlist(idWishlist);

        // só adicionamos o produto na wishlist se ele existe no banco
        Produto produtoBuscado = buscarProduto(produto.getId());

        wishlist.adicionarProduto(produtoBuscado);
        return wishlistRepository.save(wishlist);
    }

    //listar todos os produtos da wishlist
    public List<Produto> mostrarProduto(Long id) {
        Wishlist wishlist = buscarWishlist(id);
        return wishlist.getProdutos();
    }

    //buscar produtos na wishlist
    public Produto buscarProdutoWishlist(Long idWishlist, Long idProduto) {
        // verificar se a wishlist existe
        Wishlist wishlist = buscarWishlist(idWishlist);

        // só buscamos o produto na wishlist se ele existe no banco
        Produto produto = buscarProduto(idProduto);

        // só buscamos o produto na wishlist se ele existe no banco
        List<Produto> produtosWishlist = wishlist.getProdutos();

        // percorre todos os produtos dentro da wishlist encontrada
        // para cada um deles:
        for (Produto produtoWishlist : produtosWishlist) {
            // verifica se o id do produto da wishlist é igual ao id do produto buscado
            Long idProdutoWishlist = produtoWishlist.getId();
            if (idProdutoWishlist.equals(produto.getId())) {
                return produtoWishlist;
            }
        }
        // produto buscado não está na wishlist
        return null;
    }

    public Produto removerProdutoWishlist(Long idWishlist, Long idProduto) {
        // verificar se a wishlist existe
        Wishlist wishlist = buscarWishlist(idWishlist);

        // só removemos o produto na wishlist se ele existe no banco
        Produto produto = buscarProduto(idProduto);

        List<Produto> produtosWishlist = wishlist.getProdutos();

        // percorre todos os produtos dentro da wishlist encontrada
        // para cada um deles:
        for (Produto produtoWishlist : produtosWishlist) {
            // verifica se o id do produto da wishlist é igual ao id do produto buscado
            Long idProdutoWishlist = produtoWishlist.getId();
            if (idProdutoWishlist.equals(produto.getId())) {
                wishlist.getProdutos().remove(produtoWishlist);
                wishlistRepository.save(wishlist);
                return produtoWishlist;
            }
        }
        // produto buscado não está na wishlist
        return null;
    }

    private Produto buscarProduto(Long id){
        Optional<Produto> produtoBuscado = produtoRepository.findById(id);
        if (produtoBuscado.isPresent()) {
            return produtoBuscado.get();
        }
        throw new IllegalArgumentException("Produto com id " + id + " não existe.");
    }

    private Wishlist buscarWishlist(Long id) {
        // busca a wishlist no banco
        Optional<Wishlist> wishlistBuscada = wishlistRepository.findById(id);

        // verifica se a wishlist existe
        if (wishlistBuscada.isPresent()) {
            return wishlistBuscada.get();
        }
        // wishlist buscada não existe
        throw new IllegalArgumentException("Wishlist com id " + id + " não existe.");
    }
}
