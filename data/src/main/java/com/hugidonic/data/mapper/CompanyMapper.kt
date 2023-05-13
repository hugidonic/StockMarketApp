package com.hugidonic.data.mapper

import com.hugidonic.data.db.CompanyListingEntity
import com.hugidonic.data.remote.dto.CompanyInfoDto
import com.hugidonic.domain.models.CompanyInfoModel
import com.hugidonic.domain.models.CompanyListingModel

fun CompanyListingEntity.toCompanyListingModel(): CompanyListingModel {
    return CompanyListingModel(
        symbol=symbol,
        name=name,
        exchange=exchange
    )
}

fun CompanyListingModel.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        symbol=symbol,
        name=name,
        exchange=exchange
    )
}

fun CompanyInfoDto.toCompanyInfoModel(): CompanyInfoModel {
    return CompanyInfoModel(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}