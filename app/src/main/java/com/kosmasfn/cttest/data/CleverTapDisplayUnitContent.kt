package com.kosmasfn.cttest.data

import android.os.Parcel
import android.os.Parcelable


class CleverTapDisplayUnitContent() : Parcelable {
    /**
     * Getter for the title section of the Display Unit Content
     * @return String
     */
    val title: String? = null

    /**
     * Getter for the hex-code value of the title color e.g. #000000
     * @return String
     */
    val titleColor: String? = null

    /**
     * Getter for the message section of the Display Unit Content
     * @return String
     */
    val message: String? = null

    /**
     * Getter for the hex-code value of the message color e.g. #000000
     * @return String
     */
    val messageColor: String? = null

    /**
     * Getter for the media URL of the Display Unit Content
     * @return String
     */
    val media: String? = null

    /**
     * Getter for the content type of the media(image/gif/audio/video etc.)
     *
     * Refer[.mediaIsImage], [.mediaIsGIF],
     * [.mediaIsAudio] ,[.mediaIsVideo]
     * @return String
     */
    val contentType: String? = null

    /**
     * Getter for the URL for the thumbnail of the video
     * @return String
     */
    val posterUrl: String? = null

    /**
     * Getter for the action URL of the body of the Display Unit Content
     * @return String
     */
    val actionUrl: String? = null

    /**
     * Getter for the URL as String for the icon in case of Icon Message template
     * @return String
     */
    val icon: String? = null

    constructor(parcel: Parcel) : this() {
    }

    /**
     * Method to check whether media in the [CleverTapDisplayUnitContent] object is an image.
     *
     * @return boolean - | true, if the media type is image
     * | false, if the media type is not an image
     */
    fun mediaIsImage(): Boolean {
        return contentType != null && media != null && contentType.startsWith("image") && contentType != "image/gif"
    }

    /**
     * Method to check whether media in the [CleverTapDisplayUnitContent] object is a GIF.
     *
     * @return boolean - | true, if the media type is GIF
     * | false, if the media type is not a GIF
     */
    fun mediaIsGIF(): Boolean {
        return contentType != null && media != null && contentType == "image/gif"
    }

    /**
     * Method to check whether media in the [CleverTapDisplayUnitContent] object is a video.
     *
     * @return boolean - | true, if the media type is video
     * | false, if the media type is not a video
     */
    fun mediaIsVideo(): Boolean {
        return contentType != null && media != null && contentType.startsWith("video")
    }

    /**
     * Method to check whether media in the [CleverTapDisplayUnitContent] object is an audio.
     *
     * @return boolean - | true, if the media type is audio
     * | false, if the media type is not an audio
     */
    fun mediaIsAudio(): Boolean {
        return contentType != null && media != null && contentType.startsWith("audio")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CleverTapDisplayUnitContent> {
        override fun createFromParcel(parcel: Parcel): CleverTapDisplayUnitContent {
            return CleverTapDisplayUnitContent(parcel)
        }

        override fun newArray(size: Int): Array<CleverTapDisplayUnitContent?> {
            return arrayOfNulls(size)
        }
    }
}