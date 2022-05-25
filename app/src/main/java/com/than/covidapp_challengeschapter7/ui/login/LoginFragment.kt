package com.than.covidapp_challengeschapter7.ui.login

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.than.covidapp_challengeschapter7.R
import com.than.covidapp_challengeschapter7.data.DataStoreManager.Companion.DEF_ID
import com.than.covidapp_challengeschapter7.data.room.UserEntity
import com.than.covidapp_challengeschapter7.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LoginFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserPref()
        viewModel.userPref.observe(viewLifecycleOwner){
            if (it.id_user != DEF_ID){
                Toast.makeText(requireContext(), "Selamat Datang, ${it.nama}", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
        binding.btnLogin.setOnClickListener {
            when{
                binding.etUsername.text.toString().isEmpty() || binding.etPassword.text.toString().isEmpty() -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Alert")
                        .setMessage("Username/Password tidak boleh kosong!")
                        .setPositiveButton("OK"){ dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
                else -> {
                    binding.tilPassword.error = ""
                    val username = binding.etUsername.text.toString()
                    val password = binding.etPassword.text.toString()
                    viewModel.loginUser(username, password)
                    viewModel.loginData.observe(viewLifecycleOwner){
                        if (it == null){
                            binding.tilPassword.error = "Username/password salah!"

                        } else {
                            viewModel.setUserPref(it)
                            if (findNavController().currentDestination?.id == R.id.loginFragment){
                                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                            }
                        }
                    }
                }
            }
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}