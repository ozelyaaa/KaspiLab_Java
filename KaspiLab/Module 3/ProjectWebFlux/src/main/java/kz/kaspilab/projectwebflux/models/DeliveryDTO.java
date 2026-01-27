package kz.kaspilab.projectwebflux.models;

import kz.kaspilab.projectwebflux.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {
    private Integer id;
    private Integer productId;
    private String address;
    private DeliveryStatus status;
}
