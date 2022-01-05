package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class stumbledb(context:Context) : SQLiteOpenHelper(context, "USERDB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT, UNAME TEXT, PWD TEXT, EMAIL TEXT, fNAME TEXT, UNIV TEXT, COURSE TEXT, GENDER TEXT, AGE INT)")
        db?.execSQL("INSERT INTO USERS(UNAME,PWD) VALUES ('user 1' , 'user123')")
        db?.execSQL("INSERT INTO USERS(UNAME,PWD) VALUES ('user 2' , 'asdqwe123')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}