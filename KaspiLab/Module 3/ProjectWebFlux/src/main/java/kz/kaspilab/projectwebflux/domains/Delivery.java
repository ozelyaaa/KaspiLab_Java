package kz.kaspilab.projectwebflux.domains;

import kz.kaspilab.projectwebflux.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="deliveries")
public class Delivery {
    @Id
    private Integer id;
    private Integer product_id;
    private String address;
    private DeliveryStatus status;
}
