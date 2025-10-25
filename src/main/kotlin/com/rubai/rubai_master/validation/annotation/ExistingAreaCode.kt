package com.rubai.rubai_master.validation.annotation

import com.rubai.rubai_master.validation.ExistingAreaCode
import jakarta.validation.Constraint
import jakarta.validation.Payload
import java.lang.annotation.Documented
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Documented
@Constraint(validatedBy = [ExistingAreaCode::class])
@Target(FIELD, FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER, VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExistingAreaCode(
    val message: String = "AreaCode does not exist",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
