package com.example.madd_lab_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import com.example.madd_lab_03.databinding.FragmentInputBinding

// ...same imports & class header as before...

class InputFragment : Fragment() {
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clicker = View.OnClickListener { v ->
            val a = binding.etFirst.text.toString().trim()
            val b = binding.etSecond.text.toString().trim()

            val x = a.toDoubleOrNull()
            val y = b.toDoubleOrNull()
            if (x == null || y == null) {
                Toast.makeText(requireContext(), "Please enter valid numbers in both fields", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            val result = when (v.id) {
                R.id.btnAdd      -> x + y
                R.id.btnSubtract -> x - y
                R.id.btnMultiply -> x * y
                R.id.btnDivide   -> {
                    if (y == 0.0) {
                        Toast.makeText(requireContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        return@OnClickListener
                    }
                    x / y
                }
                else -> 0.0
            }

            val frag = ResultFragment.newInstance(result)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, frag)
                .addToBackStack(null)
                .commit()
        }

        binding.btnAdd.setOnClickListener(clicker)
        binding.btnSubtract.setOnClickListener(clicker)
        binding.btnMultiply.setOnClickListener(clicker)
        binding.btnDivide.setOnClickListener(clicker)
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
