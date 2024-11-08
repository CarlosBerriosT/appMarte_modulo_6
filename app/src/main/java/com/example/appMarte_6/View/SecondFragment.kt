package com.example.appMarte_6.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appMarte_6.ViewModel.MarsViewModel
import com.example.appmarte_6.R
import com.example.appmarte_6.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var _binding: FragmentSecondBinding
    private val viewModel : MarsViewModel by activityViewModels()

    // para carptar la selección
    var idMars: String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // para captar el id con el método de factory

        arguments?.let {

            idMars= it.getString("id","")
        }

    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.seletedItem().observe(viewLifecycleOwner, Observer {
            it?.let {

                Glide.with(_binding.ivTerrain)
                    .load(it.img_src).centerCrop().into(_binding.ivTerrain)
                _binding.tvPrice.text = it.price.toString()
                _binding.tvType.text = it.type
            }
        })

        _binding.btnReturn.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
       // _binding = null
    }
}