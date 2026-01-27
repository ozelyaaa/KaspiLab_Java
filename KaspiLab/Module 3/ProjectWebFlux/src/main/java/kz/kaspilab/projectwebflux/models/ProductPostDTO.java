package kz.kaspilab.projectwebflux.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPostDTO {
    private String name;
    private Double price;
    private String address;
}
