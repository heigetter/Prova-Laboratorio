package card.game.serving_web_content;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {
    private final ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository){this.produtosRepository = produtosRepository;}

    public List<Produtos> listarProduto(){return produtosRepository.findAll(Sort.by("precoP").ascending());}

    public Produtos buscarProduto(Long id){
        return produtosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));

    }
    public void cadastrarProduto(Produtos p){ produtosRepository.save(p);}

    public void atualizarProduto(Long id, Produtos produtoAtualizada){
        Produtos produto = produtosRepository.findById(id).orElse(null);

        if(produto != null){
            produto.setFranquiaP(produtoAtualizada.getFranquiaP());
            produto.setNomeP(produtoAtualizada.getNomeP());
            produto.setIdiomaP(produtoAtualizada.getIdiomaP());
            produto.setTipoP(produtoAtualizada.getTipoP());
            produto.setPrecoP(produtoAtualizada.getPrecoP());
            produto.setQuantidadeP(produtoAtualizada.getQuantidadeP());
            produto.setFornecedorP(produtoAtualizada.getFornecedorP());
            produtosRepository.save(produto);
        } else {
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
    }

    public void excluirProduto(Long id){
        if (!produtosRepository.existsById(id)){
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
        produtosRepository.deleteById(id);
    }

    public List<Produtos> acharNome(@Param("nomeKey") String nomeKey){return produtosRepository.findByNomeP(nomeKey);}
    public List<Produtos> acharFranquia(@Param("franquiaKey") String franquiaKey){return produtosRepository.findByFranquiaP(franquiaKey);}
    public List<Produtos> acharTipo(@Param("tipoKey") String tipoKey){return produtosRepository.findByTipoP(tipoKey);}
    public List<Produtos> acharFornecedor(@Param("fornecedorKey") String fornecedorKey){return produtosRepository.findByFornecedorP(fornecedorKey);}
    public List<Produtos> acharIdioma(@Param("idiomaKey") String idiomaKey){return produtosRepository.findByIdiomaP(idiomaKey);}

    public List<Produtos> listarOrdemPASC(){return produtosRepository.findAll(Sort.by("precoP").ascending());}
    public List<Produtos> listarOrdemPDESC(){return produtosRepository.findAll(Sort.by("precoP").descending());}
    public List<Produtos> listarOrdemQASC(){return produtosRepository.findAll(Sort.by("quantidadeP").ascending());}
    public List<Produtos> listarOrdemQDESC(){return produtosRepository.findAll(Sort.by("quantidadeP").descending());}
}
