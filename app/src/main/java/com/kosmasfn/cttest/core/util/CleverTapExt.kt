package com.kosmasfn.cttest.core.util

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import com.clevertap.android.sdk.CTInboxStyleConfig
import com.clevertap.android.sdk.CleverTapAPI
import com.kosmasfn.cttest.view.dashboard.HomeActivity
import java.util.Date

object CleverTapExt {
    fun onUserLogin(context: Context) {
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE)
        val profileUpdate = HashMap<String, Any>()
        profileUpdate["Name"] = "Kosmas FN" // String
        profileUpdate["Identity"] = 6200000000 // String or number
        profileUpdate["Email"] = "kosmasfn@gmail.com" // Email address of the user
        profileUpdate["Phone"] = "+620000000000" // Phone (with the country code, starting with +)
        profileUpdate["Gender"] = "M" // Can be either M or F
        profileUpdate["DOB"] = Date() // Date of Birth.
        profileUpdate["MSG-email"] = true // Disable email notifications
        profileUpdate["MSG-push"] = true // Enable push notifications
        profileUpdate["MSG-sms"] = true // Disable SMS notifications
        profileUpdate["MSG-whatsapp"] = true // Enable WhatsApp notifications

        val stuff = ArrayList<String>()
        stuff.add("bag")
        stuff.add("shoes")
        profileUpdate["MyStuff"] = stuff //ArrayList of Strings

        val otherStuff = arrayOf("Jeans", "Perfume")
        profileUpdate["MyStuff"] = otherStuff //String Array

        CleverTapAPI.getDefaultInstance(context)?.onUserLogin(profileUpdate)
    }

    fun onLoginActivity(context: Context) {
        val userData = mapOf(
            "User Name" to "kosmas",
            "Name" to "Kosmas FN",
            "Address" to "Jakarta",
            "Date Of Birth" to Date()
        )
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("Login", userData)
    }

    fun onHomePageSelected(context: Context) {
        val data = mapOf(
            "Name" to "Home Page",
            "Description" to "Product List",
            "Position" to "Left",
            "Date Selected" to Date()
        )
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("HomePageSelected", data)
    }

    fun onSearchPageSelected(context: Context) {
        // event without properties
        val data = mapOf(
            "Name" to "Search Page",
            "Select Able" to true,
            "Page Number" to 2,
            "Date Selected" to Date()
        )
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("SearchPageSelected", data)
    }

    fun onProfilePageSelected(context: Context) {
        // event without properties
        val data = mapOf(
            "Name" to "Profile Page",
            "Select Able" to true,
            "Position" to "Right",
            "Date Selected" to Date()
        )
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("ProfilePageSelected", data)
    }

    fun onProductSelected(context: Context) {
        // event without properties
        val data = mapOf(
            "Name" to "Jeans",
            "Select Able" to true,
            "Price" to 77000f,
            "Date Selected" to Date()
        )
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("ProductSelected", data)
    }

    fun onProductCheckout(context: Context) {
        // event without properties
        val data = mapOf(
            "Name" to "Jeans",
            "Select Able" to true,
            "Price" to 87000f,
            "Date Selected" to Date()
        )
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("ProductCheckout", data)
    }

    fun onInboxSelected(context: Context) {
        val data = mapOf(
            "Name" to "Inbox Page",
            "Select Able" to true
        )
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("InboxSelected", data)
    }

    fun onCharged(context: Context) {
        val charges = hashMapOf<String, Any>("Total Number Of Items" to 3, "Total Amount" to 87000f)
        val items = arrayListOf(
            hashMapOf<String, Any>(
                "Item name" to "Shoes",
                "Number of Items" to 1,
                "Amount" to 87000f
            ),
            hashMapOf<String, Any>(
                "Item name" to "Watch",
                "Number of Items" to 1,
                "Amount" to 87000f
            ),
            hashMapOf<String, Any>(
                "Item name" to "Biscuit",
                "Number of Items" to 1,
                "Amount" to 87000f
            ),
        )
        CleverTapAPI.getDefaultInstance(context)?.pushChargedEvent(charges, items)
    }

    fun requestPermissionNotification(context: Context) {
        with(CleverTapAPI.getDefaultInstance(context)?.isPushPermissionGranted) {
            if (this != true) CleverTapAPI.getDefaultInstance(context)
                ?.promptForPushPermission(true)
        }
    }

    fun registerPushPermission(context: Context, application: HomeActivity) {
        CleverTapAPI.getDefaultInstance(context)
            ?.registerPushPermissionNotificationResponseListener(application)
    }

    fun unregisterPushPermission(context: Context, activity: HomeActivity) {
        CleverTapAPI.getDefaultInstance(context)
            ?.unregisterPushPermissionNotificationResponseListener(activity)
    }

    fun pushProfile(context: Context) {
        val profileUpdate = HashMap<String, Any>()
        profileUpdate["City"] = "Jakarta"
        profileUpdate["District"] = "Jakarta"
        CleverTapAPI.getDefaultInstance(context)?.pushProfile(profileUpdate)
    }

    fun initInboxView(context: Context) {
        val inboxTabs = arrayListOf("Promotions")//Anything after the first 2 will be ignored
        CTInboxStyleConfig().apply {
            tabs = inboxTabs //Do not use this if you don't want to use tabs
            tabBackgroundColor = "#17420d"
            selectedTabIndicatorColor = "#000000"
            selectedTabColor = "#FFFFFF"
            unselectedTabColor = "#FFFFFF"
            backButtonColor = "#000000"
            navBarTitleColor = "#000000"
            navBarTitle = "My Inbox"
            navBarColor = "#FFFFFF"
            inboxBackgroundColor = "#FFFFFF"
            firstTabTitle = "All Inbox"
            CleverTapAPI.getDefaultInstance(context)?.let {
                it.showAppInbox(this)
                val firstMessageId = it.allInboxMessages?.firstOrNull()?.messageId
                //Raise Notification Viewed event for Inbox Message. Message id should be a String
                Log.i("inboxpayload", it.allInboxMessages.toString())
                firstMessageId?.also { id ->
                    it.pushInboxNotificationViewedEvent(id)
                    println("Raised Notification Viewed event For Id = $id")
                } ?: println("inboxMessage Id is null")
            }
        }
    }

    fun handleDisplayUnit(context: Context) {
        CleverTapAPI.getDefaultInstance(context)?.let {
            val displayUnitID = it.allDisplayUnits?.firstOrNull()?.unitID
            //Raise Notification Viewed event for DisplayUnit. Message id should be a String
            displayUnitID?.also { id ->
                it.pushDisplayUnitViewedEventForID(id)
                it.pushDisplayUnitClickedEventForID(id)
                println("Raised Notification Viewed event For DisplayUnit Id = $it")
            } ?: println("DisplayUnit Id is null")
        }
    }

    fun onDisplayUnitClicked(context: Context, id: String) {
        CleverTapAPI.getDefaultInstance(context)?.pushDisplayUnitClickedEventForID(id)
    }

    fun nativeDisplaySimpleMessage(context: Context) {
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("nativeDisplaySimpleMessage")
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("nativeDisplayCarouselMessage")
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("nativeDisplayMessageWithIcon")
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("nativeDisplayCustomKeyValue")
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("nativeDisplaySimpleMessageWithIconOnly")
        CleverTapAPI.getDefaultInstance(context)?.pushEvent("nativeDisplayCarouselMessageWithIconOnly")
    }
}