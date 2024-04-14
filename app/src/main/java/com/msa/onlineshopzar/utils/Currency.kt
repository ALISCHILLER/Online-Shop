package com.msa.onlineshopzar.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
/**
 * create by Ali Soleymani.
 */
// Create Ali Soleymani
class Currency(private var value: BigDecimal) {

    constructor(value: String) : this(parse(value))
    constructor(value: Number) : this(BigDecimal.valueOf(value.toDouble())
        .setScale(2, RoundingMode.HALF_UP))

    fun add(currency: Currency): Currency {
        return Currency(value.add(currency.value))
    }

    fun add(number: Number): Currency {
       val currency= Currency(number)
        return Currency(value.add(currency.value))
    }

    fun subtract(currency: Currency): Currency {
        return Currency(value.subtract(currency.value))
    }

    fun multiply(currency: Currency): Currency {
        return Currency(value.multiply(currency.value))
    }

    fun divide(currency: Currency, scale: Int = 2, roundingMode: RoundingMode = RoundingMode.HALF_UP): Currency {
        return Currency(value.divide(currency.value, scale, roundingMode))
    }

    fun toFormattedString(): String {
        val symbols = DecimalFormatSymbols.getInstance()
        symbols.groupingSeparator = ','
        val formatter = DecimalFormat("###,###.##", symbols)
        return formatter.format(value)
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        val ZERO = Currency(0)
        val ONE = Currency(1)
        val TEN = Currency(10)
        val HUNDRED = Currency(100)

        private fun parse(value: String): BigDecimal {
            val cleanedValue = value.replace(",", "")
            return try {
                BigDecimal(cleanedValue)
            } catch (e: NumberFormatException) {
                throw ParseException("Invalid currency format: $value", 0)
            }
        }
    }
}