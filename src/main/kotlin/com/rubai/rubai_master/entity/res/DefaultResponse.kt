package com.rubai.rubai_master.entity.res

data class DefaultResponse(
    var status: String,
    var message: String,
    var data: Any?,
    var error: Any?,
    var timestamp: Long = System.currentTimeMillis()
)
