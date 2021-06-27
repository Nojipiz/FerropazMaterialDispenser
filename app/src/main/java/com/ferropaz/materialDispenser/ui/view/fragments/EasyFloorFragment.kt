package com.ferropaz.materialDispenser.ui.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ferropaz.materialDispenser.databinding.FragmentEasyFloorBinding
import com.ferropaz.materialDispenser.ui.viewmodel.EasyFloorViewModel


class EasyFloorFragment : Fragment() {

    private var _binding: FragmentEasyFloorBinding? = null
    private val binding get() = _binding!!

    private val easyFloorViewModel : EasyFloorViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEasyFloorBinding.inflate(inflater, container, false)
        val view = binding.root
        componentsInit()
        setObservers()
        return view
    }

    private fun componentsInit(){
        binding.metersField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                calculate()
            }
        })
    }

    private fun calculate(){
        easyFloorViewModel.calculate(binding.metersField.text)
    }

    private fun setObservers(){
        easyFloorViewModel.quantities.observe(viewLifecycleOwner, {
            setQuantities(it)
        })
    }

    private fun setQuantities(list:ArrayList<Int>){
        binding.profilesAmount.text = list[0].toString()
        binding.blocksAmount.text = list[1].toString()
        binding.meshAmount.text = list[2].toString()
    }
}