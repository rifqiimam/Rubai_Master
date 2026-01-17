package com.rubai.rubai_master.entity.req

import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank

data class UpdateRegionCodeRequest(
    @field:NotBlank(message = "Region Name Code tidak boleh kosong")
    var regionName: String,
)
