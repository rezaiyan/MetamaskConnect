# MetaMask Integration Sample App for Android

This Android app demonstrates how to connect with MetaMask to interact with the Ethereum blockchain using an Infura API key. The app allows users to perform basic blockchain operations by leveraging MetaMask's secure wallet management.

## Getting Started

To get started with this sample app, you will need to set up your development environment and replace the Infura API key in the project.

### Prerequisites

- Android Studio
- An Infura account and API key
- MetaMask wallet

### Setting Up

1. **Clone the repository**

   Start by cloning this repository to your local machine using Android Studio or your preferred Git client.

2. **Replace the Infura Key**

   You will need an Infura API key to interact with the Ethereum blockchain. If you don't have one, create an account at [Infura](https://infura.io/) and create a new project to obtain an API key.

   Once you have your Infura API key, navigate to the `gradle.properties` file in your project and replace the placeholder text `YOUR_INFURA_KEY_HERE` with your actual Infura API key.

    ```
    INFURA_API_KEY="YOUR_INFURA_KEY_HERE"
    ```

3. **Build the Project**

   Open the project in Android Studio and build it. Make sure there are no errors and that the build completes successfully.

4. **Run the App**

   Once the build is successful, you can run the app on your Android device or emulator. Follow the prompts in the app to connect with MetaMask and perform blockchain operations.

## Features

- Connect with MetaMask wallet
- View Ethereum account details
- Perform simple blockchain operations

## Contributing

We welcome contributions to this sample app. If you have suggestions or improvements, please submit a pull request or create an issue in the repository.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Disclaimer

This app is intended for demonstration purposes only. Be cautious when interacting with the blockchain and ensure you understand the implications of your actions.

## Support

If you have any questions or run into issues setting up the app, please create an issue in the repository, and we'll do our best to assist you.
