package com.example.applogginghandle.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.applogginghandle.databinding.FragmentFirstBinding
import com.example.applogginghandle.presentation.viewmodel.FirstFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private val myTag = "FirstFragment"

    private var _binding: FragmentFirstBinding? = null

    private val viewModel: FirstFragmentViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener {
            viewModel.onPostEvent(myTag, "buttonFirst gets clicked")
        }
        viewModel.hint.observe(viewLifecycleOwner){event->
            event.getContentIfNotHandled()?.let {
                binding.textviewFirst.text = getString(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}