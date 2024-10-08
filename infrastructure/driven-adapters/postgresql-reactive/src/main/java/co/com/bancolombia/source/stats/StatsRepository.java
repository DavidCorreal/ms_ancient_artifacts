package co.com.bancolombia.source.stats;


import co.com.bancolombia.source.stats.dto.StatsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StatsRepository {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public Mono<StatsDTO> findFirstElement() {
        return r2dbcEntityTemplate.selectOne(Query.query(Criteria.where("id").is(1)),
                StatsDTO.class);
    }

}