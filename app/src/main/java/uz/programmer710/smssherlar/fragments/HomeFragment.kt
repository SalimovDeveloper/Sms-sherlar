package uz.programmer710.smssherlar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.programmer710.smssherlar.R
import uz.programmer710.smssherlar.adapters.RvAdapter
import uz.programmer710.smssherlar.databinding.FragmentHomeBinding
import uz.programmer710.smssherlar.models.Info
import uz.programmer710.smssherlar.models.MyObject

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.yangiCount.text = MyObject.list_Yangi_Sherlar.count().toString()
        binding.saqlanganCount.text = MyObject.list_Saqlangan_Sherlar.count().toString()

        binding.cardYangilar.setOnClickListener {
            val msg = "Yangi Sherlar"
            findNavController().navigate(R.id.sherlar, bundleOf("keyName" to msg))
        }
        binding.cardSaqlangan.setOnClickListener {
            val msg = "Saqlanganlar"
            findNavController().navigate(R.id.sherlar, bundleOf("keyName" to msg))
        }
        binding.cardSevgiSherlari.setOnClickListener {
            val msg = "Sevgi Sherlari"
            findNavController().navigate(R.id.sherlar, bundleOf("keyName" to msg))
        }
        binding.cardSoginchSherlari.setOnClickListener {
            val msg = "Sog'inch Sherlari"
            findNavController().navigate(R.id.sherlar, bundleOf("keyName" to msg))
        }
        binding.cardTabrikSherlari.setOnClickListener {
            val msg = "Tabrik Sherlari"
            findNavController().navigate(R.id.sherlar, bundleOf("keyName" to msg))
        }
        binding.cardOtaOnaSherlari.setOnClickListener {
            val msg = "Ota Ona Sherlari"
            findNavController().navigate(R.id.sherlar, bundleOf("keyName" to msg))
        }
        binding.cardDostlikSherlari.setOnClickListener {
            val msg = "Do'stlik Sherlari"
            findNavController().navigate(R.id.sherlar, bundleOf("keyName" to msg))
        }
        binding.cardHazilSherlari.setOnClickListener {
            val msg = "Hazil Sherlari"
            findNavController().navigate(R.id.sherlar, bundleOf("keyName" to msg))
        }

        return binding.root

    }
}