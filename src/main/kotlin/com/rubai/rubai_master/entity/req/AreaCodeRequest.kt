package com.rubai.rubai_master.entity.req

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class AreaCodeRequest(
    @Id
    @Column(name = "area_code",nullable = false)
    val areaCode: String,

    @Column(name = "area_name")
    var areaName: String,

    @Column(name = "region_code")
    var regionCode: String,

)
