package com.example.vendas.validation.constraintvalidation;

import com.example.vendas.validation.NotEmptyList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyListValidator
        implements ConstraintValidator<NotEmptyList, List> {//primeiro parametro recebe a anotação e o segundo o que ele tem que validar


    @Override
    public boolean isValid(List list,//vai dizer se o metodo é valido
                           ConstraintValidatorContext constraintValidatorContext) {
        return list != null && list.isEmpty(); //lista não é nula e nem vazia então ela é válida
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
    }
}
