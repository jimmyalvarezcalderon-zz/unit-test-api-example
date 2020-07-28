package com.example.stock

import com.example.Order
import com.example.Product
import java.math.BigInteger

class StockServiceImpl(private val getProducts: () -> List<Product>) : StockService {

    override fun getExistencesFromOrder(order: Order): List<Product> {
        return getProducts()
            .filter { order.products.contains(it.id) }
            .filter { it.stock > BigInteger.ZERO }
    }

}