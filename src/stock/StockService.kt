package com.example.stock

import com.example.Order
import com.example.Product

interface StockService {
    fun getExistencesFromOrder(order: Order): List<Product>
}