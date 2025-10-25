package com.rubai.rubai_master.entity.req

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class BusinessCodeRequest(
    @Id
    @Column(name = "business_code",nullable = false)
    val businessCode: String,

    @Column(name = "businessName")
    var businessName: String,

    @Column(name = "area_code")
    var areaCode: String,

    @Column(name = "address")
    var address: String,

    @Column(name = "phone")
    var phone: String,

    @Column(name = "isActive")
    var is_active: Boolean,
)
