package com.slices.starbucks

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction

/**
 * @author Adnan A M
 * @since  12/06/18
 */
class CoffeeSliceProvider : SliceProvider() {

    private lateinit var coffeeContext : Context
    private val customerName = "Adnan"
    private val servingSize = "Grande"
    private val coffeeName = "Caramel Frappuccino"
    private val requestCode = 1336

    /**
     * This function is used to setup everything before the
     * actual call to onBindSlice. Use it to setup things
     * you would need. As an example I set the context in a
     * local variable in this demo.
     */
    override fun onCreateSliceProvider(): Boolean {

        coffeeContext = context
        return true

    }

    /**
     *  This is the most important method, all the magic happens here.
     *  Based on the path return the appropriate slice.
     */
    override fun onBindSlice(sliceUri: Uri): Slice? {

        when(sliceUri.path) {

            "/coffee" ->  return createCoffeeSlice(sliceUri)

        }

        return null

    }

    /**
     *  This function is a helper function to build the slice and UI
     */
    private fun createCoffeeSlice(sliceUri: Uri) : Slice? {

        // Building actions to be added
        val buyAction = SliceAction(getBuyCoffeeIntent(), IconCompat.createWithResource(coffeeContext, R.drawable.buynow), "Buy Now")
        val mainAction = SliceAction(getBuyCoffeeIntent(), IconCompat.createWithResource(coffeeContext, R.drawable.buyicon), "Open App")

        // Build the ListBuilder and return the slice generated
        return ListBuilder(coffeeContext, sliceUri)
                .setHeader {
                    it.apply {
                        setTitle("Hello $customerName, here is your coffee !")
                        setPrimaryAction(mainAction)
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
                        addEndItem(buyAction)
                    }
                }.build()
    }

    /**
     *  This function builds a pending intent that can be
     *  used to bind actions from the Slice
     */
    private fun getBuyCoffeeIntent() : PendingIntent {

        val intent = Intent(coffeeContext, MainActivity::class.java)
        return PendingIntent.getActivity(coffeeContext, requestCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT)

    }
}