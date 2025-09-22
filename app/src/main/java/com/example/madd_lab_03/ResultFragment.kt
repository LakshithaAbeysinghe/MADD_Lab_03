package com.example.madd_lab_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madd_lab_03.databinding.FragmentResultBinding
import kotlin.math.round

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val value = arguments?.getDouble(ARG_RESULT) ?: 0.0
        binding.tvResult.text = "Answer: ${format(value)}"
        binding.btnBack.setOnClickListener { parentFragmentManager.popBackStack() }
    }

    private fun format(x: Double): String {
        val r = round(x * 1_000_000.0) / 1_000_000.0
        val asLong = r.toLong()
        return if (r == asLong.toDouble()) asLong.toString() else r.toString()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }

    companion object {
        private const val ARG_RESULT = "arg_result"
        fun newInstance(result: Double) = ResultFragment().apply {
            arguments = Bundle().apply { putDouble(ARG_RESULT, result) }
        }
    }
}
