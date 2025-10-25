package com.rubai.rubai_master.repository

import com.rubai.rubai_master.entity.AreaMaster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface AreaMasterRepository: JpaRepository<AreaMaster, String>, JpaSpecificationExecutor<AreaMaster> {
}