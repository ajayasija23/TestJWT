package com.asija.machinetask

import com.philjay.jwt.JWTAuthPayload

class CustomJWTAuthPayload(iss: String, iat: Long,val exp: Long): JWTAuthPayload(iss, iat){

}