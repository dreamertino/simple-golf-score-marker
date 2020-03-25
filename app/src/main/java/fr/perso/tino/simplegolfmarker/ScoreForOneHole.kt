package fr.perso.tino.simplegolfmarker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import fr.perso.tino.simplegolfmarker.databinding.ScoreForOneHoleFragmentBinding


class ScoreForOneHole : Fragment() {

    private val viewModel: ScoreForOneHoleViewModel by activityViewModels()

    private var _binding: ScoreForOneHoleFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ScoreForOneHoleFragmentBinding.inflate(inflater, container, false)

        initFragment()

        binding.add.setOnClickListener { viewModel.increaseCurrentScore() }
        binding.minus.setOnClickListener { viewModel.decreaseCurrentScore() }
        binding.nextHole.setOnClickListener {
            viewModel.currentHole.value?.let {
                if (it.toInt() < 18) {
                    viewModel.nextHole()
                }

            }
        }
        binding.previousHole.setOnClickListener {
            viewModel.currentHole.value?.let {
                if (it.toInt() > 1) {
                    viewModel.previousHole()
                }
            }
        }
        Log.d(null, "Binding save button action")
        binding.save.setOnClickListener {

            viewModel.saveCurrentSession()
        }
        viewModel.currentScore.observe(viewLifecycleOwner, Observer { item ->
            // Update the UI
            binding.score.text = item.toString()
        })
        viewModel.currentHole.observe(viewLifecycleOwner, Observer { holeNumber ->
            run {
                binding.holeNumber.text = holeNumber.toString()
                when (holeNumber?.toInt()) {
                    1 -> binding.previousHole.visibility = View.INVISIBLE
                    18 -> binding.nextHole.visibility = View.INVISIBLE
                    else -> {
                        binding.previousHole.visibility = View.VISIBLE
                        binding.nextHole.visibility = View.VISIBLE
                    }
                }
            }

        })
        return binding.root
    }

    private fun initFragment() {

    }

}
