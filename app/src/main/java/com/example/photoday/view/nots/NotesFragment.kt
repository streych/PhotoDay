package com.example.photoday.view.nots

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.photoday.R
import com.example.photoday.databinding.NotesFragmentBinding
import com.google.android.material.snackbar.Snackbar

class NotesFragment : Fragment() {

    val data = arrayListOf(
        Pair(Data(1, "Первый", "ПервыйПервый", "00-00-0000"), true),
        Pair(Data(2, "Второй", "ВторойВторой", "00-00-0000"), false),
        Pair(Data(3, "Третий", "ТретийТретий", "00-00-0000"), false),
        Pair(Data(5, "Пятый", "ПятыйПятый", "00-00-0000"), false),
        Pair(Data(6, "Шестой", "ШестойШестой", "00-00-0000"), false),
        Pair(Data(7, "Седьмой", "СедьмойСедьмой", "00-00-0000"), false)
    )

    private var _binding: NotesFragmentBinding? = null
    val binding get() = _binding!!

    private val adapter: NRAdapter by lazy {
        NRAdapter(
            object : NRAdapter.OnListItemClickListener {
                override fun onItemClick(data: Data) {

                        val mDialogView = LayoutInflater.from(context).inflate(R.layout.alert_add, null)
                        val mBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                        mBuilder.setView(mDialogView)
                        mBuilder.setTitle("Редактируем заметка")
                        val title = mDialogView.findViewById<EditText>(R.id.input_title).text
                        val description = mDialogView.findViewById<EditText>(R.id.input_description).text
                        title.append(data.title)
                        description.append(data.description)
                        val i: Int = data.id

                        mBuilder.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->

                            adapter.appendItem(title.toString(), description.toString())
                            adapter.removeItem(i)
                        })

                        mBuilder.setNegativeButton(
                            "Cancel",
                            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

                        mBuilder.show()


                    Snackbar.make(requireView(), data.title.toString(), Snackbar.LENGTH_LONG).show()
                    //Toast.makeText(context, data.title, Toast.LENGTH_SHORT).show()
                }
            },
            data
        )
    }

    companion object {
        fun newInstance() = NotesFragment()
    }

    private val viewModel: NotesViewModel by lazy {
        ViewModelProvider(this).get(NotesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NotesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerVN.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener {
            showDialog()
        }
        ItemTouchHelper(ItemTouchHelperCallback(adapter))
            .attachToRecyclerView(binding.recyclerVN)

        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("InflateParams")
    private fun showDialog() {

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.alert_add, null)
        val mBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        mBuilder.setView(mDialogView)
        mBuilder.setTitle("Новый заметка")

        mBuilder.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
            val title = mDialogView.findViewById<EditText>(R.id.input_title).text
            val description = mDialogView.findViewById<EditText>(R.id.input_description).text

            adapter.appendItem(title.toString(), description.toString())

        })

        mBuilder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        mBuilder.show()

    }

}