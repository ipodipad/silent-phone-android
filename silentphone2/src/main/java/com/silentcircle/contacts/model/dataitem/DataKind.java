/*
Copyright (C) 2014-2015, Silent Circle, LLC. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Any redistribution, use, or modification is done solely for personal
      benefit and not for any commercial purpose or for monetary gain
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name Silent Circle nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL SILENT CIRCLE, LLC BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/*
 * This  implementation is edited version of original Android sources.
 */

/*
 * Copyright (C) 2011 The Android Open Source Project
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

package com.silentcircle.contacts.model.dataitem;

import android.content.ContentValues;

import com.google.common.collect.Iterators;
import com.silentcircle.contacts.model.account.AccountType.EditField;
import com.silentcircle.contacts.model.account.AccountType.EditType;
import com.silentcircle.contacts.model.account.AccountType.StringInflater;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Description of a specific data type, usually marked by a unique
 * {@link com.silentcircle.silentcontacts.ScContactsContract.Data#MIMETYPE}. Includes details about how to view and edit
 * {@link com.silentcircle.silentcontacts.ScContactsContract.Data} rows of this kind, including the possible {@link EditType}
 * labels and editable {@link EditField}.
 */
public final class DataKind {

    public static final String PSEUDO_MIME_TYPE_DISPLAY_NAME = "#displayName";
    public static final String PSEUDO_MIME_TYPE_PHONETIC_NAME = "#phoneticName";
    public static final String PSEUDO_COLUMN_PHONETIC_NAME = "#phoneticName";

    public String resourcePackageName;
    public String mimeType;
    public int titleRes;
    public int iconAltRes;
    public int iconAltDescriptionRes;
    public int weight;
    public boolean editable;

    public StringInflater actionHeader;
    public StringInflater actionAltHeader;
    public StringInflater actionBody;

    public boolean actionBodySocial = false;

    public String typeColumn;

    /**
     * Maximum number of values allowed in the list. -1 represents infinity.
     */
    public int typeOverallMax;

    public List<EditType> typeList;
    public List<EditField> fieldList;

    public ContentValues defaultValues;

    /** Layout resource id for an editor view to edit this {@link DataKind}. */
    public final int editorLayoutResourceId;

    /**
     * If this is a date field, this specifies the format of the date when saving. The
     * date includes year, month and day. If this is not a date field or the date field is not
     * editable, this value should be ignored.
     */
    public SimpleDateFormat dateFormatWithoutYear;

    /**
     * If this is a date field, this specifies the format of the date when saving. The
     * date includes month and day. If this is not a date field, the field is not editable or
     * dates without year are not supported, this value should be ignored.
     */
    public SimpleDateFormat dateFormatWithYear;

    /**
     * The number of lines available for displaying this kind of data in a
     * { @link ContactDetailFragment} (and possibly elsewhere)
     * Defaults to 1.
     */
    public int maxLinesForDisplay;

    public DataKind() {
        editorLayoutResourceId = 111; // TODO R.layout.text_fields_editor_view;
        maxLinesForDisplay = 1;
    }

    public DataKind(String mimeType, int titleRes, int weight, boolean editable, int editorLayoutResourceId) {
        this.mimeType = mimeType;
        this.titleRes = titleRes;
        this.weight = weight;
        this.editable = editable;
        this.typeOverallMax = -1;
        this.editorLayoutResourceId = editorLayoutResourceId;
        maxLinesForDisplay = 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DataKind:");
        sb.append(" resPackageName=").append(resourcePackageName);
        sb.append(" mimeType=").append(mimeType);
        sb.append(" titleRes=").append(titleRes);
        sb.append(" iconAltRes=").append(iconAltRes);
        sb.append(" iconAltDescriptionRes=").append(iconAltDescriptionRes);
        sb.append(" weight=").append(weight);
        sb.append(" editable=").append(editable);
        sb.append(" actionHeader=").append(actionHeader);
        sb.append(" actionAltHeader=").append(actionAltHeader);
        sb.append(" actionBody=").append(actionBody);
        sb.append(" actionBodySocial=").append(actionBodySocial);
        sb.append(" typeColumn=").append(typeColumn);
        sb.append(" typeOverallMax=").append(typeOverallMax);
        sb.append(" typeList=").append(toString(typeList));
        sb.append(" fieldList=").append(toString(fieldList));
        sb.append(" defaultValues=").append(defaultValues);
        sb.append(" editorLayoutResourceId=").append(editorLayoutResourceId);
        sb.append(" dateFormatWithoutYear=").append(toString(dateFormatWithoutYear));
        sb.append(" dateFormatWithYear=").append(toString(dateFormatWithYear));

        return sb.toString();
    }

    public static String toString(SimpleDateFormat format) {
        return format == null ? "(null)" : format.toPattern();
    }

    public static String toString(Iterable<?> list) {
        if (list == null) {
            return "(null)";
        } else {
            return Iterators.toString(list.iterator());
        }
    }
}