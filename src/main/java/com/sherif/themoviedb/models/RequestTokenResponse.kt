package com.sherif.themoviedb.models

import kotlin.properties.Delegates

class RequestTokenResponse {


    var success by Delegates.notNull<Boolean>()
    lateinit var expires_at : String
    lateinit var request_token :String

    lateinit var status_message : String
    lateinit var status_code : String
}