# EquinoxScreen

This API is useful to create a screen with a lifecycle management similar to the Android's activities

## Usage

To use the EquinoxScreen API you have to follow this guide for a correct implementation

### Creation of concrete custom screen

Create a concrete custom screen with the custom specifications you need 

```kotlin
class TestScreen : EquinoxScreen(
  loggerEnabled = true // whether enable the logger
) {

    private val viewModel: TestViewModel = TestViewModel()

    @Composable
    override fun ArrangeScreenContent() {
        // the content of the TestScreen to display
    }

    override fun onCreate() {
        super.onCreate()
        // example usage of the onCreate method
        viewModel.setActiveContext(this::class.java)
    }

    override fun onStart() {
        super.onStart()

        // example usage of the onStart method
        viewModel.refreshingRoutine()
    }
  
    override fun onPause() {
        super.onPause()
      
        // example usage of the onPause method
        viewModel.suspendRefresher()
    }
  
    override fun onResume() {
        super.onResume()
      
        // example usage of the onResume method
        viewModel.restartRefresher()
    }
  
    override fun onDestroy() {
        super.onDestroy()

        // example usage of the onDestroy method
        viewModel.suspendRefresher()
    }

}
```

### Display the screen 

For example from the `App` function you can show the screen created before:

```kotlin
@Composable
fun App() { 
    val screen = TestScreen()
    screen.ShowContent()
}
```

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

