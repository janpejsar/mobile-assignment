package cz.quanti.spacexrockets_janpejsar.rocketslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.FragmentRocketListBinding
import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket

class RocketListFragment: Fragment() {
    private lateinit var binding: FragmentRocketListBinding
    private lateinit var viewModel: RocketListViewModel
    private val adapter = RocketsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val provider = ViewModelProvider(this)
        val viewModel = provider.get(RocketListViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter = adapter

        adapter.onItemClickListener = this::showRocketDetail

        viewModel.rocketsLiveData.observe(viewLifecycleOwner, {
            Log.i("TAG", "onCreateView: Submitting ${it?.size} rockets")
            adapter.submitList(it)
        })

        return binding.root
    }

    private fun showRocketDetail(rocket: Rocket) {
        Log.d("TAG", "showRocketDetail: ${rocket.name}: ${rocket.description}")
        findNavController().navigate(RocketListFragmentDirections.actionRocketListFragmentToRocketDetailFragment())
    }
}