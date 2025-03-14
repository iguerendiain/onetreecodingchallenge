# OneTree Coding Challenge (Kotlin Multiplatform)

## Ignacio Guerendiain



## App Architecture
The app was built based on clean architecture principles and following the Model View ViewModel or  Model View Intent (because I use a state class instead of separate variables for each data on the VM) design pattern. Beyond what was requiered, I tend to choose this kind of method since it allows me to separate the UI from the logic (which in Kotlin Multiplatform is even more important) and it gives me a better result regarding maintainability and flexibility.

### Libraries used
- [SplashScreen](https://developer.android.com/develop/ui/views/launch/splash-screen): Handling of native Android Splash screen
- [Koin](https://insert-koin.io/): Dependency injection
- [Room](https://developer.android.com/kotlin/multiplatform/room): Database
- [NavigationCompose](https://developer.android.com/develop/ui/compose/navigation): Routing and navigation between screens
- [KotlinX Datetime](https://github.com/Kotlin/kotlinx-datetime): Used for generation of epoch value
  Arbol de directorios

### Directory tree

```
App.kt                              Starting point of the app
- app                               App's init or setup files 
- core                              Common app code
- core/di                           Koin init and modules setup
- core/domain                       Business models and repositories
- core/domain/model                 Business models
- core/domain/repository            Repository definitions (interfaces)
- core/domain/repositoryimpl        Repository implementations
- core/storage                      Database init and configuration
- core/theme                        App's theming
- core/theme/db                     Definition of colors (and in bigger apps also fonts)
- core/defs                         Colors and fonts definition
- core/specs                        Specification of which colors and fonts are used
- core/ui                           General reusable composables
- core/utils                        General helpers and utils
- feature                           App features
- feature/tasks                     Tasks features
- feature/tasks/ui                  Composables
- featrue/tasks/vm                  ViewModels and state classes
```

### Acual / Expect classes
Kotlin Multiplatform provides a way of "extending" classes on each platform the app is written to run. While not exactly the same principle as class inheritance is a good analogy. In this app, this mechanism is used as follows:

- `core/storage/RoomDBBuilder.kt` - Provides the funcion `getDatabaseBuilder` for each platform which is needed to access the filesystem on each case.
- `core/utils/LocationService.*.kt` - Provides the `LocationService` class for accessing the GPS and permissions on each platform
- `core/utils/OrientationUtils.*.kt` - Gives access to knowing the orientation of the display for each platfotm

### Models, DBModels, Repositories and model mapper
With the objective of easier changes in repository implementation as well as decoupling the actual source of information from the rest of the app I handle two sets of models. RoomDB requires classes with annotations to handle the underlaying DB, theses classes are found in `core/storage/model` (Actually only one class in this case `TaskDB`) and the app uses the models in `core/domain/model` (Again, only one in this case `Task`).

The implementation of the repositories are responsable for converting between these two types of classes, the RoomDB ones could not even exists if the storage mechanism would be different, like using raw SQLite or storing in files, etc...

For ease of use, I created the object `DBModelMapper` found in `core/storage/ModelMapper.kt` with functions that converts between `Task` and `TaskDB`. In bigger apps I may use libraries such as Retrofit which would have their own classes for backend communication and their own mappers for converting back and forth between those classes and the ones used by the app, done again, in the repository implementations.

### Screen composables and Content composables
I always try to shared as primitive data as posible between components (including composables) in my code. I do this to separate the responsabilities and increase flexibility and ease of testing. That's why, for each screen I create a composable for the screen that's responsible for handling navigation and viewmodel interactions and a composable for the content that only gets exactly what it needs and the corresponding callbacks for interaction.

As you can see in `TaskListContent`, I cannot always pass just primitive data, in this case I'm passing a list of Tasks, it's always good to find a healthy balance, if I were to pass a list of each value that the task is made of it would greatly reduce the maintenability of the code.

### Only one ViewModel and it's scopes
While I've created only one ViewModel for this app, this was done becasue the scope of the app is small enough that makes it unnecessary to separate in more than one ViewModel the UI logic.

However, I know that I am managing the scope of it in two places, this was done on purpose. One of the two scopes is defined in the RootNavigation, I'm injecting an instance of it and then passing it to the task list screen; this was done so the state of the screen is remembered even when navigating to the details (basically the selected filter). On the other hand, the task details screen has it's own instance each time is opened (injecting it as a default param), this is done so all information is cleared once the user leaves the screen.

I could also have constructred a separate ViewModel for all GPS data and/or task details related, due to how the app is contructed (Screens and Content composables) it's not hard to change if in the future the app grows (of course, not the case, only a coding challenge, but still...).

### Reused own code ported to Kotlin Multiplatform
Most of the stuff under `core/ui` is code written by me for other projects in the past. I keep reusing and updating it. In this case I ported it from Android to Kotlin Multiplatform. In some cases you may find it's over engineeried but that's just because they are part of a bigger set of tools that work wogether (specially the `dialog` package).

### Running the application
There's no secial setup beyond what's required by a normal Kotlin Multiplatform app. In Android's case the project should be opened in Android Studio (should work with IntelliJ Idea too haven't tested it ) and just press the run button. More information can be found in the [official Kotlin Multiplaform documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-your-application).

### Considerations and clarifications
- As with other parts of the app, the `strings.xml` used for internationalization of the app could be split in different files if the scope of the app would require it.
- At `App.kt` the composable with the theme definition `MainTheme` it's actually duplicated since it's already added in the Android part of the app for status bar color management. The idea would be to add `MainTheme` in the other platforms too so it can be removed from the `App.kt` file and not be repeated
- Decided to use `.safepadding()` Modifier in some parts of the app only when the screen is in horizontal orientation since for portrait mode the content composables are handling those spaces with `NavbarSpacer` and `StatusbarSpacer`.
- In apps with a bigger scope more than one file could be written for DI module definition instead of only `DIModules.kt` as is the case of the app.
- The same can be said regarding the navigation graph deined at `RootNavigation.kt`, I usually make one graph per feature and then use nested navigation from a root navigation.
- In `core/storage/RoomDB.kt` the `@Suppress("NO_ACTUAL_FOR_EXPECT")` annotation is added by reommendation of the Room documentation. This is, apparently, because the actual classes for each platform for this expect class are generated at compile time.
- I'm not familiar enough with iOS to be able to implement GPS and orientation for that platform. The app should work correctly on iOS since I did implemented what's required for the database. Was not able to test this since I (yet) lack access to a device with MacOS and XCode.
- Having more time I would work on animating the state transitions of the composables, write unit tests and add features like swiping to delete or toggle the "done" state of tasks.
