package com.rubai.rubai_master.repository

import com.rubai.rubai_master.entity.BusinessMaster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface BusinessMasterRepository: JpaRepository<BusinessMaster, String>, JpaSpecificationExecutor<BusinessMaster> {
}