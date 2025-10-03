package card.game.serving_web_content;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cartas")
public class CartasController {
    private final CartasService cartasService;

    static List<String> idiomaList;
    static List<String> franquiaList;

    static{
        franquiaList = new ArrayList<>();
        franquiaList.add("Yu-Gi-Oh!");
        franquiaList.add("Pokémon");
        franquiaList.add("Magic");
        franquiaList.add("Digimon");
        franquiaList.add("Outro");

        idiomaList = new ArrayList<>();
        idiomaList.add("Português");
        idiomaList.add("Inglês");
        idiomaList.add("Espanhol");
        idiomaList.add("Alemão");
        idiomaList.add("Italiano");
        idiomaList.add("Francês");
    }

    @Autowired
    public CartasController(CartasService cartasService) {this.cartasService = cartasService;}

    @GetMapping
    public String listar(Model model,@Param("idiomaKey") String idiomaKey, @Param("nomeKey") String nomeKey, @Param("edicaoKey") String edicaoKey, @Param("numeroCKey") String numeroCKey, @Param("fornecedorKey") String fornecedorKey, @Param("franquiaKey") String franquiaKey,
                         @Param("sortie") String sortie) {
        model.addAttribute("franquiaList", franquiaList);
        model.addAttribute("idiomaList", idiomaList);

        if(nomeKey != null){
            model.addAttribute("cartas", cartasService.acharNome(nomeKey));
            model.addAttribute("pesquisa", "true");
        } else if(edicaoKey != null){
            model.addAttribute("cartas", cartasService.acharEdicao(edicaoKey));
            model.addAttribute("pesquisa", "true");
        } else if(numeroCKey != null) {
            model.addAttribute("cartas", cartasService.acharNumeroC(numeroCKey));
            model.addAttribute("pesquisa", "true");
        } else if (fornecedorKey != null) {
            model.addAttribute("cartas", cartasService.acharFornecedor(fornecedorKey));
            model.addAttribute("pesquisa", "true");
        } else if (franquiaKey != null) {
            model.addAttribute("cartas", cartasService.acharFranquia(franquiaKey));
            model.addAttribute("pesquisa", "true");
        } else if (idiomaKey != null) {
            model.addAttribute("cartas", cartasService.acharIdioma(idiomaKey));
            model.addAttribute("pesquisa", "true");
        } else {
            if(sortie == null)
                model.addAttribute("cartas", cartasService.listarCartas());
            else if(sortie.equals("pasc"))
                model.addAttribute("cartas", cartasService.listarOrdemPASC());
            else if(sortie.equals("pdesc"))
                model.addAttribute("cartas", cartasService.listarOrdemPDESC());
            else if(sortie.equals("qasc"))
                model.addAttribute("cartas", cartasService.listarOrdemQASC());
            else if(sortie.equals("qdesc"))
                model.addAttribute("cartas", cartasService.listarOrdemQDESC());
        }

        return "cartas/lista_bootstrap";

    }

    @GetMapping("/novo")
    public String abrirCadastro(Model model){
        model.addAttribute("cartas", new Cartas());
        model.addAttribute("franquiaList", franquiaList);
        model.addAttribute("idiomaList", idiomaList);
        return "cartas/form_bootstrap";
    }

    @PostMapping
    public String cadastrar(@Valid @ModelAttribute Cartas carta,
                            BindingResult erros,
                            RedirectAttributes ra){
        if(erros.hasErrors()){
            return "cartas/form_bootstrap";
        }
        try {
            cartasService.cadastrarCarta(carta);
            ra.addFlashAttribute("sucess", "Carta cadastrada." );
            return "redirect:/cartas";
        } catch (DataIntegrityViolationException e){
            return "cartas/form_bootstrap";
        }
    }

    @GetMapping("/editar/{id}")
    public String abrirEdicao(@PathVariable Long id,
                              Model model,
                              RedirectAttributes ra){
       try{
           model.addAttribute("cartas", cartasService.buscarCarta(id));
           model.addAttribute("franquiaList", franquiaList);
           model.addAttribute("idiomaList", idiomaList);
           return "cartas/form_bootstrap";
       } catch (RuntimeException e){
           ra.addFlashAttribute("error", e.getMessage());
           return "redirect:/cartas";
       }
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute Cartas carta,
                            BindingResult erros,
                            RedirectAttributes ra){
        if(erros.hasErrors()){
            return "cartas/form_bootstrap";
        } try {
            cartasService.atualizarCarta(id, carta);
            ra.addFlashAttribute("sucess", "Carta atualizada.");
            return "redirect:/cartas";
        } catch (DataIntegrityViolationException e){
            return "cartas/form_bootstrap";
        } catch(RuntimeException e){
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/cartas";
        }
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Long id,
                          RedirectAttributes ra){
        try{
            cartasService.excluirCarta(id);
            ra.addFlashAttribute("success", "carta excluída.");
        } catch(RuntimeException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/cartas";
    }
}
