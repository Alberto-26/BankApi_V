package com.kodin.mobilbank.ui.userDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.databinding.FragmentUsersDetailsBinding
import com.kodin.mobilbank.model.userDetailsBank.UsersDetailsItem
import com.kodin.mobilbank.util.*


import dagger.hilt.android.AndroidEntryPoint
import hilt_aggregated_deps._com_kodin_mobilbank_ui_UserAcountDetail_UserAccountTransactionViewModel_HiltModules_KeyModule
import kotlinx.coroutines.runBlocking

private const val TAG = "UsersDetailsFragment"

@AndroidEntryPoint
class UsersDetailsFragment : Fragment()  ,  RecyclerViewClickListener,ErrApiRest{

//    private val args by navArgs<UpdateInsertFragmentArgs>()




    private lateinit var _binding: FragmentUsersDetailsBinding



    private val binding get() = _binding!!
    private var myRoles: User? = null
    private lateinit var navController: NavController
    private val viewModel: UserDetailViewModel by viewModels()
    private lateinit var mUserAdapter: AdapterUserDetail
    private lateinit var globalRecyclerId: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    //    return inflater.inflate(R.layout.fragment_users_details, container, false)
        _binding = FragmentUsersDetailsBinding.inflate(inflater, container, false)
        myRoleFun()
        return binding.root
    }



    fun myRoleFun() = runBlocking {
        if (myRoles == null) {
            Coroutines.main {
                runBlocking {
                    myRoles = viewModel.getUserRol()
                }
            }
        }
    }

    fun cargarAdapter() {

        mUserAdapter = AdapterUserDetail(DataUtil.listUsersStatic as ArrayList<UsersDetailsItem>,this)
        globalRecyclerId.adapter=mUserAdapter
        (globalRecyclerId.adapter as AdapterUserDetail) .notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        val decoration = DividerItemDecoration(context, linearLayoutManager.orientation)

        super.onViewCreated(view, savedInstanceState)
       globalRecyclerId= binding.idUserFound
        globalRecyclerId.layoutManager =LinearLayoutManager(requireContext())
        globalRecyclerId.addItemDecoration(decoration)
        globalRecyclerId.setHasFixedSize(true)
        setHasOptionsMenu(true)



    }

    private fun listaItems() { // lamado desde onResume
     if (DataUtil.listUsersStatic.size==0){
         Coroutines.main {
             try {
                 binding.progressfBar.show()
                 myRoleFun()
                 viewModel.obtenerListaRoles(myRoles)?.await()?.observe( requireParentFragment().viewLifecycleOwner,
                         Observer {     listar ->
                             binding.progressfBar.hide()
                             myList(listar)// llama a cargarAdapter()
                         }
                     )

             }catch (e: ApiException) {
                 binding.rootLayout.snackbar(e.message.toString())
                 binding.progressfBar.hide()


             }catch (e: NoInternetException) {
                 binding.rootLayout.snackbar(e.message.toString())
                 binding.progressfBar.hide()
             }

         }
     } else {
         binding.progressfBar.hide()
         cargarAdapter()
     }
     }


    private fun myList(it: List<UsersDetailsItem>?) {
        if (it != null) {
            DataUtil.listUsersStatic.addAll(it.toMutableList())

        }
        cargarAdapter()
    }

    override fun onRecyclerViewItemClick(view: Int, items: UsersDetailsItem, position: Int) {


        Log.d(TAG, "onRecyclerViewItemClick: "+view +" "+items.toString() + " position " +position)
//action_usersDetailsFragment_to_UserAccountDetails
        val action: NavDirections =   UsersDetailsFragmentDirections.actionUsersDetailsFragmentToUserAccountDetails(
            items
        )
        findNavController().navigate(action)
// private val arg by navArgs<UsersDetailsFragmentArgs>()
    }

    override fun onUpdate(position: Int, model: UsersDetailsItem) {
        TODO("Not yet implemented")
    }

    override fun onDelete(model: UsersDetailsItem, position: Int) {
        TODO("Not yet implemented")
    }

    override fun errorUser(e: Exception) {

    }


    override fun onResume() {
        super.onResume()
        myRoleFun()
        if(view!=null){
            listaItems()
        }


    }

}