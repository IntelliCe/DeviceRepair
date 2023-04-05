package com.qianyun.devicerepair.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import kotlin.reflect.KClass

fun Context.finishActivity() {
    this.findActivity()?.finish()
}

fun Context.findActivity(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> this.baseContext.findActivity()
        else -> null
    }
}

fun Context.launchActivity(activity: KClass<*>, extraScope: (Intent.() -> Unit)? = null) {
    val intent = Intent(this, activity.java)
    extraScope?.let { intent.it() }
    this.startActivity(intent)
}

fun Context.launchActivityThenFinishSelf(activity: KClass<*>, extraScope: (Intent.() -> Unit)? = null) {
    this.launchActivity(activity, extraScope)
    this.findActivity()?.finish()
}

fun Context.finishSelf() {
    this.findActivity()?.finish()
}

fun Context.setResult(resultCode: Int, intentScope: Intent.() -> Unit) {
    this.findActivity()?.apply { setResult(resultCode, Intent().apply(intentScope)) }
}

fun Context.dp2px(dp: Int): Int = (this.resources.displayMetrics.density * dp + 0.5f).toInt()