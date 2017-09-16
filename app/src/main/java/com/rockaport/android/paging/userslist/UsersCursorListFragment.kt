package com.rockaport.android.paging.userslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockaport.android.paging.MainApplication
import com.rockaport.android.paging.R
import kotlinx.android.synthetic.main.fragment_users_list.view.*

class UsersCursorListFragment : Fragment() {
    private lateinit var viewModel: UsersCursorListViewModel
    private lateinit var listAdapter: UsersCursorListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_users_list, container)

        viewModel = ViewModelProviders.of(this).get(UsersCursorListViewModel::class.java)

        recyclerView = view.users_list
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return view
    }

    override fun onStart() {
        super.onStart()

        viewModel.getDataSource(MainApplication.database.usersDao()).observe(this, Observer {
            listAdapter = UsersCursorListAdapter(it!!)
            recyclerView.adapter = listAdapter
        })
    }

    override fun onStop() {
        super.onStop()

        listAdapter.close()
    }
}