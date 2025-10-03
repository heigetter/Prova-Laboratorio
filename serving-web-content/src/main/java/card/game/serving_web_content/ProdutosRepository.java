package card.game.serving_web_content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    @Query(value = "SELECT * FROM produtos p WHERE p.nomeP like %:nomePKey%", nativeQuery = true)
    List<Produtos> findByNomeP(@Param("nomePKey") String nomePKey);

    @Query(value = "SELECT * FROM produtos p WHERE p.franquiaP like %:franquiaPKey%", nativeQuery = true)
    List<Produtos> findByFranquiaP(@Param("franquiaPKey") String franquiaPKey);

    @Query(value = "SELECT * FROM produtos p WHERE p.tipoP like %:tipoPKey%", nativeQuery = true)
    List<Produtos> findByTipoP(@Param("tipoPKey") String tipoPKey);

    @Query(value = "SELECT * FROM produtos p WHERE p.fornecedorP like %:fornecedorPKey%", nativeQuery = true)
    List<Produtos> findByFornecedorP(@Param("fornecedorPKey") String fornecedorPKey);

    @Query(value = "SELECT * FROM produtos p WHERE p.idiomaP like %:idiomaPKey%", nativeQuery = true)
    List<Produtos> findByIdiomaP(@Param("idiomaPKey") String idiomaPKey);
}
