package cz.quanti.spacexrockets_janpejsar.rocketslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.FragmentRocketListBinding
import cz.quanti.spacexrockets_janpejsar.spacexdatabase.entities.RocketDatabaseEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketListFragment: Fragment() {
    private lateinit var binding: FragmentRocketListBinding
    private val viewModel: RocketListViewModel by viewModels()
    private val adapter = RocketsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter = adapter

        adapter.onItemClickListener = this::showRocketDetail

        viewModel.rocketsLiveData.observe(viewLifecycleOwner, {
            Log.i("TAG", "onCreateView: Submitting ${it?.size} rockets")

            val items = ArrayList<RocketItem>()
            it.forEach { rocket: RocketDatabaseEntity -> items.add(RocketItem(rocket)) }
            adapter.submitList(items)
        })

        return binding.root
    }

    private fun showRocketDetail(rocket: RocketItem) {
        Log.d("TAG", "showRocketDetail: ${rocket.name}")
        findNavController().navigate(RocketListFragmentDirections.actionRocketListFragmentToRocketDetailFragment())
    }
}