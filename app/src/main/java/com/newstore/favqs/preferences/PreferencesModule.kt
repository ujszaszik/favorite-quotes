package com.newstore.favqs.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    private const val FILE_NAME = "favqs_001_storage.dat"

    @Provides
    @EncryptedSharedPrefs
    fun provideSharedPreferences(
        @ApplicationContext appContext: Context,
        @MasterKeyAlias masterKeyAlias: String
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            FILE_NAME, masterKeyAlias, appContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @PrivateSharedPrefs
    fun providePrivateModeSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @MasterKeyAlias
    fun provideMasterKeyAlias(): String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

}