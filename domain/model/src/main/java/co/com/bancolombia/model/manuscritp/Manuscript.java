package co.com.bancolombia.model.manuscritp;

import lombok.Builder;

@Builder
public record Manuscript(String[] manuscript, Boolean clueFound) { }

