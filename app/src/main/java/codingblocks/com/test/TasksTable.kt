package codingblocks.com.test

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class TasksTable {
    companion object{
        val TABLE_NAME="tasks"
        val CMD_CREATE="""
        CREATE TABLE $TABLE_NAME(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        task TEXT,
        done BOOLEAN);
        """.trimIndent()

    fun insertTask(db:SQLiteDatabase,task:Task){
        val row=ContentValues()
        row.put("task",task.task)
        row.put("done",task.done)
        db.insert(TABLE_NAME,null,row)
    }
    fun getAllTask(db: SQLiteDatabase):ArrayList<Task>{
        val tasks= arrayListOf<Task>()
        val cursor=db.query(TABLE_NAME, arrayOf("id","task","done"),
            null, null,
            null,
            null,
            null)
        cursor.moveToFirst()
        val idcol=cursor.getColumnIndexOrThrow("id")
        val taskcol=cursor.getColumnIndexOrThrow("task")
        val donecol=cursor.getColumnIndexOrThrow("done")
        while (cursor.moveToNext()){
            val task=Task(
                cursor.getInt(idcol),
                cursor.getString(taskcol),
                cursor.getInt(donecol)==1)
            tasks.add(task)

        }
        cursor.close()
        return tasks
    }
        fun deleteTask(db: SQLiteDatabase, id: Int) {
            db.delete(TABLE_NAME, "id", null)
        }

        fun updateTask(db:SQLiteDatabase,task:Task)
        {
            val updateRow = ContentValues().apply {
                put("task",task.task )
                put("done",true)
            }
            db.update(TABLE_NAME,updateRow,"id = ?", arrayOf(task.id.toString()))
        }
}}