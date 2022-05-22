package com.than.covidapp_challengeschapter7.ui.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.than.covidapp_challengeschapter7.R
import com.than.covidapp_challengeschapter7.data.Status
import com.than.covidapp_challengeschapter7.data.model.DetailCountryCases
import com.than.covidapp_challengeschapter7.data.model.GetAllCountryCases
import com.than.covidapp_challengeschapter7.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeFragmentViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCountryCases()
        binding.toolbar.setOnClickListener {
            showAlertDialog()
        }
        fetchData()
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("User Menu")
            .setMessage("Halo!")
            .setNeutralButton("Favorite"){ dialog, _ ->
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                dialog.dismiss()
            }
            .show()
    }

    private fun fetchData() {
        viewModel.countryCases.observe(viewLifecycleOwner){ resource ->
            when(resource.status){
                Status.LOADING -> {
                    binding.pbMain.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    val adapter = HomeAdapter(object: HomeAdapter.OnClickListener{
                        override fun onClickItem(data: GetAllCountryCases) {
                            val dataDetail = DetailCountryCases(
                                data.country,
                                data.countryInfo.flag,
                                data.cases,
                                data.active,
                                data.deaths,
                                data.recovered
                            )
                            val moveToDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment(dataDetail)
                            findNavController().navigate(moveToDetail)
                        }
                    })
                    adapter.submitData(resource.data)
                    binding.rvMain.adapter = adapter
                    binding.pbMain.visibility = View.GONE
                }
                else -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Error")
                        .setMessage("Error Get Data : ${resource.message}")
                        .setCancelable(false)
                        .show()
                }
            }

        }
        viewModel.allDataCases.observe(viewLifecycleOwner){ resource ->
            when(resource.status){
                Status.LOADING -> {
                    binding.pbMain.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.tvWelcome.text = getString(R.string.welcome, "SulthanLR")
                    binding.tvAllCases.text = getString(R.string.jumlah_kasus_seluruh_dunia_s, resource.data?.cases.toString())
                    binding.pbMain.visibility = View.GONE
                }
                else -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Error")
                        .setMessage("Error Get Data : ${resource.message}")
                        .setCancelable(false)
                        .show()
                }
            }
        }
    }

}