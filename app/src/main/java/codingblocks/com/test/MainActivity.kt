package codingblocks.com.test

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var tasks = arrayListOf<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dphelper = Sqlitehelper(this)
        val taskdb = dphelper.writableDatabase
        tasks = TasksTable.getAllTask(taskdb)
        val taskAdapter = TaskAdapter(tasks)
        lv.adapter = taskAdapter

        button.setOnClickListener {
            TasksTable.insertTask(
                taskdb, Task(null, et.text.toString(), true)
            )
//            tasks = TasksTable.getAllTask(taskdb)
//            taskAdapter.notifyDataSetChanged()
            taskAdapter.updateTasks(TasksTable.getAllTask(taskdb))
            et.setText("")
        }
//        val arrayAdapter = ArrayAdapter<Task>(this,android.R.layout.simple_list_item_1)
//         arrayAdapter.notifyDataSetChanged()

        lv.setOnItemClickListener { parent, view, position, id ->
            if(et.text.toString()=="") {
                TasksTable.deleteTask(taskdb, position)
            }
            else {
                TasksTable.updateTask(taskdb, Task(tasks[position].id, et.text.toString(), true))
            }
            taskAdapter.updateTasks(TasksTable.getAllTask(taskdb))
            et.setText("")
            }
    }

    class TaskAdapter(var tasks: ArrayList<Task>) : BaseAdapter() {

        fun updateTasks(newTasks: ArrayList<Task>) {
            tasks.clear()
            tasks.addAll(newTasks)
            notifyDataSetChanged()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val li = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = li.inflate(android.R.layout.simple_list_item_1, parent, false)
            view.findViewById<TextView>(android.R.id.text1).text = getItem(position).task

            return view
        }

        override fun getItem(position: Int): Task = tasks[position]

        override fun getItemId(position: Int): Long = 0

        override fun getCount(): Int = tasks.size

    }
}
        //"To-do list" with adding and deleting items without SQL database
//        val list : ArrayList<String> = arrayListOf()
//        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
//
//        button.setOnClickListener {
//            if (et.text.toString() != "") {
//
//                list.add(et.text.toString())
//                et.setText("")
//                lv.adapter = adapter
//            }
//            else
//                Toast.makeText(this,"Enter value before adding!",Toast.LENGTH_LONG).show()
//        }
//        lv.setOnItemClickListener { parent, view, position, id ->
//            Toast.makeText(this,list[position]+"  deleted!",Toast.LENGTH_LONG).show()
//            list.removeAt(position)
//            adapter.notifyDataSetChanged()
//
//        }
