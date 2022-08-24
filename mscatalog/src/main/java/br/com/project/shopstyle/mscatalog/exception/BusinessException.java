package br.com.project.shopstyle.mscatalog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class BusinessException extends RuntimeException {

    private final Integer status;
    private final String message;

}
