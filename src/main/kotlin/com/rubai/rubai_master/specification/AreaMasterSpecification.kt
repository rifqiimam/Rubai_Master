package com.rubai.rubai_master.specification

import com.rubai.rubai_master.entity.AreaMaster
import jakarta.persistence.criteria.Predicate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class AreaMasterSpecification {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun buildSpecification(params: Map<String, String?>): Specification<AreaMaster> {
        return Specification { root, _, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            params.forEach { (key, value) ->
                if (!value.isNullOrBlank()) {
                    when (key) {
                        "areaCode" -> {
                            predicates.add(
                                criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get(key)),
                                    "%${value.lowercase()}%"
                                )
                            )
                        }

                        "areaName" -> {
                            predicates.add(
                                criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get(key)),
                                    "%${value.lowercase()}%"
                                )
                            )
                        }

                        "regionCode" -> {
                            predicates.add(
                                criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get(key)),
                                    "%${value.lowercase()}%"
                                )
                            )
                        }

                        "createdAtFrom" -> {
                            try {
                                val date = LocalDate.parse(value, DateTimeFormatter.ISO_DATE)
                                val dateTime = date.atStartOfDay()
                                predicates.add(
                                    criteriaBuilder.greaterThanOrEqualTo(
                                        root.get("createdAt"),
                                        dateTime
                                    )
                                )
                            } catch (e: Exception) {
                                log.error("Failed to parse createdAtFrom date: ${e.message}")
                            }
                        }

                        "createdAtTo" -> {
                            try {
                                val date = LocalDate.parse(value, DateTimeFormatter.ISO_DATE)
                                val dateTime = date.atTime(23, 59, 59)
                                predicates.add(
                                    criteriaBuilder.lessThanOrEqualTo(
                                        root.get("createdAt"),
                                        dateTime
                                    )
                                )
                            } catch (e: Exception) {
                                log.error("Failed to parse createdAtTo date: ${e.message}")
                            }
                        }

                        "updatedAtFrom" -> {
                            try {
                                val date = LocalDate.parse(value, DateTimeFormatter.ISO_DATE)
                                val dateTime = date.atStartOfDay()
                                predicates.add(
                                    criteriaBuilder.greaterThanOrEqualTo(
                                        root.get("updatedAt"),
                                        dateTime
                                    )
                                )
                            } catch (e: Exception) {
                                log.error("Failed to parse updatedAtFrom date: ${e.message}")
                            }
                        }

                        "updatedAtTo" -> {
                            try {
                                val date = LocalDate.parse(value, DateTimeFormatter.ISO_DATE)
                                val dateTime = date.atTime(23, 59, 59)
                                predicates.add(
                                    criteriaBuilder.lessThanOrEqualTo(
                                        root.get("updatedAt"),
                                        dateTime
                                    )
                                )
                            } catch (e: Exception) {
                                log.error("Failed to parse updatedAtTo date: ${e.message}")
                            }
                        }
                    }
                }
            }

            if (predicates.isEmpty()) {
                criteriaBuilder.conjunction()
            } else {
                criteriaBuilder.and(*predicates.toTypedArray())
            }
        }
    }
}