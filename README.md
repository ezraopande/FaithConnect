# FaithConnect

**FaithConnect** is a mobile application developed using Jetpack Compose and Kotlin with MVVM architecture. The app leverages Retrofit for seamless API data fetching and provides a range of features to support church activities and user management.

## Features

- **Prayers:** View and manage prayer requests and updates.
- **Pastors:** Browse information about pastors, including their profiles and contact details.
- **Sermons:** Access and view sermons from various services and events.
- **Sliders:** Interactive image or content sliders for announcements and highlights.
- **User Management:** 
  - Create and manage user accounts.
  - Secure login and authentication.
  - Account management and profile updates.
- **Bible Study Groups:** Join and interact with Bible study groups, view group details, and access study materials.

## Architecture

- **Jetpack Compose:** For building modern and responsive UI components.
- **MVVM (Model-View-ViewModel):** For maintaining a clean separation of concerns and managing UI-related data.
- **Retrofit:** For handling API requests and responses efficiently.

## Getting Started

### Prerequisites

- Android Studio with the latest stable version.
- Basic knowledge of Kotlin and Jetpack Compose.

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/ezraopande/FaithConnect.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd FaithConnect
    ```

3. **Open the project in Android Studio:**

    - Open Android Studio and select "Open an existing project".
    - Navigate to the cloned repository directory and open it.

4. **Sync the project with Gradle files:**

    - Click on "Sync Project with Gradle Files" in Android Studio to ensure all dependencies are downloaded.

5. **Build and Run the App:**

    - Connect an Android device or start an emulator.
    - Click on the "Run" button in Android Studio to build and run the app.

## API Endpoints

Below are some of the key API endpoints used in **FaithConnect**:

### Prayers
- `GET /api/prayers`: Fetches the list of prayer requests.
- `POST /api/prayers`: Submits a new prayer request.
- `GET /api/prayers/{id}`: Retrieves details of a specific prayer request.
- `PUT /api/prayers/{id}`: Updates a specific prayer request.
- `DELETE /api/prayers/{id}`: Deletes a specific prayer request.

### Pastors
- `GET /api/pastors`: Retrieves information about pastors.
- `GET /api/pastors/{id}`: Retrieves details of a specific pastor.
- `POST /api/pastors`: Adds a new pastor (admin only).
- `PUT /api/pastors/{id}`: Updates information of a specific pastor (admin only).
- `DELETE /api/pastors/{id}`: Deletes a specific pastor (admin only).

### Sermons
- `GET /api/sermons`: Fetches the list of available sermons.
- `GET /api/sermons/{id}`: Retrieves details of a specific sermon.
- `POST /api/sermons`: Uploads a new sermon (admin only).
- `PUT /api/sermons/{id}`: Updates details of a specific sermon (admin only).
- `DELETE /api/sermons/{id}`: Deletes a specific sermon (admin only).

### Sliders
- `GET /api/sliders`: Retrieves content for sliders and announcements.
- `POST /api/sliders`: Adds new slider content (admin only).
- `PUT /api/sliders/{id}`: Updates a specific slider content (admin only).
- `DELETE /api/sliders/{id}`: Deletes a specific slider content (admin only).

### User Management
- `POST /api/register`: Registers a new user account.
- `POST /api/login`: Authenticates a user and logs them in.
- `GET /api/users/{id}`: Retrieves details of a specific user.
- `PUT /api/users/{id}`: Updates user account information.
- `DELETE /api/users/{id}`: Deletes a user account.

### Bible Study Groups
- `GET /api/groups`: Retrieves a list of Bible study groups.
- `GET /api/groups/{id}`: Retrieves details of a specific Bible study group.
- `POST /api/groups`: Creates a new Bible study group (admin only).
- `PUT /api/groups/{id}`: Updates details of a specific Bible study group (admin only).
- `DELETE /api/groups/{id}`: Deletes a specific Bible study group (admin only).

## Contributing

We welcome contributions to improve **FaithConnect**. If you would like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes with descriptive messages.
4. Push your changes to your forked repository.
5. Open a pull request to the main repository.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For any questions or feedback, feel free to reach out to:

- Email: ezraopande@gmail.com
- Phone: +254796759850
