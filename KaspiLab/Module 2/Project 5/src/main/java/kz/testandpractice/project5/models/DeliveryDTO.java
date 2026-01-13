package kz.testandpractice.project5.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryDTO {
    @NotNull
    private Long productId;
    @NotBlank
    private String address;
}
