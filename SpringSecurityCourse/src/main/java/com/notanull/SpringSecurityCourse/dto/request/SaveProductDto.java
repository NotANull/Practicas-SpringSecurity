package com.notanull.SpringSecurityCourse.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveProductDto implements Serializable {

    @NotBlank
    private String name;
    @DecimalMin(value = "0.01")
    private BigDecimal price;
    @Min(value = 1)
    private Long categoryId;
}
