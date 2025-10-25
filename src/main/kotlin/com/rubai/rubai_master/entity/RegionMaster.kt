package com.rubai.rubai_master.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "master_region")
data class RegionMaster(
    @Id
    @Column(name = "region_code", nullable = false)
    var regionCode: String,

    @Column(name = "region_name")
    var regionName: String? = null,

    @Column(name = "create_at")
    var createAt: LocalDateTime? = null,

    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null,
)
