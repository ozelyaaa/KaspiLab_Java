package kz.testandpractice.project5.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    @NotBlank
    private String name;
    @Positive
    private Double price;
}
