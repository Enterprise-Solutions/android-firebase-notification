# Configurando Firebase
  1. Haga click izquierdo en "Tools"
    ![Tools location](.images/01.png)

  2. Seguidamente, haga click izquierdo en "Fibebase"
    ![Firebase location](.images/02.png)

  3. Se abrirá una pestaña que es el _Asistente de Configuración de Firebase_ y deberá seleccionar la opción _Cloud Messaging_ y hacer click en _Setup Firebase Cloud Messaging_
    ![Setup FCM](.images/03.png)

  4. Haga click en el botón "Conect to Firebase" para poder conectar el proyecto a una cuenta Firebase.
    ![Conecting to Firebase](.images/04.png)
    _Obs: se solicitará iniciar sesión con una cuenta de Firebase_

  5. Haga click en "Add FCM to your app" para añadir Firebase Cloud Messaging a la app
    ![Adding FCM](.images/05.png)
    _Obs: se agregará las dependencias de Firebase Cloud Messaging a la App_

  6. Cree un servicio, en este caso lo llamaremos FirebaseMessagingService el cuál extenderá las funcionalidades de FirebaseMessagingService (Desde Firebase)
    ![Services](.images/06.png)
    _Obs: Este servicio es necesario para recibir las notificaciones en la app_

    Aquí sobrescribirá el método onMessageReceived:

    ```@Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    ```
    ![Services](.images/07.png)

  7. Declare lo siguiente en el _App manifest_
    ```
      <service android:name=".FirebaseMessagingService">
        <intent-filter>
            <action android:name="com.google.firebase.MESSAGING_EVENT" />
        </intent-filter>
    </service>
    ```
    ![Services](.images/08.png)

  8. Acceda al token de registro del dispositivo.
      - En el servicio _FirebaseMessagingService_ sobrescribe el siguiente método _onNewToken_
        ```
        @Override
        public void onNewToken(String token) {
            Log.d(TAG, "Refreshed token: " + token);

            // If you want to send messages to this application instance or
            // manage this apps subscriptions on the server side, send the
            // Instance ID token to your app server.
            sendRegistrationToServer(token);
        }
        ```
        ![Services](.images/09.png)
