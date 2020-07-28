package com.example

import com.example.invoice.InvoiceGenerationServiceImpl
import com.example.stock.StockServiceImpl
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.features.*
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.random.Random
import kotlin.streams.asSequence

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    val stockService = StockServiceImpl(Data.products)

    val invoiceService = InvoiceGenerationServiceImpl()


    routing {

        post("/buy_items") {
            val order = call.receive<Order>()
            val selectedProducts = stockService.getExistencesFromOrder(order)

            val total = invoiceService.calculateTotal(selectedProducts)


            if (order.coupon.isNotBlank() && Data.coupons.contains(order.coupon)) {
                val invoice =
                    Invoice(
                        selectedProducts,
                        order.cash - total.apply { ((this * BigDecimal(20)) / 100.toBigDecimal()) })
                call.respond(invoice)
            } else {
                Invoice(selectedProducts, order.cash - total)
            }
        }
    }

}

object Data {
    val coupons = listOf("SALE1", "SALE2", "SALE3")
    val products = {
        (1..10).map {
            Product(
                it,
                "Nike ref ${Random.nextBytes(5).toString()}",
                Random.nextInt(100).toBigDecimal(),
                Random.nextInt(20).toBigInteger(),
                Random.nextInt(5).toBigInteger()
            )
        }
    }
}

data class Order(val products: List<Int>, val cash: BigDecimal, val coupon: String)
data class Product(
    val id: Int = 1,
    val name: String = "",
    val price: BigDecimal = BigDecimal.ZERO,
    val discount: BigInteger = BigInteger.ZERO,
    val stock: BigInteger = BigInteger.ZERO
)

data class Invoice(val products: List<Product>, val total: BigDecimal)

