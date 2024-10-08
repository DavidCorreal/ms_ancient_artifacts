package co.com.bancolombia.model.manuscritp.gateway;

import co.com.bancolombia.model.manuscritp.Manuscript;
import reactor.core.publisher.Mono;

public interface ManuscriptSource {

    Mono<Void> save(Manuscript manuscript);

}
