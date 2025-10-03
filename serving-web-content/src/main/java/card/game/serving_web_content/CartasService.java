package card.game.serving_web_content;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartasService {
    private final CartasRepository cartasRepository;

    public CartasService(CartasRepository cartasRepository){this.cartasRepository = cartasRepository;}

    public List<Cartas> listarCartas(){return cartasRepository.findAll(Sort.by("nome").ascending());}

    public Cartas buscarCarta(Long id){
        return cartasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carta não encontrada com id: " + id));
    }

    public void cadastrarCarta(Cartas c) { cartasRepository.save(c);}

    public void atualizarCarta(Long id, Cartas cartaAtualizada){
        Cartas carta = cartasRepository.findById(id).orElse(null);

        if(carta != null){
            carta.setFranquia(cartaAtualizada.getFranquia());
            carta.setNome(cartaAtualizada.getNome());
            carta.setIdioma(cartaAtualizada.getIdioma());
            carta.setEdicao(cartaAtualizada.getEdicao());
            carta.setNumeroC(cartaAtualizada.getNumeroC());
            carta.setPreco(cartaAtualizada.getPreco());
            carta.setQuantidade(cartaAtualizada.getQuantidade());
            carta.setFornecedor(cartaAtualizada.getFornecedor());
            cartasRepository.save(carta);
        } else {
            throw  new RuntimeException("Carta não encontrada com id: " + id);
        }
    }

    public void excluirCarta(Long id) {
        if (!cartasRepository.existsById(id)) {
            throw new RuntimeException("Carta não encontrada com id: " + id);
        }
        cartasRepository.deleteById(id);
    }

    public List<Cartas> acharNome(@Param("nomeKey") String nomeKey){return cartasRepository.findByNome(nomeKey);}
    public List<Cartas> acharFranquia(@Param("franquiaKey") String franquiaKey){return cartasRepository.findByFranquia(franquiaKey);}
    public List<Cartas> acharEdicao(@Param("edicaoKey") String edicaoKey){return cartasRepository.findByEdicao(edicaoKey);}
    public List<Cartas> acharNumeroC(@Param("numeroCKey") String numeroCKey){return cartasRepository.findByNumeroC(numeroCKey);}
    public List<Cartas> acharFornecedor(@Param("fornecedorKey") String fornecedorKey){return cartasRepository.findByFornecedor(fornecedorKey);}
    public List<Cartas> acharIdioma(@Param("idiomaKey") String idiomaKey){return cartasRepository.findByIdioma(idiomaKey);}

    public List<Cartas> listarOrdemPASC(){return cartasRepository.findAll(Sort.by("preco").ascending());}
    public List<Cartas> listarOrdemPDESC(){return cartasRepository.findAll(Sort.by("preco").descending());}
    public List<Cartas> listarOrdemQASC(){return cartasRepository.findAll(Sort.by("quantidade").ascending());}
    public List<Cartas> listarOrdemQDESC(){return cartasRepository.findAll(Sort.by("quantidade").descending());}

}
