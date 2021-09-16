package com.example.photoday.view.picture

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.photoday.R
import com.example.photoday.databinding.MainFragmentBinding
import com.example.photoday.view.MainActivity
import com.example.photoday.viewmodel.PODData
import com.example.photoday.viewmodel.PODViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior


class PODFragment : Fragment() {


    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var minusTodayPlusDay: Int = 0

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

        binding.chipToday.setOnClickListener {
            viewModel.getLiveData()
                .observe(viewLifecycleOwner, Observer<PODData> { renderData(it) })
            viewModel.sendServerRequest(minusTodayPlusDay)
            setBottomSheetBehavior(binding.includeLayout.bottomSheetContainer)

        }
        binding.chipTomorrow.setOnClickListener {
            viewModel.getLiveData()
                .observe(viewLifecycleOwner, Observer<PODData> { renderData(it) })
            viewModel.sendServerRequest(2)
            setBottomSheetBehavior(binding.includeLayout.bottomSheetContainer)
        }
        binding.chipYesterday.setOnClickListener {
            viewModel.getLiveData()
                .observe(viewLifecycleOwner, Observer<PODData> { renderData(it) })
            viewModel.sendServerRequest(1)
            setBottomSheetBehavior(binding.includeLayout.bottomSheetContainer)
        }

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        binding.fab.setOnClickListener {
            val mBuilder = AlertDialog.Builder(activity)
            mBuilder.setTitle("Выбери свой стиль:")
            mBuilder.setNeutralButton("Розовенькая") { dialog, which ->
                // Do something when click the neutral button
                sendData(1)
            }
            mBuilder.setNegativeButton("Зелененькая"){ dialog, which ->
                sendData(2)
            }
            mBuilder.setPositiveButton("Темненькая") { dialog, which ->
                val isNightTheme =
                    resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                when (isNightTheme) {
                    Configuration.UI_MODE_NIGHT_YES ->
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    Configuration.UI_MODE_NIGHT_NO ->
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
            val mDialog = mBuilder.create()
            mDialog.show()

        }
    }

    private fun renderData(data: PODData?) {
        when (data) {
            is PODData.Error -> {
                error(R.drawable.ic_load_error_vector)
            }//HW
            is PODData.Loading -> {
                binding.imageView.load("https://api.nasa.gov/planetary/apod?data="){
                    placeholder(R.drawable.progress_animation)
                }
            }
            is PODData.Success -> {
                viewDataLayout(data.serverResponseData.url.toString(), data.serverResponseData.explanation.toString())
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

    private fun sendData(p: Int) {
        val i = Intent(requireActivity().baseContext, MainActivity::class.java)
        i.putExtra("SENDER_KEY", p)
        requireActivity().startActivity(i)
    }


}