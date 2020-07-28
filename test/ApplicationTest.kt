package com.example

import com.example.invoice.InvoiceGenerationServiceImpl
import com.example.stock.StockServiceImpl
import kotlin.test.*
import org.junit.Assert
import java.math.BigDecimal

class ApplicationTest {

    @Test
    fun when_there_are_not_products_in_stock() {
//        Given
        val productsDependency: () -> List<Product> = { emptyList() }
        val stockService = StockServiceImpl(productsDependency)
        val order = Order(listOf(1, 2, 3), BigDecimal(40000), "")
//        When
        val productsExistences = stockService.getExistencesFromOrder(order)
//        Then
        Assert.assertEquals("Invalid stock verification", emptyList<Product>(), productsExistences)
    }


    @Test
    fun calculate_total_when_there_are_no_products() {
//        Given
        val products = emptyList<Product>()
        val stockService = InvoiceGenerationServiceImpl()
//        When
        val actualTotal = stockService.calculateTotal(products)
//        Then
        Assert.assertEquals("Invalid stock verification", BigDecimal.ZERO, actualTotal)
    }

}
