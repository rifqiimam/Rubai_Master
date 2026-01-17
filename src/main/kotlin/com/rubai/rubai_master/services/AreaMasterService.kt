package com.rubai.rubai_master.services

import com.rubai.rubai_master.entity.req.AreaCodeRequest
import com.rubai.rubai_master.entity.req.UpdateAreaRequest
import com.rubai.rubai_master.entity.res.DefaultResponse
import org.springframework.data.domain.Pageable

interface AreaMasterService {
    fun createAreaMaster(request: AreaCodeRequest): DefaultResponse
    fun updateAreaMaster(areaCode: String,request: UpdateAreaRequest): DefaultResponse
    fun getAreaMaster(params: Map<String, String?>, pageable: Pageable):DefaultResponse
    fun deleteAreaMaster(areaCode: String):DefaultResponse
}