/*
 * Copyright  (c) 2017 Lyloou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lyloou.test.onearticle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lyloou.test.common.db.LouSQLite;

import java.util.Arrays;
import java.util.List;

public class DbCallback implements LouSQLite.ICallBack {
    public static final String TABLE_NAME_ONE_ARTICLE = "one_article";
    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TYPE_TEXT = " TEXT";
    private static final String TYPE_INTEGER = " INTEGER";
    private static final String SEP_COMMA = ",";

    private static final String TABLE_SCHEMA_ONE_ARTICLE =
            "CREATE TABLE " + TABLE_NAME_ONE_ARTICLE + " (" +
                    ArticleEntry._ID + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " +
                    ArticleEntry.COLEUM_NAME_DATE + TYPE_TEXT +SEP_COMMA +
                    ArticleEntry.COLEUM_NAME_TITLE + TYPE_TEXT + SEP_COMMA +
                    ArticleEntry.COLEUM_NAME_AUTHOR + TYPE_TEXT +
                    ")";
    private static DbCallback INSTANCE;

    private DbCallback() {
    }

    public static DbCallback getInstance() {
        if (INSTANCE == null) {
            synchronized (DbCallback.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DbCallback();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public int getVersion() {
        return DATABASE_VERSION;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public List<String> createTablesSQL() {
        return Arrays.asList(
                TABLE_SCHEMA_ONE_ARTICLE
        );
    }

    @Override
    public <T> void assignValuesByEntity(String tableName, T t, ContentValues values) {
        switch (tableName) {
            case TABLE_NAME_ONE_ARTICLE:
                if (t instanceof Article) {
                    Article t2 = (Article) t;
                    values.put(ArticleEntry.COLEUM_NAME_DATE, t2.getDate());
                    values.put(ArticleEntry.COLEUM_NAME_TITLE, t2.getTitle());
                    values.put(ArticleEntry.COLEUM_NAME_AUTHOR, t2.getAuthor());
                }
                break;
        }
    }

    @Override
    public <T> T newEntityByCursor(String tableName, Cursor cursor) {
        switch (tableName) {
            case TABLE_NAME_ONE_ARTICLE:
                String date = cursor.getString(cursor.getColumnIndex(ArticleEntry.COLEUM_NAME_DATE));
                String title = cursor.getString(cursor.getColumnIndex(ArticleEntry.COLEUM_NAME_TITLE));
                String author = cursor.getString(cursor.getColumnIndex(ArticleEntry.COLEUM_NAME_AUTHOR));
                return (T) new Article(date, author, title);
        }

        return null;
    }
}

