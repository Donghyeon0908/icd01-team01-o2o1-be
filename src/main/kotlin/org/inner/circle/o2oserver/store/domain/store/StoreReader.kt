package org.inner.circle.o2oserver.store.domain.store

import org.inner.circle.o2oserver.store.domain.store.command.StoreListCommand

interface StoreReader {
    fun getStoreDetail(storeId: Long): Store

    fun getStoreList(command: StoreListCommand): StoreListInfo

    fun getStoreListWithLocationAndName(command: StoreListCommand): StoreListInfo
}
