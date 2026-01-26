package kz.kaspilab.projectwebflux.services;

import kz.kaspilab.projectwebflux.clients.DeliveryClient;
import kz.kaspilab.projectwebflux.clients.ProductClient;
import kz.kaspilab.projectwebflux.enums.DeliveryStatus;
import kz.kaspilab.projectwebflux.exceptions.NotFoundException;
import kz.kaspilab.projectwebflux.models.DeliveryDTO;
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
                            .defaultIfEmpty(getDefaultDelivery(id))
                ).map(tuple ->
                    new ReportDTO(
                            id,
                            tuple.getT1().getName(),
                            tuple.getT2().getStatus()
                    ))
            ).onErrorResume(NotFoundException.class, e -> Mono.empty());
    }

    private DeliveryDTO getDefaultDelivery(Integer product_id) {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setProduct_id(product_id);
        deliveryDTO.setStatus(DeliveryStatus.NOT_CREATED);
        return deliveryDTO;
    }
}
