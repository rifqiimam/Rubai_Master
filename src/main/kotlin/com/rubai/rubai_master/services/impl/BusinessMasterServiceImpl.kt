package com.rubai.rubai_master.services.impl

import com.rubai.rubai_master.entity.BusinessMaster
import com.rubai.rubai_master.entity.req.BusinessCodeRequest
import com.rubai.rubai_master.entity.res.DefaultResponse
import com.rubai.rubai_master.repository.BusinessMasterRepository
import com.rubai.rubai_master.services.BusinessMasterService
import com.rubai.rubai_master.specification.BusinessMasterSpecification
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BusinessMasterServiceImpl(
    private val businessMasterRepository: BusinessMasterRepository,
    private val specification: BusinessMasterSpecification
): BusinessMasterService {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun createBusinessMaster(request: BusinessCodeRequest): DefaultResponse {

        return try {
            log.info("Message : INIT Create Business Master")
            val businessMaster = BusinessMaster(
                businessCode = request.businessCode,
                businessName = request.businessName,
                area_code = request.areaCode,
                address = request.address,
                phone = request.phone,
                isActive = true,
                createAt = LocalDateTime.now(),
            )
            log.info("Message : bypass Check user data ")
            businessMasterRepository.save(businessMaster)

            log.info("Message : FINISH Create Business Master")

            DefaultResponse("200", "SUCCESS : Create Business Master", null, false)

        } catch (ex:Exception){
            log.error("Error creating Business Master: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : Create Business Master ${ex.message}", null, true)
        }
    }

    override fun updateBusinessMaster(businessCode:String,request: BusinessCodeRequest): DefaultResponse {

        return try {
            log.info("Message : INIT Update Business Master")

            val existingArea = businessMasterRepository.findById(businessCode)
                .orElseThrow { IllegalArgumentException("business not found with Code: $businessCode") }

            existingArea.apply {
                businessName = request.businessName
                area_code = request.areaCode
                address = request.address
                phone = request.phone
                isActive = request.is_active
                updateAt = LocalDateTime.now()
            }

            log.info("Message : FINISH Update Business Master")

            DefaultResponse("200", "SUCCESS : Update Business Master", null, false)

        } catch (ex:Exception){
            log.error("Error updating Business Master: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : Update Business Master ${ex.message}", null, true)
        }
    }

    override fun getBusinessMasters(params: Map<String, String?>, pageable: Pageable): DefaultResponse {

        return try {
            log.info("Message : INIT Get Business Masters")

            val specification = specification.buildSpecification(params)

            val sortedPageable = PageRequest.of(
                pageable.pageNumber,
                pageable.pageSize,
                Sort.by(Sort.Direction.DESC, "createAt")
            )
            log.info("Message : bypass Check user data ")
            val data = businessMasterRepository.findAll(specification, sortedPageable)
            log.info("Message : FINISH Get Business Masters")

            DefaultResponse("200", "SUCCESS : Get Business Masters", data, false)

        } catch (ex:Exception){
            log.error("Error getting Business Masters: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : Get Business Masters ${ex.message}", null, true)
        }
    }

    override fun deleteBusinessMaster(businessCode: String): DefaultResponse {

        return try {
            log.info("Message : INIT Delete Business Master")
            val existingProduct = businessMasterRepository.findById(businessCode)
                .orElseThrow { IllegalArgumentException("Business not found with Code: $businessCode") }

            businessMasterRepository.delete(existingProduct)

            log.info("Message : FINISH Delete Business Master")

            DefaultResponse("200", "SUCCESS : Delete Business Master", null, false)

        } catch (ex:Exception){
            log.error("Error deleting Business Master: {}", ex.message, ex)
            DefaultResponse("500", "FAILED : Delete Business Master ${ex.message}", null, true)
        }
    }


}