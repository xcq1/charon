/*
 * Copyright 2017 Tobias Kuhn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xcq1.charon.api

import feign.Param
import feign.RequestLine
import java.math.BigDecimal
import java.util.*

interface CharonWarehouseService {

    @RequestLine("GET /warehouse/all")
    fun getAllItems(): Map<CharonItemResource, Int>

    @RequestLine("POST /warehouse/add")
    fun addNewItem(item: CharonItemResource, quantity: Int)

    @RequestLine("POST /warehouse/remove")
    fun removeItem(item: UUID): Int

    @RequestLine("POST /warehouse/replenish")
    fun replenishItem(item: UUID, additionalQuantity: Int)

    @RequestLine("POST /warehouse/reserve")
    fun reserveItem(item: UUID, reserveQuantity: Int)

}

interface CharonCartService {

    @RequestLine("GET /cart/items/{account}")
    fun getItems(@Param("account") account: UUID): Collection<UUID>

    @RequestLine("POST /cart/add/{account}")
    fun addItem(@Param("account") account: UUID, item: UUID, quantity: Int)

    @RequestLine("POST /cart/remove/{account}")
    fun removeItem(@Param("account") account: UUID, item: UUID)

    @RequestLine("POST /cart/order/{account}")
    fun order(@Param("account") account: UUID): Boolean

    @RequestLine("POST /cart/timeout/{account}")
    fun timeout(@Param("account") account: UUID)

}

interface CharonAccountService {

    @RequestLine("GET /account/balance/{account}")
    fun getBalance(@Param("account") account: UUID): BigDecimal

    @RequestLine("GET /account/history/{account}")
    fun getOrderHistory(@Param("account") account: UUID): Collection<Collection<UUID>>

    @RequestLine("POST /account/transfer/{account}")
    fun transfer(@Param("account") account: UUID, balance: BigDecimal)

}