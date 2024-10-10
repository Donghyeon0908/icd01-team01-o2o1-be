package org.inner.circle.o2oserver.store.presentation.api

import jakarta.validation.Valid
import org.inner.circle.o2oserver.store.application.ReviewFacade
import org.inner.circle.o2oserver.store.application.StoreFacade
import org.inner.circle.o2oserver.store.domain.review.ReviewQueryObject
import org.inner.circle.o2oserver.store.presentation.dto.CommonListResponse
import org.inner.circle.o2oserver.store.presentation.dto.CommonResponse
import org.inner.circle.o2oserver.store.presentation.dto.StoreListRequest
import org.inner.circle.o2oserver.store.presentation.dto.StoreReviewDTO
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/store")
class StoreController(
    private val storeFacade: StoreFacade,
    private val reviewFacade: ReviewFacade,
) {
    @GetMapping("/{storeId}")
    fun getStoreDetail(
        @PathVariable storeId: Long,
    ): CommonResponse {
        val store = storeFacade.getStoreDetail(storeId)
        return CommonResponse(response = store, msg = "조회 되었습니다", statusCode = 200)
    }

    @PostMapping("")
    fun storeList(
        @Valid @RequestBody request: StoreListRequest,
        bindingResult: BindingResult
    ): CommonListResponse {
        if (bindingResult.hasErrors()) {
            throw RuntimeException(bindingResult.fieldErrors.joinToString { el -> el.defaultMessage.toString() })
        }
        val command = request.toCommand()
        val storeListInfo = storeFacade.getStoreList(command)
        return CommonListResponse(
            response = storeListInfo.stores,
            totalCount = storeListInfo.totalCount,
            size = request.size,
            page = request.page,
            statusCode = 200,
            msg = "음식점 목록을 조회했습니다.",
        )
    }

    @GetMapping("/{storeId}/reviews")
    fun getStoreDetail(
        @PathVariable("storeId") storeId: Int,
        @RequestParam("page") page: Int,
        @RequestParam("limit") size: Int?,
    ): CommonListResponse {
        val queryObject =
            ReviewQueryObject(
                storeId = storeId,
                page = page,
                limit = size?: 10,
            )

        val reviews = reviewFacade.getStoreReviewList(queryObject)
        return CommonListResponse(
            response =
                StoreReviewDTO(
                    reviews = reviews,
                    storeName = "임시 가게 이름",
                ),
            totalCount = 100,
            size = size ?: 10,
            page = page,
            statusCode = 200,
            msg = "목록을 조회했습니다.",
        )
    }
}
