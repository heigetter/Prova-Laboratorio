package card.game.serving_web_content;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
    private final ProdutosService produtosService;

    static List<String> idiomaPList;
    static List<String> franquiaPList;
    static List<String> tipoPList;

    static{
        franquiaPList = new ArrayList<>();
        franquiaPList.add("Yu-Gi-Oh!");
        franquiaPList.add("Pokémon");
        franquiaPList.add("Magic");
        franquiaPList.add("Digimon");
        franquiaPList.add("Outro");

        idiomaPList = new ArrayList<>();
        idiomaPList.add("Português");
        idiomaPList.add("Inglês");
        idiomaPList.add("Espanhol");
        idiomaPList.add("Alemão");
        idiomaPList.add("Italiano");
        idiomaPList.add("Francês");

        tipoPList = new ArrayList<String>();
        tipoPList.add("Deck");
        tipoPList.add("Booster");
        tipoPList.add("Caixa de Booster");
        tipoPList.add("Deck Box");
        tipoPList.add("Playmat");
        tipoPList.add("Shield/Sleeve");
        tipoPList.add("Outros");
    }

    @Autowired
    public ProdutosController(ProdutosService produtosService){this.produtosService = produtosService;}

    @GetMapping
    public String listar(Model model, @Param("idiomaPKey") String idiomaPKey, @Param("nomePKey") String nomePKey, @Param("tipoPKey") String tipoPKey, @Param("fornecedorPKey") String fornecedorPKey, @Param("franquiaPKey") String franquiaPKey,
                         @Param("sortie") String sortie){
        model.addAttribute("franquiaPList", franquiaPList);
        model.addAttribute("idiomaPList", idiomaPList);
        model.addAttribute("tipoPList", tipoPList);

        if(nomePKey != null){
            model.addAttribute("produtos", produtosService.acharNome(nomePKey));
            model.addAttribute("pesquisa", "true");
        } else if(fornecedorPKey != null){
            model.addAttribute("produtos", produtosService.acharFornecedor(fornecedorPKey));
            model.addAttribute("pesquisa", "true");
        } else if(franquiaPKey != null){
            model.addAttribute("produtos", produtosService.acharFranquia(franquiaPKey));
            model.addAttribute("pesquisa", "true");
        } else if(idiomaPKey != null){
            model.addAttribute("produtos", produtosService.acharIdioma(idiomaPKey));
            model.addAttribute("pesquisa", "true");
        } else if (tipoPKey != null) {
            model.addAttribute("produtos", produtosService.acharTipo(tipoPKey));
            model.addAttribute("pesquisa", "true");
        } else {
            if(sortie == null)
                model.addAttribute("produtos", produtosService.listarProduto());
            else if(sortie.equals("pasc"))
                model.addAttribute("produtos", produtosService.listarOrdemPASC());
            else if(sortie.equals("pdesc"))
                model.addAttribute("produtos", produtosService.listarOrdemPDESC());
            else if(sortie.equals("qasc"))
                model.addAttribute("produtos", produtosService.listarOrdemQASC());
            else if(sortie.equals("qdesc"))
                model.addAttribute("produtos", produtosService.listarOrdemQDESC());
        }

        return "produtos/listaP";
    }

    @GetMapping("/novo")
    public String abrirCadastro(Model model){
        model.addAttribute("produtos", new Produtos());
        model.addAttribute("tipoPList", tipoPList);
        model.addAttribute("franquiaPList", franquiaPList);
        model.addAttribute("idiomaPList", idiomaPList);
        return "produtos/formP";
    }

    @PostMapping
    public String cadastrar(@Valid @ModelAttribute Produtos produtos, BindingResult erros, RedirectAttributes ra){
        if(erros.hasErrors()){
            return "produtos/formP";
        } try {
            produtosService.cadastrarProduto(produtos);
            ra.addFlashAttribute("sucess", "produto cadastrado.");
            return "redirect:/produtos";
        } catch(DataIntegrityViolationException e){
            return "produtos/formP";
        }
    }

    @GetMapping("/editar/{idP}")
    public String abrirEdicao(@PathVariable Long idP, Model model, RedirectAttributes ra){
        try{
            model.addAttribute("franquiaPList", franquiaPList);
            model.addAttribute("idiomaPList", idiomaPList);
            model.addAttribute("tipoPList", tipoPList);
            model.addAttribute("produtos", produtosService.buscarProduto(idP));
            return "produtos/formP";
        } catch (RuntimeException e){
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/produtos";
        }
    }

    @PutMapping("/{idP}")
    public String atualizar(@PathVariable Long idP, @Valid @ModelAttribute Produtos produtos, BindingResult erros, RedirectAttributes ra){
        if(erros.hasErrors()){
            return "produtos/formP";
        }try{
            produtosService.atualizarProduto(idP, produtos);
            ra.addFlashAttribute("sucess", "Produto atualizado.");
            return "redirect:/produtos";
        } catch(DataIntegrityViolationException e){
            return "produtos/formP";
        } catch(RuntimeException e){
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/produtos";
        }
    }

    @DeleteMapping("{idP}")
    public String excluir(@PathVariable Long idP, RedirectAttributes ra){
        try{
            produtosService.excluirProduto(idP);
            ra.addFlashAttribute("sucess", "Produto atualizado.");
        } catch (RuntimeException e){
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/produtos";
    }
}
