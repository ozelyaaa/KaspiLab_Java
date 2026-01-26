package kz.kaspilab.projectwebflux.models;

import kz.kaspilab.projectwebflux.enums.DeliveryStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryDTO {
    private Integer id;
    private Integer product_id;
    private String address;
    private DeliveryStatus status;
}
