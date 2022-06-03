package com.example.vendas.validation;


import com.example.vendas.validation.constraintvalidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // para ser verificada em tempo de execução
@Target(ElementType.FIELD)//vai dizer onde podemos colocar essa anotação, que será em cima de um campo
@Constraint(validatedBy = NotEmptyListValidator.class) //validateBy vai dizer que essa anotação é de validação
public @interface NotEmptyList { //anotação que vai validar se nossa lista do pedido esta vazia

    String message() default "A lista não pode ser vazia.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
