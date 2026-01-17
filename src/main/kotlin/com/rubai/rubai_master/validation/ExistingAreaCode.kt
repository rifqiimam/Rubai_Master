package com.rubai.rubai_master.validation

import com.rubai.rubai_master.repository.AreaMasterRepository
import com.rubai.rubai_master.validation.annotation.ExistingAreaCode
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class ExistingAreaCode (
    private val areaMasterRepository: AreaMasterRepository
): ConstraintValidator<ExistingAreaCode, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext): Boolean {
        if (!StringUtils.hasText(value)) return true
        return areaMasterRepository.existsById(value)
    }
}