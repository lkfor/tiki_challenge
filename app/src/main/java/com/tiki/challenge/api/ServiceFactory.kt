package com.tiki.challenge.api

import com.google.gson.*
import com.tiki.challenge.BuildConfig
import com.tiki.challenge.utils.DateTimeUtils
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class ServiceFactory {
    fun provideSearchApi(): KeyWordApi {
        return provideRetrofit(BuildConfig.BASE_API_URL + BuildConfig.API_VERSION).create(KeyWordApi::class.java)
    }

    private fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        Date::class.java,
                        DateDeserializer()
                    ).serializeNulls().create()
                )
            )
            .build()
    }

    private fun getHttpClient(): OkHttpClient {
        val logging =
            HttpLoggingInterceptor()
        logging.level = Level.BODY
        val builder: Builder = Builder()
            .addInterceptor(logging)
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
        return builder.build()
    }


    internal class DateDeserializer : JsonDeserializer<Date?> {
        @Throws(JsonParseException::class)
        override fun deserialize(
            element: JsonElement,
            arg1: Type?,
            arg2: JsonDeserializationContext?
        ): Date? {
            val date = element.asString
            val formatter = SimpleDateFormat(
                DateTimeUtils.PATTERN_DATE_TIME_TIMELINE_yyyy_MM_dd_T_HH_mm_ss,
                Locale.getDefault()
            )
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            return try {
                formatter.parse(date)
            } catch (e: ParseException) {
                System.err.println("Failed to parse Date due to: " + e.message)
                null
            }
        }
    }
}