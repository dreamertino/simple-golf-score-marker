package fr.perso.tino.simplegolfmarker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.perso.tino.simplegolfmarker.model.SessionResultWithHoleResults

class SessionListAdapter : RecyclerView.Adapter<SessionListAdapter.SessionViewHolder>() {

    private var sessions = emptyList<SessionResultWithHoleResults>()

    inner class SessionViewHolder(private val sessionView: View) :
        RecyclerView.ViewHolder(sessionView) {
        val textView: TextView = sessionView.findViewById(R.id.sessionId)
        val scoreHole1: TextView = sessionView.findViewById(R.id.scoreHole1)
        val scoreHole2: TextView = sessionView.findViewById(R.id.scoreHole2)
        val scoreHole3: TextView = sessionView.findViewById(R.id.scoreHole3)
        val scoreHole4: TextView = sessionView.findViewById(R.id.scoreHole4)
        val scoreHole5: TextView = sessionView.findViewById(R.id.scoreHole5)
        val scoreHole6: TextView = sessionView.findViewById(R.id.scoreHole6)
        val scoreHole7: TextView = sessionView.findViewById(R.id.scoreHole7)
        val scoreHole8: TextView = sessionView.findViewById(R.id.scoreHole8)
        val scoreHole9: TextView = sessionView.findViewById(R.id.scoreHole9)
        val scoreHole10: TextView = sessionView.findViewById(R.id.scoreHole10)
        val scoreHole11: TextView = sessionView.findViewById(R.id.scoreHole11)
        val scoreHole12: TextView = sessionView.findViewById(R.id.scoreHole12)
        val scoreHole13: TextView = sessionView.findViewById(R.id.scoreHole13)
        val scoreHole14: TextView = sessionView.findViewById(R.id.scoreHole14)
        val scoreHole15: TextView = sessionView.findViewById(R.id.scoreHole15)
        val scoreHole16: TextView = sessionView.findViewById(R.id.scoreHole16)
        val scoreHole17: TextView = sessionView.findViewById(R.id.scoreHole17)
        val scoreHole18: TextView = sessionView.findViewById(R.id.scoreHole18)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_session, parent, false)

        return SessionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        val current = sessions[position]
        holder.textView.text = current.sessionResult.uid.toString()
        for (i in 1..18) {
            val scoreToDisplay =
                current.holeResults.find { holeResult -> holeResult.holeNumber.compareTo(i) == 0 }?.score?.toString()
                    ?: "  "
            when (i) {
                1 -> holder.scoreHole1.text = scoreToDisplay
                2 -> holder.scoreHole2.text = scoreToDisplay
                3 -> holder.scoreHole3.text = scoreToDisplay
                4 -> holder.scoreHole4.text = scoreToDisplay
                5 -> holder.scoreHole5.text = scoreToDisplay
                6 -> holder.scoreHole6.text = scoreToDisplay
                7 -> holder.scoreHole7.text = scoreToDisplay
                8 -> holder.scoreHole8.text = scoreToDisplay
                9 -> holder.scoreHole9.text = scoreToDisplay
                10 -> holder.scoreHole10.text = scoreToDisplay
                11 -> holder.scoreHole11.text = scoreToDisplay
                12 -> holder.scoreHole12.text = scoreToDisplay
                13 -> holder.scoreHole13.text = scoreToDisplay
                14 -> holder.scoreHole14.text = scoreToDisplay
                15 -> holder.scoreHole15.text = scoreToDisplay
                16 -> holder.scoreHole16.text = scoreToDisplay
                17 -> holder.scoreHole17.text = scoreToDisplay
                18 -> holder.scoreHole18.text = scoreToDisplay
            }

        }

    }

    internal fun setSessions(session: List<SessionResultWithHoleResults>) {
        this.sessions = session
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = sessions.size

    companion object {
        private val TAG = "SessionListAdapter"
    }
}