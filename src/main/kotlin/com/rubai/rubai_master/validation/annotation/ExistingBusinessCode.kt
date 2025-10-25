package com.rubai.rubai_master.validation.annotation

import com.rubai.rubai_master.validation.ExistingBusinessCode
import jakarta.validation.Constraint
import jakarta.validation.Payload
import java.lang.annotation.Documented
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Documented
@Constraint(validatedBy = [ExistingBusinessCode::class])
@Target(FIELD, FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER, VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExistingBusinessCode(
    val message: String = "BusinessCode does not exist",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
