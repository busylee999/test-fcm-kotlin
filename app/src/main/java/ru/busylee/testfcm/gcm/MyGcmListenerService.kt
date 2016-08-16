/**
 * Copyright 2015 Google Inc. All Rights Reserved.

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.busylee.share.gcm

import android.text.TextUtils
import android.util.Log

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.busylee.testfcm.MainApplication

class MyGcmListenerService : FirebaseMessagingService() {

    override fun onCreate() {
        super.onCreate()
        (application as MainApplication).component.inject(this)
    }

    /**
     * Called when message is received.

     * @param from SenderID of the sender.
     * *
     * @param data Data bundle containing message data as key/value pairs.
     * *             For Set of keys use data.keySet().
     */
    // [START receive_message]
//    override fun onMessageReceived(from: String?, data: Bundle?) {
//        val message = data!!.getString("message", "")
//        Log.d(TAG, "From: " + from!!)
//        Log.d(TAG, "Message: " + message)
//
//        if (from.startsWith("/topics/")) {
//            // message received from some topic.
//        } else {
//            // normal downstream message.
//        }
//
//        // [START_EXCLUDE]
//        /**
//         * Production applications would usually process the message here.
//         * Eg: - Syncing with server.
//         * - Store message in local database.
//         * - Update UI.
//         */
//
//        /**
//         * In some cases it may be useful to show a notification indicating to the user
//         * that a message was received.
//         */
//        handleMessage(message)
        // [END_EXCLUDE]
//    }
    // [END receive_message]

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "onMessageReceived")
        val from = remoteMessage!!.from
        val message = remoteMessage!!.data["message"]
        Log.d(TAG, "Message: " + message)

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         * - Store message in local database.
         * - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
        if(TextUtils.isEmpty(message)) {
            return
        }

        handleMessage(message!!)
    }

    /**
     * Create and show a simple notification containing the received GCM message.

     * @param message GCM message received.
     */
    private fun handleMessage(message: String) {
        if (message.startsWith(Actions.INTENT)) {
            notifyAboutNewActions()
        } else if (message.startsWith(Actions.CONNECTION)) {
            val connectedClientId = message.substring(IntRange(Actions.CONNECTION.length + 1, message.length - 1))
//            mSharedPreferencesWrapper.storeConnectedClientId(connectedClientId)
            notifyAboutNewConnection()
        }

        /*if(message.startsWith(Actions.INTENT)) {
            ApiService.Start.checkNewIntents(this);
        } else if (startsWith(Actions.CONNECTION)) {
            // Todo parse connected clientId
        }*/

        /*
        var intent = Intent()

        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, message)

        intent = Intent.createChooser(intent, null)

        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_stat_ic_notification).setContentTitle("Share Me")//Todo change
                .setAutoCancel(true).setSound(defaultSoundUri).setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
        */
    }

    private fun notifyAboutNewConnection() {
//        val registrationComplete = Intent(Consts.NEW_CONNECTED_CLIENT)
//        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete)
    }

    private fun notifyAboutNewActions() {
//        ApiService.Start.checkNewActions(this)
    }

    companion object {
        private val TAG = "MyGcmListenerService"
    }

    object Actions {
        val INTENT = "intent"
        val CONNECTION = "connection"
    }
}
