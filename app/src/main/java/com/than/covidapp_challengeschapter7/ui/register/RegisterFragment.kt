package com.than.covidapp_challengeschapter7.ui.register

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.than.covidapp_challengeschapter7.R
import com.than.covidapp_challengeschapter7.data.DataStoreManager.Companion.DEF_IMAGE
import com.than.covidapp_challengeschapter7.data.room.UserEntity
import com.than.covidapp_challengeschapter7.databinding.FragmentRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {
            when {
                binding.etUsername.text.toString().isEmpty() || binding.etPassword.text.toString()
                    .isEmpty() || binding.etNama.text.toString()
                    .isEmpty() || binding.etEmail.text.toString()
                    .isEmpty() || binding.etConfirmPassword.text.toString().isEmpty() -> {
                    Toast.makeText(requireContext(), "Form tidak boleh kosong!", Toast.LENGTH_SHORT)
                        .show()
                }
                binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString() -> {
                    Toast.makeText(requireContext(), "Password tidak sama!", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    val nama = binding.etNama.text.toString()
                    val email = binding.etEmail.text.toString()
                    val username = binding.etUsername.text.toString()
                    val password = binding.etPassword.text.toString()
                    val user = UserEntity(
                        null,
                        nama,
                        email,
                        username,
                        password,
                        DEF_IMAGE
                    )
//                    viewModel.checkUsernameExists(username)
                    var isUsed = false
//                    val checkUser = viewModel.checkUsernameExists(username)
                    viewModel.userData.observe(viewLifecycleOwner) {
                        if (it != null) {
                            if(it.username == username){
                                isUsed = true
                            }
                        }
                    }
//                    if (checkUser == null){
//                        binding.tilUsername.error = ""
//                        viewModel.insertUser(user)
//                        Toast.makeText(
//                            requireContext(),
//                            "Register Success!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
                    if(isUsed){
                        binding.tilUsername.error = "Username sudah digunakan!"
                    } else {
                        binding.tilUsername.error = ""
                        viewModel.insertUser(user)
                        Toast.makeText(
                            requireContext(),
                            "Register Success!",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    }
                }
            }
        }
    }

}