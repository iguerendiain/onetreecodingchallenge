# OneTree Coding Challenge (Kotlin Multiplatform)

## Ignacio Guerendiain

### Libraries used
- [SplashScreen](https://developer.android.com/develop/ui/views/launch/splash-screen): Handling of native Android Splash screen
- [Koin](https://insert-koin.io/): Dependency injection
- [Room](https://developer.android.com/kotlin/multiplatform/room): Database
- [NavigationCompose](https://developer.android.com/develop/ui/compose/navigation): Routing and navigation between screens
- [KotlinX Datetime](https://github.com/Kotlin/kotlinx-datetime): Used for generation of epoch value

### Considerations
- I'm not familiar enough with iOS to be able to implement GPS and orientation for that platform. The app should work correctly on iOS since I did implemented what's required for the database. Was not able to test this since I lack access to a device with MacOS and XCode (situation which if you decide to sign with me I'll make sure to solve).
- Having more time I would work on animating the state transitions of the composables, write unit tests and add features like swiping to delete or toggle the "done" state of tasks.