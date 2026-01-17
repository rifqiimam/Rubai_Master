package com.rubai.rubai_master.entity.req

import jakarta.validation.constraints.NotBlank


data class UpdateAreaRequest(
    @field:NotBlank(message = "AreaName Code tidak boleh kosong")
    val areaName: String,

    @field:NotBlank(message = "Region Code Code tidak boleh kosong")
    val regionCode: String,
)
