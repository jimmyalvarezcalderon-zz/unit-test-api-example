package com.example.invoice

import com.example.Product
import java.math.BigDecimal

interface InvoiceGenerationService {

    fun calculateTotal(products: List<Product>): BigDecimal

    fun applyCoupon(total: BigDecimal, coupon: String): BigDecimal

    fun generateInvoice(products: List<Product>, cash: BigDecimal, total: BigDecimal )
}