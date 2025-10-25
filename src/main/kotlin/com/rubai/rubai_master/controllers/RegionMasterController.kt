package com.rubai.rubai_master.controllers

import com.rubai.rubai_master.entity.req.RegionCodeRequest
import com.rubai.rubai_master.entity.res.DefaultResponse
import com.rubai.rubai_master.resources.constants.GlobalConstant
import com.rubai.rubai_master.services.RegionMasterService
import com.rubai.rubai_master.validation.annotation.ExistingRegionCode
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping(GlobalConstant.MASTER.ROOT_PATH)
class RegionMasterController (
    private val regionMasterService: RegionMasterService
){

    @GetMapping(GlobalConstant.REGION.GET_LIST_REGION_MASTER)
    fun getListMasterRegion(
        @RequestParam params: Map<String, String?>,
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Any> {
        val data = regionMasterService.getRegionMasters(params, pageable)
        return ResponseEntity(
            DefaultResponse(
                status = data.status,
                message = data.message,
                data = data.data,
                error = data.error
            ), HttpStatus.OK
        )
    }

    @GetMapping(GlobalConstant.REGION.CREATE_REGION_MASTER)
    fun createRegionMaster(
        @Valid @RequestBody request: RegionCodeRequest
    ): ResponseEntity<Any> {
        val data = regionMasterService.createRegionMaster(request)
        return ResponseEntity(
            DefaultResponse(
                status = data.status,
                message = data.message,
                data = data.data,
                error = data.error
            ), HttpStatus.OK
        )
    }

    @PutMapping(GlobalConstant.REGION.UPDATE_REGION_MASTER + "/{regionCode}")
    fun updateRegionMaster(
        @PathVariable @ExistingRegionCode regionCode: String,
        @Valid @RequestBody request: RegionCodeRequest
    ): ResponseEntity<Any> {
        val data = regionMasterService.updateRegionMaster(regionCode, request)
        return ResponseEntity(
            DefaultResponse(
                status = data.status,
                message = data.message,
                data = data.data,
                error = data.error
            ), HttpStatus.OK
        )
    }

    @DeleteMapping(GlobalConstant.REGION.DELETE_REGION_MASTER + "/{regionCode}")
    fun deleteRegionMaster(
        @PathVariable @ExistingRegionCode regionCode: String
    ): ResponseEntity<Any> {
        val data = regionMasterService.deleteRegionMaster(regionCode)
        return ResponseEntity(
            DefaultResponse(
                status = data.status,
                message = data.message,
                data = data.data,
                error = data.error
            ), HttpStatus.OK
        )
    }
}