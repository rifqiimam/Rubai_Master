package com.rubai.rubai_master.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "master_areas")
data class AreaMaster(
    @Id
    @Column(name = "area_code", nullable = false)
    val areaCode: String,

    @Column(name = "area_name")
    var areaName: String? = null,

    @Column(name = "region_code")
    var regionCode: String? = null,

    @Column(name = "create_at")
    var createAt: LocalDateTime? = null,

    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null,

    )
