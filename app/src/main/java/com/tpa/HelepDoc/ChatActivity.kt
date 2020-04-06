package com.tpa.HelepDoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.tpa.HelepDoc.adapters.ChatAdapter
import com.tpa.HelepDoc.chatFragments.ChatFragment
import com.tpa.HelepDoc.chatFragments.DoctorFragment

class ChatActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var fragmentList: ArrayList<Fragment>
    private lateinit var titles: ArrayList<String>

    private lateinit var chatAdapter:ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initVars()
        connectAdapter()


    }


    private fun initVars() {
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        fragmentList = ArrayList()
        titles = ArrayList()
        fragmentList.add(DoctorFragment())
        titles.add("Doctors")
        fragmentList.add(ChatFragment())
        titles.add("Chats")
    }

    private fun connectAdapter() {
        chatAdapter = ChatAdapter(supportFragmentManager, fragmentList, titles)
        viewPager.adapter = chatAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
