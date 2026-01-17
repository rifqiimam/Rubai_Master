package com.rubai.rubai_master.validation

import com.rubai.rubai_master.repository.BusinessMasterRepository
import com.rubai.rubai_master.validation.annotation.ExistingBusinessCode
import jakarta.validation.ConstraintValidator
import org.springframework.stereotype.Component

@Component
class ExistingBusinessCode (
    private val businessMasterRepository: BusinessMasterRepository
): ConstraintValidator<ExistingBusinessCode, String> {
    override fun isValid(value: String?, context: jakarta.validation.ConstraintValidatorContext): Boolean {
        if (value.isNullOrBlank()) return true
        return businessMasterRepository.existsById(value)
    }
}