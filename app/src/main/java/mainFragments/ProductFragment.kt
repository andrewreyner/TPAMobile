package mainFragments

import android.app.ActionBar
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.MatrixCursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.CursorAdapter
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.adapters.ProductAdapter
import com.tpa.HelepDoc.models.Cart
import com.tpa.HelepDoc.models.Product

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var rvProduct: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var products:ArrayList<Product>
    private lateinit var PRODUCTS:ArrayList<Product>
    private lateinit var productNames:ArrayList<String>
    private val drugDatabaseRef=  FirebaseDatabase.getInstance().getReference("products")
    private val drugStorageReference = FirebaseStorage.getInstance().getReference("products")

    private lateinit var svProduct: SearchView

    private lateinit var acTextView: AutoCompleteTextView
    private lateinit var acAdapter:ArrayAdapter<String>
    private lateinit var myView:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productNames = ArrayList()
        setHasOptionsMenu(true)
        val view:View =  inflater.inflate(R.layout.fragment_product, container, false)
        myView = view
//        svProduct = view.findViewById(R.id.sv_product)
        rvProduct  =  view.findViewById(R.id.rv_product)
        products = ArrayList()
        PRODUCTS = ArrayList()





        drugDatabaseRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(dataSnapShot: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (p in dataSnapshot.children) {
                        // MAKE SURE THE DRUG Constructor is Initialize -> check on the models
                        val product = p.getValue(Product::class.java)
                        products.add(product!!)
                    }
                    PRODUCTS.addAll(products)
                    for (p in PRODUCTS) {
                        productNames.add(p.name)
                    }
                    productAdapter = ProductAdapter(products, view.context!!)
                    rvProduct.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(view.context, 2)
                        adapter = productAdapter
                    }
                }
            }
        })



//        svProduct.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                updateDatas(query)
//
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                updateDatas(newText)
//                return false
//            }
//
//        })




        return view

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.product_menu,menu)

        val searchItem = menu.findItem(R.id.sv_product)
        svProduct = searchItem?.actionView as SearchView
        svProduct.queryHint = "search"
        val tv = svProduct.findViewById<AutoCompleteTextView>(androidx.appcompat.R.id.search_src_text)
        tv.threshold = 1


        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.item_label)
        val cursorAdapter = SimpleCursorAdapter(context, R.layout.search_product_layout, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)
        svProduct.suggestionsAdapter =  cursorAdapter
        val suggestions = listOf("Apple", "Blueberry", "Carrot", "Daikon")
        svProduct.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                updateDatas(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val cursor  = MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                newText?.let{
                    suggestions.forEachIndexed { index, suggestion ->
                        if (suggestion.contains(newText, true))
                            cursor.addRow(arrayOf(index, suggestion))
                    }
                }
                cursorAdapter.changeCursor(cursor)
                updateDatas(newText)
                return true
            }

        })
        svProduct.setOnSuggestionListener(object: SearchView.OnSuggestionListener{
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val cursor  = svProduct.suggestionsAdapter.getItem(position)  as Cursor
                val selection = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                svProduct.setQuery(selection, false)

                return true

            }


        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    fun updateDatas(query:String?){
        products.removeAll(products)
        if(query == null){
            products.addAll(PRODUCTS)
        }else{
            for(p in PRODUCTS){
                if(p.name.toUpperCase().contains(query!!.toUpperCase())){
                    products.add(p)
                }
            }
        }
        productAdapter.notifyDataSetChanged()

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        var carts :ArrayList<Cart>  = ArrayList()
    }
}
