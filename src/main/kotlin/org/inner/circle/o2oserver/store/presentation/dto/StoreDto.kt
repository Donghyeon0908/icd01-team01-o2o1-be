package org.inner.circle.o2oserver.store.presentation.dto

import org.inner.circle.o2oserver.store.domain.address.Address

data class CommonResponse<T>(
    val response: T,
    val statusCode: Int,
    val msg: String,
)

data class CommonListResponse<T>(
    val totalCount: Int?,
    val page: Int?,
    val size: Int,
    val response: T,
    val statusCode: Int,
    val msg: String,
)

data class StoreListRequest(
    val address: AddressDTO,
    val category: String?,
    val page: Int? = 1,
    val size: Int? = 10,
    val keyword: String?,
)

data class AddressDTO(
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val addressDetail: String?,
    val zipCode: String,
) {
    fun toDomain(): Address {
        return Address(latitude, longitude, address, addressDetail, zipCode)
    }
}

data class StoreResponse(
    val stores: List<StoreInfo>,
    val totalCount: Int,
    val page: Int,
    val size: Int,
    val statusCode: Int,
    val msg: String,
)

data class StoreInfo(
    val storeId: Long,
    val storeName: String,
    val minimumPrice: Int,
    val deliveryPrice: Int,
    val reviewCount: Int,
    val reviewRate: Double,
    val thumbnailUrl: String,
    val category: String,
)