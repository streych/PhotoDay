package com.example.photoday.api

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.FontRequest
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.*
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.text.set
import androidx.fragment.app.Fragment
import com.example.photoday.R
import com.example.photoday.databinding.FragmentMarsBinding

class MarsFragment : Fragment() {

    private var show = false

    private var _binding: FragmentMarsBinding? = null
    val binding get() = _binding!!

    companion object {
        fun newInstance() = MarsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        binding.mars.setOnClickListener {
            if (show) hideComponents() else showComponents()
        }
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val spannable = SpannableStringBuilder(binding.textMars.text)
        spannable.setSpan(ForegroundColorSpan(Color.GREEN), 8, 12, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        spannable.insert(12, " (я втиснулся) ")
        spannable.setSpan(UnderlineSpan(), 101, spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan(Color.RED), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(RelativeSizeSpan(1.5f), 33, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(BackgroundColorSpan(Color.CYAN), 40, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textMars.text = spannable


    }

    private fun hideComponents() {
        show = false
        val constraintSet = ConstraintSet()
        constraintSet.clone(requireContext(), R.layout.fragment_mars)
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200
        TransitionManager.beginDelayedTransition(binding.container, transition)
        constraintSet.applyTo(binding.container)

    }

    private fun showComponents() {
        show = true

        val constraintSet = ConstraintSet()
        constraintSet.clone(requireContext(), R.layout.fragment_mars_end)
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200
        TransitionManager.beginDelayedTransition(binding.container, transition)
        constraintSet.applyTo(binding.container)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}