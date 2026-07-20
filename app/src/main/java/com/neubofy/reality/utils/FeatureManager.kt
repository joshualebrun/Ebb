package com.neubofy.reality.utils

import android.content.Context
import android.content.SharedPreferences

class FeatureManager(private val context: Context) {
    private val prefs: SharedPreferences = SecurePreferences.get(context, "reality_features")

    fun isAiEnabled(): Boolean = false
    fun setAiEnabled(enabled: Boolean) {}

    fun isRealityProEnabled(): Boolean = false
    fun setRealityProEnabled(enabled: Boolean) {}

    fun getTrialStartTime(): Long = 0L
    fun getTrialEndTime(): Long = 0L
    fun getRealityProStartTime(): Long = 0L
    fun setRealityProStartTime(timeMs: Long) {}
    fun getRealityProEndTime(): Long = 0L
    fun isRealityProVerified(): Boolean = false
    fun setRealityProVerified(verified: Boolean, currentTimeMs: Long = SecureTimeProvider.currentTimeMillis(context), months: Int = 12) {}
    fun isTrialActive(): Boolean = false
    fun hasUsedTrial(): Boolean = false

    fun isTapasyaEnabled(): Boolean = false
    fun setTapasyaEnabled(enabled: Boolean) {}

    fun isReminderEnabled(): Boolean = false
    fun setReminderEnabled(enabled: Boolean) {}

    fun isHealthConnectEnabled(): Boolean = false
    fun setHealthConnectEnabled(enabled: Boolean) {}
}
