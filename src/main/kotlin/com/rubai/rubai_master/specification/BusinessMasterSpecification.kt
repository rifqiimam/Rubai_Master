package com.rubai.rubai_master.specification

import com.rubai.rubai_master.entity.BusinessMaster
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.domain.Specification
import jakarta.persistence.criteria.Predicate
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class BusinessMasterSpecification {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun buildSpecification(params: Map<String, String?>): Specification<BusinessMaster> {
        return Specification { root, _, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            params.forEach { (key, value) ->
                if (!value.isNullOrBlank()) {
                    when (key) {
                        // Exact match for codes
                        "businessCode" -> {
                            predicates.add(
                                criteriaBuilder.equal(root.get<String>(key), value)
                            )
                        }

                        "areaCode" -> {
                            predicates.add(
                                criteriaBuilder.equal(root.get<String>(key), value)
                            )
                        }

                        // Partial text search (case-insensitive)
                        "businessName" -> {
                            predicates.add(
                                criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get(key)),
                                    "%${value.lowercase()}%"
                                )
                            )
                        }

                        "address" -> {
                            predicates.add(
                                criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get(key)),
                                    "%${value.lowercase()}%"
                                )
                            )
                        }

                        "phone" -> {
                            predicates.add(
                                criteriaBuilder.like(
                                    root.get(key),
                                    "%${value}%"
                                )
                            )
                        }

                        // Boolean field
                        "isActive" -> {
                            try {
                                val boolValue = value.toBoolean()
                                predicates.add(
                                    criteriaBuilder.equal(root.get<Boolean>(key), boolValue)
                                )
                            } catch (e: Exception) {
                                log.error("Failed to parse boolean value for isActive: ${e.message}")
                            }
                        }

                        // Date range filters
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