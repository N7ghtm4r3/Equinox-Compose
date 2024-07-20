## EquinoxViewModel

### Usage/Examples

#### TestViewModel

```kotlin
class TestViewModel: EquinoxViewModel() {

    fun refreshRoutine() {
        execRefreshingRoutine(
            currentContext = Test::class.java,
            routine = {
                
                // your refresh routine
                
            }
        )
    }

}
```

#### Test class
```kotlin
class Test {

    @Composable
    fun Screen() {
        
        // instantiate the viewmodel
        val viewModel = TestViewModel()
        
        // set the current context where is a routine is executing
        viewModel.setActiveContext(this::class.java)
        
        // some state to control the visibility of an element on screen
        val show = remember { mutableStateOf(false) }
        
        // start the refreshing routine
        viewModel.refreshRoutine()
        
        // attach the control flag to the refresher and in automatically it will suspend or will restart
        // if that element linked to that flag will appear or disappear
        viewModel.SuspendUntilElementOnScreen(
            elementVisible = show
        )
        
        // an example composable linked to 'show' state
        EquinoxAlertDialog(
            show = show,
            title = "Any title",
            text = "Any text",
            onDismissAction = {
                
                // your code
                
                show.value = false // the refresh routine will be restated
            },
            dismissText = "Dismiss",
            confirmText = "Confimer",
            confirmAction = {

                // your code

                show.value = false // the refresh routine will be restated
            }
        )
        Button(
            onClick = {
                
                // your code
                
                show.value = true // the refresh routine will be suspended
            }
        ) {
            Text(
                text = "Show"
            )
        }
    }

}
```

The other apis will be gradually released

## Authors

- [@N7ghtm4r3](https://www.github.com/N7ghtm4r3)

## Support

If you need help using the library or encounter any problems or bugs, please contact us via the following links:

- Support via <a href="mailto:infotecknobitcompany@gmail.com">email</a>
- Support via <a href="https://github.com/N7ghtm4r3/Equinox-Compose/issues/new">GitHub</a>

Thank you for your help!

## Badges

[![](https://img.shields.io/badge/Google_Play-414141?style=for-the-badge&logo=google-play&logoColor=white)](https://play.google.com/store/apps/developer?id=Tecknobit)
[![Twitter](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/tecknobit)

[![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)

[![](https://jitpack.io/v/N7ghtm4r3/Equinox-Compose.svg)](https://jitpack.io/#N7ghtm4r3/Equinox-Compose)

## Donations

If you want support project and developer

| Crypto                                                                                              | Address                                        | Network  |
|-----------------------------------------------------------------------------------------------------|------------------------------------------------|----------|
| ![](https://img.shields.io/badge/Bitcoin-000000?style=for-the-badge&logo=bitcoin&logoColor=white)   | **3H3jyCzcRmnxroHthuXh22GXXSmizin2yp**         | Bitcoin  |
| ![](https://img.shields.io/badge/Ethereum-3C3C3D?style=for-the-badge&logo=Ethereum&logoColor=white) | **0x1b45bc41efeb3ed655b078f95086f25fc83345c4** | Ethereum |

If you want support project and developer
with <a href="https://www.paypal.com/donate/?hosted_button_id=5QMN5UQH7LDT4">PayPal</a>

Copyright © 2024 Tecknobit

