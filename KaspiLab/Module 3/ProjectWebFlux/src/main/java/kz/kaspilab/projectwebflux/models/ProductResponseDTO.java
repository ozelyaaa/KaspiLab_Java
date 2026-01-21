package kz.kaspilab.projectwebflux.models;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Integer id;
    private String name;
    private Double price;
}
