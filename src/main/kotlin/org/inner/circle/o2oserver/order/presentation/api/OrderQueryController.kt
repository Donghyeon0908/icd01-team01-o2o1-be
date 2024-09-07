package org.inner.circle.o2oserver.order.presentation.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.inner.circle.o2oserver.commons.response.BaseResponse
import org.inner.circle.o2oserver.order.application.OrderQueryFacade
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/order")
class OrderQueryController(
    private val orderQueryFacade: OrderQueryFacade,
) {
    @GetMapping
    fun getOrders(
        @AuthenticationPrincipal userDetails: UserDetails,
    ): BaseResponse {
        val orderList = orderQueryFacade.getOrderList(userDetails.username)
        return BaseResponse.success(orderList)
    }

    @GetMapping("/{orderId}")
    fun getOrder(
        @PathVariable orderId: Long,
        @AuthenticationPrincipal userDetails: UserDetails,
    ): BaseResponse {
        val orderDetail = orderQueryFacade.getOrderDetail(orderId, userDetails.username)
        return BaseResponse.success(orderDetail)
    }

    @GetMapping("/cancel/{orderId}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun deliverySubscribe(
        @PathVariable orderId: Long,
        @AuthenticationPrincipal userDetails: UserDetails,
    ): Flow<BaseResponse> = flow {
        val cancelOrderResult = orderQueryFacade.deliverySubscribe(orderId, userDetails.username)
        emit(BaseResponse.success(cancelOrderResult))
    }
}
