package com.asija.machinetask

import android.content.ContentValues.TAG
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.asija.machinetask.adapters.TherapyAdapter
import com.asija.machinetask.databinding.ActivityMainBinding
import com.asija.machinetask.network.NetworkResponse
import com.asija.machinetask.viewModels.MainViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.philjay.jwt.*
import org.apache.commons.codec.binary.Base64
import java.time.Instant

class MainActivity : AppCompatActivity() {
    private lateinit var gson: Gson
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    val encoder = object : Base64Encoder {
        override fun encodeURLSafe(bytes: ByteArray): String {
            return Base64.encodeBase64URLSafeString(bytes)
        }

        override fun encode(bytes: ByteArray): String {
            return Base64.encodeBase64String(bytes)
        }
    }

    // Base64 decoder using apache commons

    val decoder = object : Base64Decoder {
        override fun decode(bytes: ByteArray): ByteArray {
            return Base64.decodeBase64(bytes)
        }

        override fun decode(string: String): ByteArray {
            return Base64.decodeBase64(string.toByteArray())
        }
    }
    private val jsonDecoder = object : JsonDecoder<CustomAuthHeader, CustomJWTAuthPayload> {

        override fun headerFrom(json: String): CustomAuthHeader {
            return gson.fromJson(json, CustomAuthHeader::class.java)
        }

        override fun payloadFrom(json: String): CustomJWTAuthPayload {
            return gson.fromJson(json, CustomJWTAuthPayload::class.java)
        }
    }

    val jsonEncoder = object : JsonEncoder<CustomAuthHeader, CustomJWTAuthPayload> {
        override fun toJson(header: CustomAuthHeader): String {
            return gson.toJson(header, CustomAuthHeader::class.java)
        }

        override fun toJson(payload: CustomJWTAuthPayload): String {
            return gson.toJson(payload, CustomJWTAuthPayload::class.java)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getApiData()
        bindObservers()
        encodeToJWT()
    }


    private fun bindObservers() {
        viewModel.apiData.observe(this) {
            when (it) {
                is NetworkResponse.Loading -> {
                    binding.progress.isVisible = true
                }
                is NetworkResponse.Error -> {
                    binding.progress.isVisible = false
                    if (it.internet) {
                        binding.llData.isVisible = false
                        binding.llNoInternet.isVisible = true
                    }
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                }
                is NetworkResponse.Success -> {
                    binding.progress.isVisible = false
                    binding.llData.isVisible = true
                    binding.llNoInternet.isVisible = false
                    binding.rvTherapy.adapter = TherapyAdapter(this, it.data.data)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encodeToJWT() {
        val nowSeconds = Instant.now().epochSecond
        val exp = nowSeconds + 12777000
        gson = GsonBuilder().create()

        val secret = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCD0KvVxLJEzRBQmcEXf\n" +
                "D2okKCNoUwZY8fc1/1Z4aJuJdg=="
        val header = CustomAuthHeader(Algorithm.ES256.name, "KHZY4KKQQM","JWT")
        val payload = CustomJWTAuthPayload("ULQ5N42B6N", nowSeconds, exp)
        val payloadString = jsonEncoder.toJson(payload)
        Log.d(TAG, "encodeToJWT: $payloadString")
        val token =
            JWT.token(Algorithm.ES256, header, payload, secret, jsonEncoder, encoder, decoder)
        Log.d(TAG, "encodeToJWT: $token")
        val t = JWT.decode(token, jsonDecoder, decoder)

        //commit in test branching for testing conflict
        
        //remote_changes
        
        //one more change

    }


}
