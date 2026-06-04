package com.rifat.Inventory.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Name required")
    private String name;

    private String category;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;
}