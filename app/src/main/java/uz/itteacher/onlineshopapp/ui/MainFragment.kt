package uz.itteacher.onlineshopapp.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnKeyListener
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.itteacher.onlineshopapp.R
import uz.itteacher.onlineshopapp.databinding.FragmentMainBinding
import uz.itteacher.onlineshopapp.model.Login

import uz.itteacher.onlineshopapp.model.Product
import uz.itteacher.onlineshopapp.model.ProductData
import uz.itteacher.onlineshopapp.model.User
import uz.itteacher.onlineshopapp.networking.APIClient
import uz.itteacher.onlineshopapp.networking.APIService


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "TAG"

class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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

        val binding = FragmentMainBinding.inflate(inflater, container, false)

        val api = APIClient.getInstance().create(APIService::class.java)
        val l = Login("hbingley1", "CQutx25i8")
//        api.login(l).enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if (response.isSuccessful && response.body() != null) {
//                    Log.d(TAG, "onResponse: ${response.body()?.username}")
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                Log.d(TAG, "onFailure: $t")
//            }
//
//        })

//        api.getAllProducts().enqueue(object : Callback<ProductData> {
//            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
//                if (response.isSuccessful && response.body() != null)
//                    Log.d(TAG, "onResponse: ${response.body()?.products?.get(0)?.title}")
//            }
//
//            override fun onFailure(call: Call<ProductData>, t: Throwable) {
//                Log.d(TAG, "onFailure: $t")
//            }
//
//        })

//        api.getProduct(6).enqueue(object : Callback<Product> {
//            override fun onResponse(call: Call<Product>, response: Response<Product>) {
//                if (response.isSuccessful && response.body() != null)
//                    Log.d(TAG, "onResponse: ${response.body()?.title}")
//            }
//
//            override fun onFailure(call: Call<Product>, t: Throwable) {
//                Log.d(TAG, "onFailure: $t")
//            }
//
//        })

        binding.sv.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText !=null) {
                    api.searchByName(newText).enqueue(object :Callback<ProductData>{
                        override fun onResponse(
                            call: Call<ProductData>,
                            response: Response<ProductData>
                        ) {
                            Log.d(TAG, "onResponse: ${response.body()?.products}")
                        }

                        override fun onFailure(call: Call<ProductData>, t: Throwable) {
                            Log.d(TAG, "onFailure: $t")
                        }

                    })
                    return true
                }
                return false
            }

        })
        return binding.root
    }


    companion object {
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}