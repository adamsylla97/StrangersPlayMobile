package com.strangersplay.user_profile.display.view


import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.strangersplay.Config
import com.strangersplay.InstanceProvider
import com.strangersplay.R
import com.strangersplay.user_profile.display.adapter.UserProfileAdapter
import com.strangersplay.user_profile.display.model.UserData
import com.strangersplay.user_profile.edit.view.EditUserProfileFragment
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfileFragment : Fragment(), UserProfileView {

    private val presenter = InstanceProvider.getUserProfilePresenter(this)
    private val userProfileAdapter = UserProfileAdapter()

    private var userId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userId = arguments?.getInt("userId",0) ?: -1
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(userId == -1) userId = Config.userToken

        userComments.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userProfileAdapter
        }

        if(userId != Config.userToken){
            updateProfile.visibility = View.GONE
        }

        presenter.displayUserInformation()

        updateProfile.setOnClickListener {
            val profile = presenter.getProfile()
            val updateProfileFragment = EditUserProfileFragment.newInstance(profile!!)

            fragmentManager?.let {
                it.beginTransaction()
                    .add(R.id.userProfileFragment, updateProfileFragment)
                    .addToBackStack("userProfile")
                    .commit()

            }
        }

    }

    override fun updateProfile(profileInformation: UserData) {

        Glide.with(this).load(profileInformation.photo).placeholder(R.drawable.ic_alien_head).into(userProfilePhoto)
        eventRatingBar.rating = profileInformation.level!!.toFloat()
        userNameTV.text = "${profileInformation.firstName}  ${profileInformation.lastName}"
        userInformation.text = profileInformation.description

        userProfileAdapter.addList(profileInformation.comments ?: emptyList())
        userProfileAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        Log.i("supertest123","hello again world!")
        presenter.displayUserInformation()
    }

    override fun getUserId(): Int {
        if(userId == 0) {
            return Config.userToken
        } else {
            return userId
        }
    }

    companion object{
        fun newInstance(userId: Int): UserProfileFragment {
            val fragment = UserProfileFragment()

            val bundle = Bundle()
            bundle.putInt("userId",userId)
            fragment.arguments = bundle

            return fragment
        }
    }
}
