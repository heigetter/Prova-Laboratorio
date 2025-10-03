package card.game.serving_web_content;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.DataOutput;

@Entity
@Table(name = "cartas")
public class Cartas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A franquia associada a carta não pode estar em branco.")
    @Size(max = 50, message =  "A franquia associada a carta deve ter no máximo 50 caracteres.")
    @Column(nullable = false, length = 50)
    private String franquia;

    @NotBlank(message = "O none não pode estar em branco.")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
    @Column(nullable = false, length = 150)
    private String nome;

    @NotBlank(message = "O idioma da carta não pode estar em branco.")
    @Size(max = 50, message = "O idioma da carta deve ter no máximo 50 caracteres.")
    @Column(nullable = false, length = 50)
    private String idioma;

    @NotBlank(message = "A Edição da carta não pode estar em branco.")
    @Size(max = 150, message = "A Edição da carta deve ter no máximo 150 caracteres.")
    @Column(nullable = false, length = 150)
    private String edicao;

    @NotBlank(message = "O número da carta não pode estar em branco.")
    @Size(max = 50, message = "O número da carta deve ter no máximo 50 caracteres.")
    @Column(nullable = false, length = 50)
    private String numeroC;

    @NotNull(message = "O preço não pode estar em branco.")
    @DecimalMin(value = "0.01", message = "O Preço deve ter valor maior que 0.00.")
    @DecimalMax(value = "100000000.01", message = "O Preço deve ter valor maximo de 100.000.000,00")
    @Column(nullable = false)
    private Double preco;

    @NotNull(message = "A quantidade de cartas não pode estar em branco.")
    @Min(value = 1, message = "A quantidade de cartas deve ter no minimo 1.")
    @Max(value = 10000, message= "A quantidade de cartas deve ter no maximo 10.000.")
    @Column(nullable = false)
    private Integer quantidade;

    @NotBlank(message = "O fornecedor não pode estar em branco.")
    @Size(max = 100, message = "O nome do fornecedor deve ter no máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String fornecedor;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getFranquia(){return franquia;}
    public void setFranquia(String franquia){this.franquia = franquia;}
    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}
    public String getIdioma(){return  idioma;}
    public void setIdioma(String idioma){this.idioma = idioma;}
    public String getEdicao(){return edicao;}
    public void setEdicao(String edicao){this.edicao = edicao;}
    public String getNumeroC(){return  numeroC;}
    public void setNumeroC(String numeroC){this.numeroC = numeroC;}
    public Double getPreco(){return preco;}
    public void setPreco(Double preco){this.preco = preco;}
    public Integer getQuantidade(){return quantidade;}
    public void setQuantidade(Integer quantidade){this.quantidade = quantidade;}
    public String getFornecedor(){return fornecedor;}
    public void setFornecedor(String fornecedor){this.fornecedor = fornecedor;}
}
