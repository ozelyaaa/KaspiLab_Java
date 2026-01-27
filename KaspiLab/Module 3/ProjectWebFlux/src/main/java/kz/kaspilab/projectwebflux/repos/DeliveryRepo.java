package kz.kaspilab.projectwebflux.repos;

import kz.kaspilab.projectwebflux.domains.Delivery;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DeliveryRepo extends ReactiveCrudRepository<Delivery, Integer> {

    Mono<Delivery> findByProductId(Integer product_id);

}
