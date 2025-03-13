package onetree.ignacio.guerendiain.core.utils

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object NavigationUtils {
    const val DEFAULT_ANIMATION_DURATION = 300

    fun AnimatedContentTransitionScope<*>.slideEnterTransition(direction: SlideDirection): EnterTransition {
        return slideIntoContainer(direction, tween(DEFAULT_ANIMATION_DURATION))
    }

    fun AnimatedContentTransitionScope<*>.slideExitTransition(direction: SlideDirection): ExitTransition {
        return slideOutOfContainer(direction, tween(DEFAULT_ANIMATION_DURATION))
    }

    fun AnimatedContentTransitionScope<*>.stayEnterTransition(): EnterTransition {
        return slideInVertically(initialOffsetY = { 0 })
    }

    fun AnimatedContentTransitionScope<*>.stayExitTransition(): ExitTransition {
        return slideOutVertically(targetOffsetY = { 0 })
    }

    fun NavGraphBuilder.enterFromRightExitToLeft(
        route: String,
        arguments: List<NamedNavArgument> = emptyList(),
        enterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = { slideEnterTransition(SlideDirection.Left) },
        exitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = { slideExitTransition(SlideDirection.Left) },
        popEnterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = { slideEnterTransition(SlideDirection.Right) },
        popExitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = { slideExitTransition(SlideDirection.Right) },
        content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
    ) {
        composable(
            route = route,
            arguments = arguments,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
            content = content
        )
    }

    fun NavGraphBuilder.enterFromDownExitToLeft(
        route: String,
        arguments: List<NamedNavArgument> = emptyList(),
        enterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = { slideEnterTransition(SlideDirection.Up) },
        exitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = { slideExitTransition(SlideDirection.Left) },
        popEnterTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = { slideEnterTransition(SlideDirection.Right) },
        popExitTransition: AnimatedContentTransitionScope<*>.() -> ExitTransition = { slideExitTransition(SlideDirection.Down) },
        content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
    ) {
        composable(
            route = route,
            arguments = arguments,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
            content = content
        )
    }

}