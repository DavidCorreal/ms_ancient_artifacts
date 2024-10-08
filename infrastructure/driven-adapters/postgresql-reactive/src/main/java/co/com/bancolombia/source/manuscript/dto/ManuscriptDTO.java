package co.com.bancolombia.source.manuscript.dto;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Table("artifacts.manuscripts")
public record ManuscriptDTO(@Id Long id, String[] manuscript, @Column("clue_found") Boolean clueFound) { }