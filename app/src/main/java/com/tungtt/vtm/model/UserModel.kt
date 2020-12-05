package com.tungtt.vtm.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by tungtt a.k.a TungTT
 * On Sat, 05 Dec 2020 - 16:23
 */
data class UserModel(
    val id: String?,
    val name: String?,
    val email: String?,
    val photoUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(photoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "UserModel(id=$id, name=$name, email=$email, photoUrl=$photoUrl)"
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }


}