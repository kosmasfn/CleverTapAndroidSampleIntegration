package com.kosmasfn.cttest.data

import android.os.Parcel
import android.os.Parcelable
import com.clevertap.android.sdk.displayunits.CTDisplayUnitType
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnitContent


class CleverTapDisplayUnit() : Parcelable {
    /**
     * Getter for the unitId of the Display Unit
     *
     * @return String
     */
    /**
     * Display unit identifier
     */
    val unitID: String? = null
    /**
     * Getter for the DisplayUnitType of the Display Unit, Refer[CTDisplayUnitType]
     *
     * @return CTDisplayUnitType
     */
    /**
     * Display Type Could be (banner,carousel,custom key value etc.)
     */
    val type: CTDisplayUnitType? = null
    /**
     * Getter for the hex-value background color of the Display Unit e.g. #000000
     *
     * @return String
     */
    /**
     * Background Color
     */
    val bgColor: String? = null
    /**
     * Getter for the list of Content Display Unit Items.
     *
     * @return ArrayList<CleverTapDisplayUnitContent>
    </CleverTapDisplayUnitContent> */
    /**
     * List of Display Content Items
     */
    val contents: ArrayList<CleverTapDisplayUnitContent>? = null
    /**
     * Getter for the Key Value pairs of the Display Unit
     *
     * @return HashMap<String></String>, String>
     */
    /**
     * Custom Key Value Pairs
     */
    val customExtras: HashMap<String, String>? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CleverTapDisplayUnit> {
        override fun createFromParcel(parcel: Parcel): CleverTapDisplayUnit {
            return CleverTapDisplayUnit(parcel)
        }

        override fun newArray(size: Int): Array<CleverTapDisplayUnit?> {
            return arrayOfNulls(size)
        }
    }
}