package kz.kaspilab.projectwebflux.repos;

import kz.kaspilab.projectwebflux.domains.Delivery;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DeliveryRepo extends ReactiveCrudRepository<Delivery, Integer> {
}
