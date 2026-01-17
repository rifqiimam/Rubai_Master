package com.rubai.rubai_master.services.impl

import com.rubai.rubai_master.entity.RegionMaster
import com.rubai.rubai_master.entity.req.RegionCodeRequest
import com.rubai.rubai_master.entity.req.UpdateRegionCodeRequest
import com.rubai.rubai_master.entity.res.DefaultResponse
import com.rubai.rubai_master.repository.RegionMasterRepository
import com.rubai.rubai_master.services.RegionMasterService
import com.rubai.rubai_master.specification.RegionMasterSpecification
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RegionMasterServiceImpl(
    private val specification: RegionMasterSpecification,
    private val regionMasterRepository: RegionMasterRepository
    ): RegionMasterService {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun createRegionMaster(request: RegionCodeRequest): DefaultResponse {
        return try {
            log.info("Message : INIT Create Business Master")

            val businessMasterResponse = RegionMaster(
                regionCode = request.regionCode,
                regionName = request.regionName,
                createAt = LocalDateTime.now(),
            )

            log.info("Message : bypass Check user data ")
            regionMasterRepository.save(businessMasterResponse)

            log.info("Message : FINISH Create Business Master")

            DefaultResponse("200", "SUCCESS : Create Business Master", null, false)

        } catch (ex: Exception) {
            log.error("Error creating Business Master: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : Create Business Master ${ex.message}", null, true)
        }
    }

    override fun updateRegionMaster(regionCode:String,request: UpdateRegionCodeRequest): DefaultResponse {
        return try {
            log.info("Message : INIT Update Business Master")

            val existingArea = regionMasterRepository.findById(regionCode)
                .orElseThrow { IllegalArgumentException("Region not found with Code: $regionCode") }

            existingArea.apply {
                regionName = request.regionName
                updateAt = LocalDateTime.now()
            }
            log.info("Message : bypass Check user data ")
            regionMasterRepository.save(existingArea)

            log.info("Message : FINISH Update Business Master")

            DefaultResponse("200", "SUCCESS : Update Business Master", null, false)

        } catch (ex: Exception) {
            log.error("Error updating Business Master: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : Update Business Master ${ex.message}", null, true)
        }
    }

    override fun getRegionMasters(params: Map<String, String?>, pageable: Pageable): DefaultResponse {
        return try {
            log.info("Message : INIT Get Business Masters")
            val specification = specification.buildSpecification(params)

            val sortedPageable = PageRequest.of(
                pageable.pageNumber,
                pageable.pageSize,
                Sort.by(Sort.Direction.DESC, "createAt")
            )
            log.info("Message : bypass Check user data ")
            val data = regionMasterRepository.findAll(specification, sortedPageable)
            log.info("Message : FINISH Get Business Masters")

            DefaultResponse("200", "SUCCESS : Get Business Masters", data, false)

        } catch (ex: Exception) {
            log.error("Error getting Business Masters: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : Get Business Masters ${ex.message}", null, true)
        }
    }

    override fun deleteRegionMaster(regionCode:String): DefaultResponse {
        return try {
            log.info("Message : INIT Delete Business Master")

            val existingArea = regionMasterRepository.findById(regionCode)
                .orElseThrow { IllegalArgumentException("Region not found with Code: $regionCode") }

            regionMasterRepository.delete(existingArea)

            log.info("Message : FINISH Delete Business Master")

            DefaultResponse("200", "SUCCESS : Delete Business Master", null, false)

        } catch (ex: Exception) {
            log.error("Error deleting Business Master: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : Delete Business Master ${ex.message}", null, true)
        }
    }

}