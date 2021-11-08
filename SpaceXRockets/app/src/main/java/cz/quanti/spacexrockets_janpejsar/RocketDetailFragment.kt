package cz.quanti.spacexrockets_janpejsar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cz.quanti.spacexrockets_janpejsar.databinding.FragmentRocketDetailBinding

class RocketDetailFragment: Fragment() {
    private lateinit var binding: FragmentRocketDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_detail, container, false)

        return binding.root
    }
}