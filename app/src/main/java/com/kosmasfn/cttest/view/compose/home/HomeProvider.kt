package com.kosmasfn.cttest.view.compose.home

import androidx.compose.runtime.staticCompositionLocalOf
import com.clevertap.android.sdk.displayunits.DisplayUnitListener

val HomeInterface = staticCompositionLocalOf<DisplayUnitListener> { error("Not provided") }
