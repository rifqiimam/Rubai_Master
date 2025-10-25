package com.rubai.rubai_master.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "master_business")
data class BusinessMaster(
    @Id
    @Column(name = "business_code", nullable = false)
    val businessCode: String,

    @Column(name = "business_name")
    var businessName: String? = null,

    @Column(name = "address")
    var address: String? = null,

    @Column(name = "phone")
    var phone: String? = null,

    @Column(name = "area_code")
    var area_code: String? = null,

    @Column(name = "is_active")
    var isActive: Boolean? = null,

    @Column(name = "create_at")
    var createAt: LocalDateTime? = null,

    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null,
)
