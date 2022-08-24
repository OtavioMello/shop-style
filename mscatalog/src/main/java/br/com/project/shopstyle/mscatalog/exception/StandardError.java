package br.com.project.shopstyle.mscatalog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class StandardError {

    private String error;
    private Integer status;
    private String path;
    private LocalDateTime timestamp;

}
