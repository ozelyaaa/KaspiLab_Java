package kz.kaspilab.projectwebflux.repos;

import kz.kaspilab.projectwebflux.domains.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepo extends ReactiveCrudRepository<Product, Integer> {
}
