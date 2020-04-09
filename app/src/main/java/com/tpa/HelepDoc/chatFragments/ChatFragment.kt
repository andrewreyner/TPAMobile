package com.tpa.HelepDoc.chatFragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.adapters.ChatAdapter
import com.tpa.HelepDoc.adapters.LatestChatAdapter
import com.tpa.HelepDoc.models.Chat
import com.tpa.HelepDoc.models.Message
import kotlin.math.log
import kotlin.reflect.typeOf

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null




    private val CURRENTID:String = "-M43c_mp8Ur1bDV3PksP" // USER

    private lateinit var chatReference:DatabaseReference


    // Components
    private lateinit var rvChats:RecyclerView


    private lateinit var chats:ArrayList<Chat>
    private lateinit var latestChatAdapter: LatestChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_chat, container, false)

        chatReference =  FirebaseDatabase.getInstance().getReference("chats")
        rvChats = view.findViewById(R.id.rv_chats)
        chats = ArrayList()


        chatReference.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                chats.removeAll(chats)
                if(p0.exists()){
                    for(c in p0.children){
                        val chat  = Chat()
                        chat.doctorId = c.child("doctorId").value.toString()
                        chat.userId = c.child("userId").value.toString()


                        val messagesSS: DataSnapshot = c.child("messages")

                        for(m in messagesSS.children){
                            val message: Message =  m.getValue(Message::class.java)!!
                            chat.messages!!.add(message)
                        }

                        chats.add(chat)
                    }
                }
                latestChatAdapter = LatestChatAdapter(chats,CURRENTID, view.context)
                rvChats.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(view.context)
                    adapter  =latestChatAdapter
                }


            }

        })
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
