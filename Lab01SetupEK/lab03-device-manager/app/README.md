# DeviceManager - CSC303 Lab 03
## Overview
DeviceManager is an Android application built in Java demonstrating Activity navigation, explicit and implicit Intents, data passing via Intent Extras, Activity Result API handling, input validation, lifecycle callbacks, and state preservation across configuration changes.
## Screens
1. **MainActivity:** Displays student details (Esha Khalid, FA23-BCE-030) and device summary (`Device Name` and `Battery Percentage`). Contains buttons to launch `EditDeviceActivity` for editing data or launch an implicit web Intent for documentation.
2. **EditDeviceActivity:** Form screen containing `EditText` inputs for editing device name and battery percentage, with built-in input validation for save operations and a cancel option.
## How to Run
1. Open project in Android Studio.
2. Select target device / emulator (**Medium Phone API 34** or higher).
3. Click **Run 'app'** (`Shift + F10` / `Control + R`).
## Tested Deployment Target
* **Emulator Device:** Medium Phone
* **API Level:** 34 (Android 14.0) / Java 17
## Lab Results Summary (T1 - T8)
* **T1 Project Setup:** Project `DeviceManager` configured under `com.example.devicemanager`.
* **T2 Manifest & Layouts:** Manifest updated; `activity_main.xml` and `activity_edit_device.xml` constructed with string resources.
* **T3 Explicit Intent & Data Transfer:** `MainActivity` launches `EditDeviceActivity` passing device name and battery extras.
* **T4 Form Population & Validation:** `EditDeviceActivity` pre-fills fields from extras and validates updated inputs upon clicking Save.
* **T5 Activity Result API:** Used `registerForActivityResult` and `ActivityResultContracts.StartActivityForResult()` to receive updated data (`RESULT_OK`) or drop changes (`RESULT_CANCELED`).
* **T6 Implicit Intent:** Opened `https://developer.android.com` via `ACTION_VIEW` safely handled with `try-catch`.
* **T7 Lifecycle Tracking:** Implemented lifecycle logging callbacks under `MainActivityLC` and `EditDeviceActivityLC` tags.
* **T8 Input Validation Edge Cases:** Validated blank names, empty numbers, invalid format, and out-of-bounds battery values [0, 100].
## Diagnosed Issue & Resolution
* **Issue:** Screen rotation reset edited values back to initial defaults (`Sensor-A`, `15%`).
* **Resolution:** Implemented `onSaveInstanceState()` to persist current `deviceName` and `battery` values into the Bundle, restoring them in `onCreate()` when `savedInstanceState != null`.