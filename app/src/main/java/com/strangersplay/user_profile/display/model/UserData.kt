package com.strangersplay.user_profile.display.model

import android.os.Parcel
import android.os.Parcelable

data class UserData(
    val active: Boolean?,
    val commentList: List<Comment>?,
    val description: String?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val level: Int?,
    val password: String?,
    val photo: String?,
    val rating: Double?,
    val username: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.createTypedArrayList(Comment),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (active!!) 1 else 0)
        parcel.writeTypedList(commentList)
        parcel.writeString(description)
        parcel.writeString(email)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeInt(level!!)
        parcel.writeString(password)
        parcel.writeString(photo)
        parcel.writeDouble(rating!!)
        parcel.writeString(username)
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