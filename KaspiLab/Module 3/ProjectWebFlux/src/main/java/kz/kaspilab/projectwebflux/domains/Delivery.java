package kz.kaspilab.projectwebflux.domains;

import kz.kaspilab.projectwebflux.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("delivery")
public class Delivery {
    @Id
    private Integer id;
    @Column("product_id")
    private Integer productId;
    private String address;
    private DeliveryStatus status;
}
