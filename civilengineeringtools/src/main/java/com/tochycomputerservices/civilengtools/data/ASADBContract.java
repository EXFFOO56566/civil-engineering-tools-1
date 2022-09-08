package com.tochycomputerservices.civilengtools.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/*
 *  Author: Eze-Odikwa Tochukwu
 *  Last date modified: 05-03-2022
 *  (C), All rights reserved, Tochy computer services 2022
 *
 * */
public class ASADBContract {

    public static final String CONTENT_AUTHORITY = "io.github.rhemon.aggregatesieveanalysis";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String ASADATA_PATH = "ASADATA";

    private ASADBContract() {}

    public static final class ASADBEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, ASADATA_PATH);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + ASADATA_PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + ASADATA_PATH;


        public static final String TABLE_NAME = "asadata";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ASADATA_TITLE = "title";
        public static final String COLUMN_ASADATA_DESC = "desc";
        public static final String COLUMN_ASDADATA_JSONOBJECT = "jsonobject";


    }


}
