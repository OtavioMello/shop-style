package br.com.project.shopstyle.mscatalog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ValidationError {

    private String field;
    private String message;

}
