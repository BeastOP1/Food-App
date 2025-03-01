package com.example.foodapp.data.auth

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.foodapp.data.models.GoogleAccount
import com.example.foodapp.ui.theme.Constant
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

class GoogleAuthProvider {

    suspend fun signUp(
        activityContext: Context,
        credentialManager: CredentialManager

    ): GoogleAccount {

        val credit =
            credentialManager.getCredential(activityContext, getCredentialRequest()).credential

        return handleCredential(credit)
    }

    fun handleCredential(credential: Credential): GoogleAccount {

        when {
            credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                val googleIdTokenCredential = credential as GoogleIdTokenCredential
                Log.d("GoogleAuthProvider", "Credential: $googleIdTokenCredential")
                return GoogleAccount(
                    token = googleIdTokenCredential.idToken,
                    displayName = googleIdTokenCredential.displayName ?: "",
                    profileImageUrl = googleIdTokenCredential.profilePictureUri.toString(),
                )
            }

            else -> {
                throw IllegalStateException("Invalid Credential Type")
            }
        }
    }

    private fun getCredentialRequest(): GetCredentialRequest {

        return GetCredentialRequest.Builder()
            .addCredentialOption(
                GetSignInWithGoogleOption.Builder(Constant.WEB_CLIENT).build()
            )
            .build()


    }
}