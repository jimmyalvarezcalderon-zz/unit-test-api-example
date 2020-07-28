package com.example.invoice

import com.example.Product
import java.math.BigDecimal

class InvoiceGenerationServiceImpl : InvoiceGenerationService {
    override fun calculateTotal(products: List<Product>): BigDecimal {
        return products.map { it.price - ((it.price * it.discount.toBigDecimal()) / 100.toBigDecimal()) }
            .fold(BigDecimal.ZERO, { v1, v2 -> v1.plus(v2) })
    }

    override fun applyCoupon(total: BigDecimal, coupon: String): BigDecimal {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generateInvoice(products: List<Product>, cash: BigDecimal, total: BigDecimal) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}