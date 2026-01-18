package kz.testandpractice.project5.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductPostRequestDTO {
    @Valid
    @NotNull
    private ProductDTO productDTO;

    @NotBlank
    private String address;
}
