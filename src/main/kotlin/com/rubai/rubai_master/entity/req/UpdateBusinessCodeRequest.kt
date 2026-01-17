package com.rubai.rubai_master.entity.req

import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank

data class UpdateBusinessCodeRequest(
    @field:NotBlank(message = "Region Code Code tidak boleh kosong")
    var businessName: String,

    var areaCode: String,

    var address: String,

    var phone: String,

    var is_active: Boolean,
)
