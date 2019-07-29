package com.example.apiarchitecture

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.apiarchitecture.adapter.ContactsAdapter
import com.example.apiarchitecture.adapter.MoviesAdapter
import com.example.apiarchitecture.controller.CallBackListener
import com.example.apiarchitecture.controller.ContactsController
import com.example.apiarchitecture.controller.MoviesController
import com.example.apiarchitecture.model.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CallBackListener {

    override fun handleSuccessData(resModel: BaseModel) {

        when(resModel){

            is ResModelMovies ->{
                rcv.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = MoviesAdapter((resModel as Movies).data, this@MainActivity)
                }
            }

            is ResModelContacts ->{
                rcv.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = ContactsAdapter((resModel as ResModelContacts).contacts,this@MainActivity)
                }
            }
        }
        /*if (resModel is ResModelMovies) {
            rcv.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MoviesAdapter((resModel as Movies).data, this@MainActivity)
            }
        }
        else if (resModel is ResModelContacts){

            rcv.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ContactsAdapter((resModel as ResModelContacts).contacts,this@MainActivity)
            }
        }*/
    }

    override fun networkConnectionError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onserverConnectionError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleErrorDataFromServer(errorModel: BaseModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            callAPI()
        }

        btn_cont.setOnClickListener {
            callContactsApi()
        }



    }

    private fun callContactsApi() {
        ContactsController().startFetching(this)
    }

    private fun callAPI() {
        MoviesController().startFetching(this)
    }
}
