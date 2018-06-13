/*
 *
 *  Copyright (c) 2016 SameBits UG. All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.samebits.beacon.locator.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.samebits.beacon.locator.BeaconLocatorApp;
import com.samebits.beacon.locator.R;
import com.samebits.beacon.locator.action.ActionExecutor;
import com.samebits.beacon.locator.action.IAction;
import com.samebits.beacon.locator.data.DataManager;
import com.samebits.beacon.locator.model.ActionBeacon;
import com.samebits.beacon.locator.model.RegionName;
import com.samebits.beacon.locator.notification.NotificationUtils;
import com.samebits.beacon.locator.util.Constants;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;

import java.util.List;


/**
 * Created by vitas on 02/01/16.
 */
public class BeaconRegionReceiver extends BroadcastReceiver {

    ActionExecutor mActionExecutor;
    DataManager mDataManager;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(Constants.TAG, "BeaconRegionReceiver onReceive");
        //TODO
        if (intent.hasExtra("BEACOM")) {
            Beacon beacon = intent.getParcelableExtra("BEACOM");
            String action = "";
            if (intent != null && intent.getAction() != null) action = intent.getAction();

            String title;
            String content;
            int id;
            switch (action) {
                case Constants.NOTIFY_BEACON_LEAVES_REGION:
                    Log.i(Constants.TAG, "NOTIFY_BEACON_LEAVES_REGION");
                    title = context.getString(R.string.notify_leave_region_title);
                    content = context.getString(R.string.notify_leave_region_content) + " " + beacon.getBluetoothName();
                    id = Constants.NOTIFY_ID_LEAVES_REGION;
                    updateNotificationValue(context, id, title, content);
                    break;
                case Constants.NOTIFY_BEACON_ENTERS_REGION:
                    Log.i(Constants.TAG, "NOTIFY_BEACON_ENTERS_REGION");
                    title = context.getString(R.string.notify_enter_region_title);
                    content = context.getString(R.string.notify_enter_region_content) + " " + beacon.getBluetoothName();;
                    id = Constants.NOTIFY_ID_ENTERS_REGION;
                    updateNotificationValue(context, id, title, content);
                    break;
                case Constants.NOTIFY_BEACON_NEAR_YOU_REGION:
                    Log.i(Constants.TAG, "NOTIFY_BEACON_NEAR_YOU_REGION");
                    title = context.getString(R.string.notify_near_region_title);
                    content = context.getString(R.string.notify_near_region_content) + " " + beacon.getBluetoothName();;
                    id = Constants.NOTIFY_ID_NEAR_REGION;
                    updateNotificationValue(context, id, title, content);
                    break;
                default:
                    break;
            }
        }
    }

    public static void updateNotificationValue(Context context, int eventID, String title, String content) {
        Log.i(Constants.TAG, "updateNotificationValue");
        NotificationUtils.addEventNotification(context, eventID, title, content);
    }
}
