package com.rubai.rubai_master.services.impl

import com.rubai.rubai_master.entity.AreaMaster
import com.rubai.rubai_master.entity.req.AreaCodeRequest
import com.rubai.rubai_master.entity.req.UpdateAreaRequest
import com.rubai.rubai_master.entity.res.DefaultResponse
import com.rubai.rubai_master.repository.AreaMasterRepository
import com.rubai.rubai_master.services.AreaMasterService
import com.rubai.rubai_master.specification.AreaMasterSpecification
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AreaMasterServiceImpl(
    private val areaMasterRepository: AreaMasterRepository,
    private val specification: AreaMasterSpecification
):AreaMasterService {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun createAreaMaster(request:AreaCodeRequest): DefaultResponse {
        return try {

        log.info("Message : INIT Create new product travel")

        val areamaster = AreaMaster(
            areaCode = request.areaCode,
            areaName = request.areaName,
            regionCode = request.regionCode,
            createAt = LocalDateTime.now(),
            updateAt = LocalDateTime.now()
        )
        log.info("Message : bypass Check user data ")
        areaMasterRepository.save(areamaster)
        log.info("Message : FINISH Product travel created")

        return DefaultResponse("200", "SUCCESS : Create Area Master", null, false)
        }
        catch (ex:Exception){
            log.error("Error creating Area Master: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : Create Area Master ${ex.message}", null, true)
        }
    }

    override fun updateAreaMaster(areaCode: String, request: UpdateAreaRequest): DefaultResponse {
        return try {
            log.info("Message : INIT Update Area Master with Code: $areaCode")

            val existingArea = areaMasterRepository.findById(areaCode)
                .orElseThrow { IllegalArgumentException("Area not found with Code: $areaCode") }

            existingArea.apply {
                areaName = request.areaName
                regionCode = request.regionCode
                updateAt = LocalDateTime.now()
            }
            log.info("Message : bypass Check user data ")
            areaMasterRepository.save(existingArea)

            log.info("Message : FINISH Area Master updated")
            DefaultResponse("200", "SUCCESS : Update Area Master", null, false)
        } catch (ex: Exception) {
            log.error("Error updating Area Master: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : Update Area Master ${ex.message}", null, true)
        }
    }

    override fun getAreaMaster(params: Map<String, String?>, pageable: Pageable): DefaultResponse {
        return try {
            log.info("Fetching all Area")
            val specification = specification.buildSpecification(params)

            val sortedPageable = PageRequest.of(
                pageable.pageNumber,
                pageable.pageSize,
                Sort.by(Sort.Direction.DESC, "createAt")
            )

            val data = areaMasterRepository.findAll(specification, sortedPageable)

            log.info("MESSAGE : Success Get Area List Data")
            DefaultResponse("200", "SUCCESS : GET Area List Data", data, false)

        } catch (ex: Exception) {
            log.error("Error retrieving Area List Data: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : GET Area List Data ${ex.message}", null, true)
        }
    }

    override fun deleteAreaMaster(areaCode: String): DefaultResponse {
        return try {
            log.info("Message : INIT Delete Area with ID: $areaCode")

            val existingProduct = areaMasterRepository.findById(areaCode)
                .orElseThrow { IllegalArgumentException("Area not found with ID: $areaCode") }

            areaMasterRepository.delete(existingProduct)

            log.info("Message : SUCCESS Delete Area with ID: $areaCode")
            DefaultResponse("200", "SUCCESS: Area deleted", null, false)
        } catch (ex: Exception) {
            log.error("Error deleting Area: {}", ex.message, ex)
            DefaultResponse("500", "FAILED: Delete Area ${ex.message}", null, true)
        }
    }


}