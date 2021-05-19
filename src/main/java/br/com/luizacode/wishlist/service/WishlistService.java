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
    public Wishlist adicionarProduto(Long id, Produto produto) {
        Wishlist wishlist = buscarWishlist(id);

        //TODO verificar se o produto existe
        wishlist.adicionarProduto(produto);
        return wishlistRepository.save(wishlist);
    }

    //listar todos os produtos da wishlist
    public List<Produto> mostrarProduto(Long id) {
        Wishlist wishlist = buscarWishlist(id);
        return wishlist.getProdutos();
    }

    //buscar produtos na wishlist
    public Produto buscarProduto(Long idWishlist, Long idProduto) {
        Wishlist wishlist = buscarWishlist(idWishlist);

        // busca o produto no banco
        Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);

        // verifica se o produto existe
        if (produtoBuscado.isPresent()) {

            // só buscamos o produto na wishlist se ele existe no banco
            List<Produto> produtosWishlist = wishlist.getProdutos();

            // percorre todos os produtos dentro da wishlist encontrada
            // para cada um deles:
            for (Produto produtoWishlist : produtosWishlist) {
                // verifica se o id do produto da wishlist é igual ao id do produto buscado
                Long idProdutoWishlist = produtoWishlist.getId();
                if (idProdutoWishlist.equals(idProduto)) {
                    return produtoWishlist;
                }
            }
            // produto buscado não está na wishlist
            return null;
        }
        throw new IllegalArgumentException("Produto com id " + idProduto + " não existe.");
    }

    public Produto removerProduto(Long idWishlist, Long idProduto) {
        Wishlist wishlist = buscarWishlist(idWishlist);

        // busca o produto no banco
        Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);

        // verifica se o produto existe
        if (produtoBuscado.isPresent()) {

            // só buscamos o produto na wishlist se ele existe no banco

            List<Produto> produtosWishlist = wishlist.getProdutos();

            // percorre todos os produtos dentro da wishlist encontrada
            // para cada um deles:
            for (Produto produtoWishlist : produtosWishlist) {
                // verifica se o id do produto da wishlist é igual ao id do produto buscado
                Long idProdutoWishlist = produtoWishlist.getId();
                if (idProdutoWishlist.equals(idProduto)) {
                    wishlist.getProdutos().remove(produtoWishlist);
                    wishlistRepository.save(wishlist);
                    return produtoWishlist;
                }
            }
            // produto buscado não está na wishlist
            return null;
        }
        throw new IllegalArgumentException("Produto com id " + idProduto + " não existe.");
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
