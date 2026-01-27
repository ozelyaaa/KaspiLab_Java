package kz.kaspilab.projectwebflux.services;

import kz.kaspilab.projectwebflux.clients.DeliveryClient;
import kz.kaspilab.projectwebflux.clients.ProductClient;
import kz.kaspilab.projectwebflux.exceptions.NotFoundException;
import kz.kaspilab.projectwebflux.models.ReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ProductClient productClient;
    private final DeliveryClient deliveryClient;

    @Override
    public Flux<ReportDTO> getReports(List<Integer> product_ids) {
        return Flux.fromIterable(product_ids)
            .flatMap(id ->
                Mono.zip(
                    productClient.getProductById(id),
                    deliveryClient.getDeliveryByProductId(id)
                ).map(tuple ->
                    new ReportDTO(
                            id,
                            tuple.getT1().getName(),
                            tuple.getT2().getStatus()
                    ))
            ).onErrorResume(NotFoundException.class, e -> Mono.empty());
    }
}
