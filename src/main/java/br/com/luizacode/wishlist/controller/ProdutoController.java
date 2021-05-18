package controller;


import br.com.luizacode.wishlist.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


}
