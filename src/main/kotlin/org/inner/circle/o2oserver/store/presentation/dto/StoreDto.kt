package org.inner.circle.o2oserver.store.presentation.dto

import org.inner.circle.o2oserver.store.domain.Address
import org.inner.circle.o2oserver.store.domain.review.ReviewInfo

data class CommonResponse(
    val response: Any,
    val statusCode: Int,
    val msg: String,
)

data class CommonListResponse(
    val totalCount: Long,
    val page: Int?,
    val size: Int,
    val response: Any,
    val statusCode: Int,
    val msg: String,
)

data class StoreListRequest(
    val address: AddressDTO,
    val category: String?,
    val page: Int = 1,
    val size: Int = 10,
    val keyword: String = "",
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

data class StoreDetailInfoDTO(
    val storeId: Long,
    val storeName: String,
    val contactNumber: String? = null,
    val zipCode: String? = null,
    val address: String? = null,
    val addressDetail: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val openTime: String? = null,
    val closeTime: String? = null,
    val category: String? = null,
    val deliveryArea: String? = null,
    val deliveryPrice: Int? = null,
    val minimumPrice: Int? = null,
    val reviewCount: Int? = null,
    val reviewRate: Double? = null,
    val thumbnails: List<String>? = null,
    val menus: List<StoreMenuDTO>,
)

data class StoreMenuDTO(
    val menuId: Long?,
    val menuName: String,
    val menuPrice: Long?,
    val optionGroups: List<MenuOptionGroupDTO>?,
    val description: String?,
    val menuImages: List<String>?,
)

class MenuOptionGroupDTO(
    val optionGroupId: Long?,
    val optionGroupName: String?,
    val options: List<MenuOptionDTO>?,
    val isRequired: Boolean?,
    val isMultiple: Boolean?,
)

class MenuOptionDTO(
    val optionId: Long?,
    val optionName: String,
    val optionPrice: Int,
)

data class Review(
    val reviewId: Int,
    val contents: String,
    val rating: Int,
    val reviewImage: List<String>,
)

data class StoreReviewDTO(
    val storeName: String,
    val reviews: List<ReviewInfo>,
)
