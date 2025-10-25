package com.rubai.rubai_master.repository

import com.rubai.rubai_master.entity.RegionMaster
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface RegionMasterRepository: JpaRepository<RegionMaster, String>, JpaSpecificationExecutor<RegionMaster> {
}