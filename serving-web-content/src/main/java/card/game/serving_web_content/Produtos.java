package card.game.serving_web_content;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "produtos")
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;

    @NotBlank(message = "A franquia associada a carta não pode estar em branco.")
    @Size(max = 50, message =  "A franquia associada a carta deve ter no máximo 50 caracteres.")
    @Column(nullable = false, length = 50)
    private String franquiaP;

    @NotBlank(message = "O none não pode estar em branco.")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
    @Column(nullable = false, length = 150)
    private String nomeP;

    @NotBlank(message = "O idioma da carta não pode estar em branco.")
    @Size(max = 50, message = "O idioma da carta deve ter no máximo 50 caracteres.")
    @Column(nullable = false, length = 50)
    private String idiomaP;

    @NotBlank(message = "O tipo do produto não pode estar em branco.")
    @Size(max = 50, message = "O tipo do produto deve ter no máximo 50 caracteres.")
    @Column(nullable = false, length = 50)
    private String tipoP;

    @NotNull(message = "O preço não pode estar em branco.")
    @DecimalMin(value = "0.01", message = "O Preço deve ter valor maior que 0.00.")
    @DecimalMax(value = "100000000.01", message = "O Preço deve ter valor maximo de 100.000.000,00")
    @Column(nullable = false)
    private Double precoP;

    @NotNull(message = "A quantidade de cartas não pode estar em branco.")
    @Min(value = 1, message = "A quantidade de cartas deve ter no minimo 1.")
    @Max(value = 10000, message= "A quantidade de cartas deve ter no maximo 10.000.")
    @Column(nullable = false)
    private Integer quantidadeP;

    @NotBlank(message = "O fornecedor não pode estar em branco.")
    @Size(max = 100, message = "O nome do fornecedor deve ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String fornecedorP;

    public Long getIdP(){return idP;}
    public void setIdP(Long idP){this.idP = idP;}
    public String getFranquiaP(){return franquiaP;}
    public void setFranquiaP(String franquiaP){this.franquiaP = franquiaP;}
    public String getNomeP(){return nomeP;}
    public void setNomeP(String nomeP){this.nomeP = nomeP;}
    public String getIdiomaP(){return  idiomaP;}
    public void setIdiomaP(String idiomaP){this.idiomaP = idiomaP;}
    public String getTipoP(){return  tipoP;}
    public void setTipoP(String tipoP){this.tipoP = tipoP;}
    public Double getPrecoP(){return precoP;}
    public void setPrecoP(Double precoP){this.precoP = precoP;}
    public Integer getQuantidadeP(){return quantidadeP;}
    public void setQuantidadeP(Integer quantidadeP){this.quantidadeP = quantidadeP;}
    public String getFornecedorP(){return fornecedorP;}
    public void setFornecedorP(String fornecedorP){this.fornecedorP = fornecedorP;}
}
