package com.pm.mbus.dto.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @NotNull
    @Schema(description = "Name of people", example = "Phu Minh")
    private String name;
    @Schema(description = "Age of people", example = "22")
    @NotNull
    @Min(value = 0)
    @Max(value = 60)
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
