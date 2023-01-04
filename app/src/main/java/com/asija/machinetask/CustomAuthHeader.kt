package com.asija.machinetask

import com.philjay.jwt.Algorithm
import com.philjay.jwt.JWTAuthHeader
import com.philjay.jwt.JWTAuthPayload

class CustomAuthHeader(alg: String,val kid: String, val typ: String): JWTAuthHeader(alg)
