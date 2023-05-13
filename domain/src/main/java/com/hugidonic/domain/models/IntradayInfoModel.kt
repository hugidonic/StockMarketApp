package com.hugidonic.domain.models

import java.time.LocalDateTime

data class IntradayInfoModel(
    val date: LocalDateTime,
    val close: Double,
)