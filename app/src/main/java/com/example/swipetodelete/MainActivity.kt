package com.example.swipetodelete

import android.content.Context
import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdacciaro.iOSDialog.iOSDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

/*
Created by ​
Hannure Abdulrahim


on 10/20/2021.
 */

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val simpleAdapter = SimpleAdapter((1..5).map { "Item: $it" }.toMutableList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = simpleAdapter

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                showAlertDialog(title = getString(R.string.delete_confirmation_title),
                    message = getString(R.string.delete_confirmation_msg),
                    positiveButtonText = getString(R.string.yes),
                    negativeButtonText = getString(R.string.no),
                    this@MainActivity,
                    viewHolder

                    )

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        addItemBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.addItemBtn -> {
                  val itemCount = simpleAdapter.itemCount + 1
                simpleAdapter.addItem("New item Added : "+itemCount)
            }

        }
    }


    fun showAlertDialog(
        title: String = "Delete!",
        message: String = "Are you sure ?",
        positiveButtonText: String = "Delete",
        negativeButtonText: String = "Cancel",
        context: Context,
        viewHolder: RecyclerView.ViewHolder,)
    {

        iOSDialogBuilder(context)
            .setTitle(title)
            .setSubtitle(message)
            .setBoldPositiveLabel(true)
            .setCancelable(false)
            .setPositiveListener(positiveButtonText) { dialog ->
                Toast.makeText(context, "Item Deleted!", Toast.LENGTH_LONG).show()

                //##########
                ///delete item here
                val adapter = recyclerView.adapter as SimpleAdapter
                adapter.removeAt(viewHolder.adapterPosition)

                //##########
                dialog.dismiss()
            }
            .setNegativeListener(
                negativeButtonText
            ) { dialog ->
                //##########
                /// reset if you are not deleting item
                recyclerView.adapter!!.notifyItemChanged(viewHolder.adapterPosition)
                Toast.makeText(context, "Dismiss, No action taken.", Toast.LENGTH_SHORT).show()

                //##########
                dialog.dismiss() }
            .build().show()

    }


}
