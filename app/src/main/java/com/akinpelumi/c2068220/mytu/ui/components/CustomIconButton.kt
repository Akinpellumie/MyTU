//package com.akinpelumi.c2068220.mytu.ui.components
//
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Box
//import androidx.compose.material.ripple.rememberRipple
//import androidx.compose.material3.IconButtonColors
//import androidx.compose.material3.IconButtonDefaults
//import androidx.compose.material3.LocalContentColor
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.semantics.Role
//
//@Composable
//fun CustomIconButton(
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    content: @Composable () -> Unit
//) {
//    Box(
//        modifier = modifier
//            .minimumInteractiveComponentSize()
//            .size(IconButtonTokens.StateLayerSize)
//            .clip(IconButtonTokens.StateLayerShape.toShape())
//            .background(color = colors.containerColor(enabled).value)
//            .clickable(
//                onClick = onClick,
//                enabled = enabled,
//                role = Role.Button,
//                interactionSource = interactionSource,
//                indication = rememberRipple(
//                    bounded = false,
//                    //radius = IconButtonTokens.StateLayerSize / 2
//                )
//            ),
//        contentAlignment = Alignment.Center
//    ) {
//        val contentColor = colors.apply {
//            Color.Transparent
//        }
//        CompositionLocalProvider(LocalContentColor provides contentColor, content = content)
//    }