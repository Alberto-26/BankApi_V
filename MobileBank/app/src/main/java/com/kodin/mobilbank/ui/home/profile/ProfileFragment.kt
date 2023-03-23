package com.kodin.mobilbank.ui.home.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kodin.mobilbank.R
import com.kodin.mobilbank.data.db.User
import com.kodin.mobilbank.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit  var navController: NavController
    private   var _binding: FragmentProfileBinding?=null


    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel  by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //   return inflater.inflate(R.layout.fragment_profile, container, false)

        _binding =FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        viewModel.user?.observe(viewLifecycleOwner, Observer { userPerfil ->
            showUserProfile(userPerfil, view)
        })
    }

    private fun showUserProfile(userPerfil: User?, view: View) {
        if (userPerfil == null) {
            return
        }

        if (userPerfil!!.rol.toString().equals("[[ROLE_USER]]")) {
          // binding.tvIdUser .setText(userPerfil?.username)
            binding.tvIdUser.setText(userPerfil.user)
            binding.tvIdUserMail.setText(userPerfil?.mail)
            binding.tvIdDescriptionRole.setText("is a pretty day to work\n you are only operator...")
            binding.btnRegistrar.isVisible = false
            binding.btnUpdateDeleteRoles.isVisible=false


        }

        if (userPerfil!!.rol.toString().equals("[[ROLE_ADMIN]]")) {
            // binding.tvIdUser .setText(userPerfil?.username)
            binding.tvIdUser .setText(userPerfil?.user)
            // 2 MAIL
            //tv_idUserMail
            binding.tvIdUserMail .setText(userPerfil?.mail)
            //  3   DESCRIPCION DEL ROL
            binding.tvIdDescriptionRole.setText("Tienes todos los Privilegios " )

        }

    }


}