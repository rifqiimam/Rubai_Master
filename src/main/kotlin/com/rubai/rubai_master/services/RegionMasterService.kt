package com.rubai.rubai_master.services

import com.rubai.rubai_master.entity.req.RegionCodeRequest
import com.rubai.rubai_master.entity.res.DefaultResponse
import org.springframework.data.domain.Pageable

interface RegionMasterService {
    fun createRegionMaster(request: RegionCodeRequest): DefaultResponse
    fun updateRegionMaster(regionCode:String,request: RegionCodeRequest): DefaultResponse
    fun getRegionMasters(params: Map<String, String?>, pageable: Pageable): DefaultResponse
    fun deleteRegionMaster(regionCode:String): DefaultResponse
}