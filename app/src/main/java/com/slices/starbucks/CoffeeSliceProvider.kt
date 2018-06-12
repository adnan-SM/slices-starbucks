package com.slices.starbucks

import android.content.Context
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder

/**
 * @author Adnan A M
 * @since  12/06/18
 */
class CoffeeSliceProvider : SliceProvider() {

    private lateinit var coffeeContext : Context
    private val customerName = "Adnan"
    private val servingSize = "Grande"
    private val coffeeName = "Caramel Frappuccino"

    override fun onCreateSliceProvider(): Boolean {

        coffeeContext = context
        return true

    }

    override fun onBindSlice(sliceUri: Uri): Slice? {

        when(sliceUri.path) {

            "/coffee" ->  return createCoffeeSlice(sliceUri)

        }

        return null

    }

    private fun createCoffeeSlice(sliceUri: Uri) : Slice? {

        return ListBuilder(coffeeContext, sliceUri)
                .setHeader {
                    it.apply {

                        setTitle("Hello $customerName, here is your coffee !")

                    }
                }
                .addGridRow {
                    it.apply {
                        addCell {
                            it.apply {
                                addImage(IconCompat.createWithResource(coffeeContext,
                                        R.drawable.starbucks_coffee), ListBuilder.LARGE_IMAGE)
                            }
                        }
                    }
                }.addRow {
                    it.apply {
                        setTitle(coffeeName)
                        setSubtitle("Size - $servingSize")
                    }
                }.build()
    }
}