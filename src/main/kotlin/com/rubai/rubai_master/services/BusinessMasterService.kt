package com.rubai.rubai_master.services

import com.rubai.rubai_master.entity.req.BusinessCodeRequest
import com.rubai.rubai_master.entity.req.UpdateBusinessCodeRequest
import com.rubai.rubai_master.entity.res.DefaultResponse
import org.springframework.data.domain.Pageable

interface BusinessMasterService {
    fun createBusinessMaster(request: BusinessCodeRequest): DefaultResponse
    fun updateBusinessMaster(businessCode:String,request: UpdateBusinessCodeRequest): DefaultResponse
    fun getBusinessMasters(params: Map<String, String?>, pageable: Pageable): DefaultResponse
    fun deleteBusinessMaster(businessCode: String): DefaultResponse
}