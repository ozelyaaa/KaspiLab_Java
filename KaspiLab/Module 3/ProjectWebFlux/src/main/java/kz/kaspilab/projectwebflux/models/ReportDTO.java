package kz.kaspilab.projectwebflux.models;

import kz.kaspilab.projectwebflux.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private Integer product_id;
    private String product_name;
    private DeliveryStatus deliveryStatus;
}