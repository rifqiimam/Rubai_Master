package com.rubai.rubai_master.controllers

import com.rubai.rubai_master.entity.req.AreaCodeRequest
import com.rubai.rubai_master.entity.req.BusinessCodeRequest
import com.rubai.rubai_master.entity.res.DefaultResponse
import com.rubai.rubai_master.resources.constants.GlobalConstant
import com.rubai.rubai_master.services.BusinessMasterService
import com.rubai.rubai_master.validation.annotation.ExistingAreaCode
import com.rubai.rubai_master.validation.annotation.ExistingBusinessCode
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
class BusinessMasterController(
    private val businessMasterService: BusinessMasterService
){
    @PostMapping(GlobalConstant.BUSINESS.CREATE_BUSINESS_MASTER)
    fun createBusinessMaster(
        @Valid @RequestBody request: BusinessCodeRequest
    ): ResponseEntity<Any> {
        val data = businessMasterService.createBusinessMaster(request)
        return ResponseEntity(
            DefaultResponse(
                status = data.status,
                message = data.message,
                data = data.data,
                error = data.error
            ), HttpStatus.OK
        )
    }

    @GetMapping(GlobalConstant.BUSINESS.GET_LIST_BUSINESS_MASTER)
    fun getListBusinessMaster(
        @RequestParam params: Map<String, String?>,
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Any> {
        val data = businessMasterService.getBusinessMasters(params, pageable)
        return ResponseEntity(
            DefaultResponse(
                status = data.status,
                message = data.message,
                data = data.data,
                error = data.error
            ), HttpStatus.OK
        )
    }

    @PutMapping(GlobalConstant.BUSINESS.UPDATE_BUSINESS_MASTER + "/{businessCode}")
    fun updateBusinessMaster(
        @PathVariable @ExistingBusinessCode businessCode: String,
        @Valid @RequestBody request: BusinessCodeRequest
    ): ResponseEntity<Any> {
        val data = businessMasterService.updateBusinessMaster(businessCode, request)
        return ResponseEntity(
            DefaultResponse(
                status = data.status,
                message = data.message,
                data = data.data,
                error = data.error
            ), HttpStatus.OK
        )
    }

    @DeleteMapping(GlobalConstant.AREA.DELETE_AREA_MASTER + "/{businessCode}")
    fun deleteBusinessMaster(
        @PathVariable @ExistingBusinessCode businessCode: String
    ): ResponseEntity<Any> {
        val data = businessMasterService.deleteBusinessMaster(businessCode)
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