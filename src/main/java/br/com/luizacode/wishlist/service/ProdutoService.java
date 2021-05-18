package br.com.luizacode.wishlist.service;


import br.com.luizacode.wishlist.entity.Produto;
import br.com.luizacode.wishlist.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //adicionar produto ao banco de dados
    public Produto adicionarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    //listar produtos no banco de dados
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }
}
