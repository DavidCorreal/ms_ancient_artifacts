package co.com.bancolombia.source.stats.dto;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("artifacts.stats")
public record StatsDTO(@Id Long id,
                       @Column("countcluefound") Integer countClueFound,
                       @Column("countcluenofound") Integer countClueNoFound) { }
