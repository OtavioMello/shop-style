package br.com.project.shopstyle.mscustomer.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ValidationError {

    private String field;
    private String message;

}
