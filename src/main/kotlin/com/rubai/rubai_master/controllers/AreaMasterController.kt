package com.rubai.rubai_master.controllers

import com.rubai.rubai_master.entity.req.AreaCodeRequest
import com.rubai.rubai_master.entity.req.UpdateAreaRequest
import com.rubai.rubai_master.entity.res.DefaultResponse
import com.rubai.rubai_master.resources.constants.GlobalConstant
import com.rubai.rubai_master.services.AreaMasterService
import com.rubai.rubai_master.validation.annotation.ExistingAreaCode
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
class AreaMasterController (
    private val areaMasterService: AreaMasterService
){

    @PostMapping(GlobalConstant.AREA.CREATE_AREA_MASTER)
    fun createMasterArea(
        @Valid @RequestBody request: AreaCodeRequest
    ): ResponseEntity<Any> {
        val data = areaMasterService.createAreaMaster(request)
        return ResponseEntity(
            DefaultResponse(
                status = data.status,
                message = data.message,
                data = data.data,
                error = data.error
            ), HttpStatus.OK
        )
    }

    @GetMapping(GlobalConstant.AREA.GET_LIST_AREA_MASTER)
    fun getListMasterArea(
        @RequestParam params: Map<String, String?>,
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Any> {
        val data = areaMasterService.getAreaMaster(params, pageable)
        return ResponseEntity(
            DefaultResponse(
                status = data.status,
                message = data.message,
                data = data.data,
                error = data.error
            ), HttpStatus.OK
        )
    }

    @PutMapping(GlobalConstant.AREA.UPDATE_AREA_MASTER + "/{areaCode}")
    fun updatePayment(
        @PathVariable @ExistingAreaCode areaCode: String,
        @Valid @RequestBody request: UpdateAreaRequest
    ): ResponseEntity<Any> {
        val data = areaMasterService.updateAreaMaster(areaCode, request)
        return ResponseEntity(
            DefaultResponse(
                status = data.status,
                message = data.message,
                data = data.data,
                error = data.error
            ), HttpStatus.OK
        )
    }

    @DeleteMapping(GlobalConstant.AREA.DELETE_AREA_MASTER + "/{areaCode}")
    fun deleteAreaMaster(
        @PathVariable @ExistingAreaCode areaCode: String
    ): ResponseEntity<Any> {
        val data = areaMasterService.deleteAreaMaster(areaCode)
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