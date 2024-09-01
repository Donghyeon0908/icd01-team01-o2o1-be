package org.inner.circle.o2oserver.order.presentation.api

import org.inner.circle.o2oserver.commons.models.BaseResponse
import org.inner.circle.o2oserver.order.application.OrderCommandFacade
import org.inner.circle.o2oserver.order.presentation.dto.OrderCreateRequest
import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 스웨거 생성을 위해 앞단에 interface 를
 * 두고 거기에 api doc 어노테이션을 만들 생각입니다.
 */
@RestController
@RequestMapping("/api/v1/order")
class OrderCommandController(
    private val orderCommandFacade: OrderCommandFacade
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun createOrder(
        @RequestBody orderCreate: OrderCreateRequest.OrderCreate,
        @AuthenticationPrincipal userDetails: UserDetails
    ): BaseResponse {
        log.info("order 생성 요청")
        val createOrderResult = orderCommandFacade.createOrder(orderCreate, userDetails.username)
        return BaseResponse(
            response = createOrderResult,
            statusCode = 200,
            msg = "success"
        )
    }
}