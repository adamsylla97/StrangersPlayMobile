package com.strangersplay.user_profile.display.model

import android.os.Parcel
import android.os.Parcelable

data class UserData(val firstName: String?,
                    val lastName: String?,
                    val emailAdress: String?,
                    val level: String?,
                    val about: String?,
                    val photo: String?,
                    val comments: List<Comment>?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Comment)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(emailAdress)
        parcel.writeString(level)
        parcel.writeString(about)
        parcel.writeString(photo)
        parcel.writeTypedList(comments)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}
