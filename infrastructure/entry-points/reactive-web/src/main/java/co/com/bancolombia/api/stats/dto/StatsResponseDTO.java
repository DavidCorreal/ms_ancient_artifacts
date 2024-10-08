package co.com.bancolombia.api.stats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record StatsResponseDTO(@JsonProperty("count_clue_found") Integer countClueFound,
                               @JsonProperty("count_no_clue") Integer countClueNoFound,
                               Double ratio) {
}