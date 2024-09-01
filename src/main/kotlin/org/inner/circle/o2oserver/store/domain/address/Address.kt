package org.inner.circle.o2oserver.store.domain.address

data class Address(
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val addressDetail: String?,
    val zipCode: String
)
