package fr.perso.tino.simplegolfmarker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.perso.tino.simplegolfmarker.model.SessionResult

class SessionListAdapter : RecyclerView.Adapter<SessionListAdapter.SessionViewHolder>() {

    private var sessions = emptyList<SessionResult>()

    inner class SessionViewHolder(private val sessionView: View) :
        RecyclerView.ViewHolder(sessionView) {
        val textView: TextView = sessionView.findViewById(R.id.sessionId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_session, parent, false)
        return SessionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        val current = sessions[position]
        holder.textView.text = current.uid.toString()
    }

    internal fun setSessions(session: List<SessionResult>) {
        this.sessions = session
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = sessions.size

    companion object {
        private val TAG = "SessionListAdapter"
    }
}