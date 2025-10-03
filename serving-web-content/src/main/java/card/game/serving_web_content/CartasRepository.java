package card.game.serving_web_content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartasRepository
    extends JpaRepository<Cartas, Long>{

    @Query(value = "SELECT * FROM cartas c WHERE c.nome like %:nomeKey%", nativeQuery = true)
    List<Cartas> findByNome(@Param("nomeKey") String nomeKey);

    @Query(value = "SELECT * FROM cartas c WHERE c.franquia like %:franquiaKey%", nativeQuery = true)
    List<Cartas> findByFranquia(@Param("franquiaKey") String franquiaKey);

    @Query(value = "SELECT * FROM cartas c WHERE c.edicao like %:edicaoKey%", nativeQuery = true)
    List<Cartas> findByEdicao(@Param("edicaoKey") String edicaoKey);

    @Query(value = "SELECT * FROM cartas c WHERE c.numeroC like %:numeroCKey%", nativeQuery = true)
    List<Cartas> findByNumeroC(@Param("numeroCKey") String numeroCKey);

    @Query(value = "SELECT * FROM cartas c WHERE c.fornecedor like %:fornecedorKey%", nativeQuery = true)
    List<Cartas> findByFornecedor(@Param("fornecedorKey") String fornecedorKey);

    @Query(value = "SELECT * FROM cartas c WHERE c.idioma like %:idiomaKey%", nativeQuery = true)
    List<Cartas> findByIdioma(@Param("idiomaKey") String idiomaKey);

}