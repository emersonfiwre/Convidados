package br.com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.example.convidados.viewmodel.GuestFormViewModel
import br.com.example.convidados.R
import br.com.example.convidados.service.constants.GuestConstants
import kotlinx.android.synthetic.main.activity_guest_form.*
import kotlinx.android.synthetic.main.row_guest.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mGuestFormViewModel: GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)
        mGuestFormViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        radio_presence.isChecked = true

    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mGuestFormViewModel.load(mGuestId)
        }
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id == R.id.butto_save) {

            val name = edit_name.text.toString()
            val presence = radio_presence.isChecked

            mGuestFormViewModel.save(mGuestId, name, presence)

        }
    }

    private fun observe() {
        mGuestFormViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mGuestFormViewModel.guest.observe(this, Observer {
            edit_name.setText(it.name)
            if (it.presence) {
                radio_presence.isChecked = true
            } else {
                radio_absent.isChecked = true
            }
        })
    }

    private fun setListeners() {
        butto_save.setOnClickListener(this)
    }


}