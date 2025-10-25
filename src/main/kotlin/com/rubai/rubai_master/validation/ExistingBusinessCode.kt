package com.rubai.rubai_master.validation

import com.rubai.rubai_master.repository.RegionMasterRepository
import com.rubai.rubai_master.validation.annotation.ExistingBusinessCode
import jakarta.validation.ConstraintValidator
import org.springframework.stereotype.Component

@Component
class ExistingBusinessCode (
    private val regionMasterRepository: RegionMasterRepository
): ConstraintValidator<ExistingBusinessCode, String> {
    override fun isValid(value: String?, context: jakarta.validation.ConstraintValidatorContext): Boolean {
        if (value.isNullOrBlank()) return true
        return regionMasterRepository.existsById(value)
    }
}