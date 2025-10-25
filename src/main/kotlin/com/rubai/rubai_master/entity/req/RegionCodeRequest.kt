package com.rubai.rubai_master.entity.req

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class RegionCodeRequest (
    @Id
    @Column(name = "region_code",nullable = false)
    val regionCode: String,

    @Column(name = "region_name")
    var regionName: String,
)