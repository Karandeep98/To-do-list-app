package codingblocks.com.test

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Sqlitehelper (context: Context):SQLiteOpenHelper(context,"task.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(TasksTable.CMD_CREATE)
            }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}