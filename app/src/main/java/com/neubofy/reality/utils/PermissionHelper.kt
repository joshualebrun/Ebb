package com.neubofy.reality.utils

import android.accessibilityservice.AccessibilityService
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.neubofy.reality.services.AppBlockerService

object PermissionHelper {

    fun checkAndPromptForCore(activity: Activity, checkAccessibility: Boolean = true) {
        // 1. Accessibility Service
        if (checkAccessibility && !isAccessibilityServiceEnabled(activity, AppBlockerService::class.java)) {
            MaterialAlertDialogBuilder(activity)
                .setTitle("Accessibility Required")
                .setMessage("Ebb needs Accessibility permission to block apps.\n\n1. Tap 'Enable'.\n2. Find 'Ebb' or 'Installed Apps'.\n3. Turn ON 'Ebb Blocker'.")
                .setPositiveButton("Enable") { _, _ ->
                    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                    activity.startActivity(intent)
                }
                .setNegativeButton("Cancel", null)
                .show()
            return
        }


        // 3. Overlay Permission
        if (!Settings.canDrawOverlays(activity)) {
            MaterialAlertDialogBuilder(activity)
                .setTitle("Overlay Permission Required")
                .setMessage("To show the block screen, Ebb needs 'Display over other apps' permission.")
                .setPositiveButton("Grant") { _, _ ->
                     activity.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, android.net.Uri.parse("package:${activity.packageName}")))
                }
                .setNegativeButton("Cancel", null)
                .show()
            return
        }
    }

    private fun isAccessibilityServiceEnabled(context: Context, service: Class<out AccessibilityService>): Boolean {
        val expectedComponentName = ComponentName(context, service)
        val enabledServices = Settings.Secure.getString(context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        return enabledServices?.contains(expectedComponentName.flattenToString()) == true
    }
}
