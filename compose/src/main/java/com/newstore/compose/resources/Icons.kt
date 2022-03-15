package com.newstore.compose.resources

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.newstore.extension.empty

object Icons {

    @Composable
    fun LogoutIcon() {
        Icon(
            Icons.Default.Logout,
            contentDescription = String.empty,
            tint = Colors.white
        )
    }

    @Composable
    fun SearchIcon() {
        Icon(
            Icons.Default.Search,
            contentDescription = String.empty,
            tint = Colors.white
        )
    }

    @Composable
    fun SearchViewLeadingIcon() {
        Icon(
            Icons.Default.Search,
            contentDescription = String.empty,
            modifier = Modifier
                .padding(Dimens.paddingDefault)
                .size(Dimens.largerIconSize)
        )
    }

    @Composable
    fun SearchViewCloseIcon() {
        Icon(
            Icons.Default.Close,
            contentDescription = String.empty,
            modifier = Modifier
                .padding(Dimens.paddingDefault)
                .size(Dimens.largerIconSize)
        )
    }

    @Composable
    fun BackArrowIcon() {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = String.empty,
            modifier = Modifier
                .padding(Dimens.paddingDefault)
                .size(Dimens.largerIconSize),
            tint = Colors.white
        )
    }

    @Composable
    fun ProfileIcon() {
        Icon(
            Icons.Default.Person,
            contentDescription = String.empty,
            modifier = Modifier
                .padding(Dimens.paddingDefault)
                .size(Dimens.largerIconSize),
            tint = Colors.white
        )
    }

    @Composable
    fun PasswordVisibilityOnIcon() {
        Icon(
            Icons.Filled.Visibility,
            contentDescription = Strings.SHOW_PASSWORD,
        )
    }

    @Composable
    fun PasswordVisibilityOffIcon() {
        Icon(
            Icons.Filled.VisibilityOff,
            contentDescription = Strings.HIDE_PASSWORD,
        )
    }

}