package br.com.project.shopstyle.mscustomer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TokenDTO {

    private String token;
    private String type;

}
