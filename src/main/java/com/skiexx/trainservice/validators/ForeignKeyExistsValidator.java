package com.skiexx.trainservice.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public class ForeignKeyExistsValidator implements ConstraintValidator<ForeignKeyExists, Object> {
    private JpaRepository<?, Serializable> jpaRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void initialize(ForeignKeyExists constraintAnnotation) {
        Class<?> repositoryClass = constraintAnnotation.repository();
        jpaRepository = (JpaRepository<?, Serializable>) applicationContext.getBean(repositoryClass);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;  // Не выполняем проверку для значения null
        }

        return jpaRepository.existsById((Serializable) value);
    }
}

