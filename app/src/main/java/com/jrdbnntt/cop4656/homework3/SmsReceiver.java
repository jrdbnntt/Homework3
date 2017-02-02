package com.jrdbnntt.cop4656.homework3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony.Sms;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] texts = Sms.Intents.getMessagesFromIntent(intent);


        if (texts.length == 0) {
            return;
        }

        // Start new activity with url from text
        Intent myIntent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.ARG_URL, texts[0].getMessageBody());
        myIntent.putExtras(bundle);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }
}
