package com.strangersplay.user_profile.edit.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.user_profile.display.model.UserData
import com.strangersplay.user_profile.edit.model.UpdatedUserInformation
import kotlinx.android.synthetic.main.fragment_edit_user_profile.*

class EditUserProfileFragment : Fragment(), EditUserProfileView {

    private lateinit var userData: UserData
    private val presenter = InstanceProvider.getEditUserProfilePresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(arguments!!.getParcelable<UserData>("userInfo") != null){
            userData = arguments!!.getParcelable<UserData>("userInfo")!!
        }
        return inflater.inflate(R.layout.fragment_edit_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateButton.setOnClickListener {
            presenter.updateUser()
            Navigation.findNavController(it).navigateUp()
        }

    }

    override fun getUpdatedUser(): UpdatedUserInformation {
        var firstName = ""
        if(firstNameEditText.text.toString() != ""){
            firstName = firstNameEditText.text.toString()
        } else {
            firstName = userData.firstName!!
        }

        var lastName = ""
        if(lastNameEditText.text.toString() != ""){
            lastName = lastNameEditText.text.toString()
        } else {
            lastName = userData.lastName!!
        }

        var about = ""
        if(aboutEditText.text.toString() != ""){
            about = aboutEditText.text.toString()
        } else {
            about = userData.description!!
        }

        return UpdatedUserInformation(
            firstName,lastName,about
        )
    }

    override fun closeFragment() {
        fragmentManager?.let {
            it.popBackStack()
        }
    }

    companion object{
        fun newInstance(userData: UserData): EditUserProfileFragment {
            val fragment = EditUserProfileFragment()

            val bundle = Bundle()
            bundle.putParcelable("userInfo",userData)

            fragment.arguments = bundle

            return fragment
        }
    }


}
