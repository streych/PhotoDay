package com.example.photoday.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.photoday.R
import com.example.photoday.databinding.MainFragmentBinding
import com.example.photoday.viewmodel.PODData
import com.example.photoday.viewmodel.PODViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PODFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var _binding: MainFragmentBinding? = null
    val binding get() = _binding!!

    companion object {
        fun newInstance() = PODFragment()
    }

    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<PODData> { renderData(it) })
        viewModel.sendServerRequest(1)
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
        setBottomSheetBehavior(binding.includeLayout.bottomSheetContainer)
    }

    private fun renderData(data: PODData?) {
        when (data) {
            is PODData.Error -> {
                Toast.makeText(context, "PODDATA Error", Toast.LENGTH_LONG).show()
            }//HW
            is PODData.Loading -> {
                binding.imageView.setImageResource(R.drawable.ic_logo_design_icon_icons_com_66427)
                Toast.makeText(context, "PODDATA Loading", Toast.LENGTH_LONG).show()
            }
            is PODData.Success -> {
                binding.chipToday.setOnClickListener {
                    viewDataLayout(data.serverResponseData.url.toString(), data.serverResponseData.explanation.toString())
                }
                binding.chipTomorrow.setOnClickListener {

                }
                binding.chipYesterday.setOnClickListener {

                }

                // Дата в формате 2021-09-10чтоб не забыл
                //binding.includeLayout.bottomSheetDescription.text = viewModel.getDaysAgo(0)
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun viewDataLayout(data: String, data2: String) {
        binding.imageView.load(data) {
            error(R.drawable.ic_load_error_vector)
        }
        binding.includeLayout.bottomSheetDescription.text = data2
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}