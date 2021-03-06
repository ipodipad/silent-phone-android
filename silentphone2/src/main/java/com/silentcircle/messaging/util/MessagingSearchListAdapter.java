/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.silentcircle.messaging.util;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

import com.silentcircle.common.list.ContactListItemView;
import com.silentcircle.silentphone2.R;
import com.silentcircle.silentphone2.list.DialerPhoneNumberListAdapter;

/**
 * List adapter to display regular search results.
 */
public class MessagingSearchListAdapter extends DialerPhoneNumberListAdapter {

    public MessagingSearchListAdapter(Context context, boolean enableConversations) {
        super(context, false);
        if (enableConversations) {
            addPartition(0, createLocalScConversationPartition());
        }
    }

    @Override
    public void setQueryString(String queryString) {
        boolean changed = setShortcutEnabled(SHORTCUT_DIRECT_CALL, false);
        // Either one of the add contacts options should be enabled. If the user entered
        // a dial-able number, then clicking add to contact should add it as a number.
        // Otherwise, it should add it to a new contact as a name.
//        changed |= setShortcutEnabled(SHORTCUT_ADD_NUMBER_TO_CONTACTS, showNumberShortcuts);

        // For NGA: Don't show the "add to contacts option"
        changed |= setShortcutEnabled(SHORTCUT_ADD_NUMBER_TO_CONTACTS, false);
        changed |= setShortcutEnabled(SHORTCUT_DIRECT_CONVERSATION,
                false);
        changed |= setShortcutEnabled(SHORTCUT_START_GROUP_CHAT, false);
        changed |= setShortcutEnabled(SHORTCUT_ADD_TO_GROUP_CHAT, false);

        if (changed) {
            notifyDataSetChanged();
        }
        super.setQueryString(queryString);
    }

    @Override
    protected ContactListItemView newView(
            Context context, int partition, Cursor cursor, int position, ViewGroup parent) {
        ContactListItemView view = super.newView(context, partition, cursor, position, parent);
        view.setBackgroundResource(R.drawable.bg_action);
        return view;
    }

    @Override
    protected void bindView(View itemView, int partition, Cursor cursor, int position) {
        super.bindView(itemView, partition, cursor, position);
    }

}
