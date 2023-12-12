package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class HistoryFragment : Fragment() {

    lateinit var listViewHistory: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
                            inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.fragment_history,
                                    container, false)

        listViewHistory = view.findViewById(R.id.listViewHistory)

        val clearButton: Button = view.findViewById(R.id.btnClearHistory)
        clearButton.setOnClickListener {
            clearHistory()
        }

        return view
    }

    private fun clearHistory() {
        TODO("Not yet implemented")
    }
}

