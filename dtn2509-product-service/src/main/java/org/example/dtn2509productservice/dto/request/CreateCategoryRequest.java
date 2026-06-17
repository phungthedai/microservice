package org.example.dtn2509productservice.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

    @NotEmpty
    private String name;

    private String parentId;
}
