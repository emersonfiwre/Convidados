package br.com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.example.convidados.service.constants.GuestConstants
import br.com.example.convidados.service.model.GuestModel
import br.com.example.convidados.service.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val mGuestRepository = GuestRepository(application.applicationContext)
    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guesList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int) {
        if (filter == GuestConstants.FILTER.EMPTY) {
            mGuestList.value = mGuestRepository.getAll()
        } else if (filter == GuestConstants.FILTER.PRESENT) {
            mGuestList.value = mGuestRepository.getPresent()
        } else {
            mGuestList.value = mGuestRepository.getAbsent()
        }

    }

    fun delete(id: Int) {
        val guest = mGuestRepository.get(id)
        if (guest != null) {
            mGuestRepository.delete(guest)
        }
    }
}