package co.com.bancolombia.source.stats;


import co.com.bancolombia.source.stats.dto.StatsDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StatsRepository extends ReactiveCrudRepository<StatsDTO, Long> {

    /*@Modifying
    @Query("UPDATE stats SET countClueFound = countClueFound + 1 WHERE id = :id")
    Mono<Integer> incrementCountClueFound(@Param("id") Long id);

    @Modifying
    @Query("UPDATE stats SET countClueNoFound = countClueNoFound + 1 WHERE id = :id")
    Mono<Integer> incrementCountClueNoFound(@Param("id") Long id);*/

    @Query("SELECT " +
            "    SUM(CASE WHEN clue_found = TRUE THEN 1 ELSE 0 END) AS countClueFound," +
            "    COUNT(CASE WHEN clue_found = FALSE THEN 1 END) AS countClueNoFound " +
            "FROM manuscripts")
    Mono<StatsDTO> consultStats();

}