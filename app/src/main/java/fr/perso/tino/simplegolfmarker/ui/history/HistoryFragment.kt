package fr.perso.tino.simplegolfmarker.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fr.perso.tino.simplegolfmarker.SessionListAdapter
import fr.perso.tino.simplegolfmarker.databinding.HistoryFragmentBinding

class HistoryFragment : Fragment() {
    private var _binding: HistoryFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = HistoryFragment()
        const val TAG = "History fragment"
    }

    private val viewModel: HistoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HistoryFragmentBinding.inflate(inflater, container, false)
        val adapter = SessionListAdapter()
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = LinearLayoutManager(this.requireContext())
        viewModel.sessions.observe(
            viewLifecycleOwner,
            Observer { sessions ->
                sessions ?: Log.w(TAG, "Pas de session trouvées en base de données !!! Bizarre")
                sessions?.let { adapter.setSessions(it) }
            })
        return binding.root
    }

}
