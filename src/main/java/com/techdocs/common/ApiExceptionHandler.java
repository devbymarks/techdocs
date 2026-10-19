package com.techdocs.common;
import com.techdocs.document.DocumentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(DocumentNotFoundException.class) ProblemDetail notFound(DocumentNotFoundException ex){ProblemDetail p=ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());p.setTitle("Documento não encontrado");return p;}
 @ExceptionHandler(MethodArgumentNotValidException.class) ProblemDetail validation(MethodArgumentNotValidException ex){String message=ex.getBindingResult().getFieldErrors().stream().map(e->e.getField()+": "+e.getDefaultMessage()).findFirst().orElse("Dados inválidos.");ProblemDetail p=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,message);p.setTitle("Validação dos dados");return p;}
}
