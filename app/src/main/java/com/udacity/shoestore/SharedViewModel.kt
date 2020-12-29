package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

/**
 * ViewModel for the shoe list
 */
class SharedViewModel : ViewModel() {

    // Variable to store a new shoe before saving to the list of shoes
    var newShoe = Shoe(name="", size=0.0, company = "", description = "")
    var newShoeSizeText = ""

    // Create a mutable list that will contain a list of shoes
    // and a public LiveData object for access
    private val _shoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    // Event triggered when the shoe detail fragment can navigate back to the shoe list
    private val _eventSaveOrCancel = MutableLiveData<Boolean>()
    val eventSaveOrCancel: LiveData<Boolean>
        get() = _eventSaveOrCancel

    // Function to add a new shoe to the list
    fun saveNewShoe() {
        Timber.i("Started to save new shoe")
        newShoe.size = newShoeSizeText.toString().toDoubleOrNull() ?: 0.0
       _shoes.value?.add(newShoe)
        eventSaveOrCancel()
    }

    // Create a shoe for linking to the detail screen
    fun createBlankNewShoe() {
        newShoe.name=""
        newShoe.size=0.0
        newShoe.company = ""
        newShoe.description = ""
        newShoeSizeText = ""
    }

    // Function to let the shoe detail fragment know that either save or cancel has been clicked
    // and it is time to navigate back to the shoe list
    fun eventSaveOrCancel() {
        _eventSaveOrCancel.value = true
    }

    // Clears the eventSaveOrCancelComplete boolean upon shoe detail fragment navigating back to
    // the shoe list
    fun eventSaveOrCancelComplete() {
        _eventSaveOrCancel.value = false
    }
}