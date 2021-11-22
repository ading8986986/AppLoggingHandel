# AppLoggingHandel


This is for requirement of send log of mobile events to the server and hanle the logic when it fails to deliver

Arcthitecture Design : Clean Architecure
  - Repository
    - local: Room database to store events that fail to be sent to server
    - remote: Retrofit client send event to server
  - Domain:
    - PostLogEventUseCase
    - SavedFailedEventUsecase
    - DeleteLogEventUsecase : delete the events after being sent successfully
    - LoadFailedEventUseCase: load all failed events in the databese
  - Prentation
    - UI: Activity + Fragment
    - Viewmodel: Fragment viewmodel

Failed events management logic:
  - EventManageWorker extends CoroutineWorker, it is created by PeriodicWorkRequestBuilder,  
    - meaning every 3 hours it will talk to LoadFailedEventUseCase to load failed events, and creates EventWorker for each of them
    - it will conduct the task event when app exits
 
  - EventWorker extends CoroutineWorker, it is created by OnetimeWorkRequestBuilder, meaning it will only conduct one time
    - it will talk to PostLogEventUseCase, to upload the failed events
    - it will talk to DeleteLogEventUsecase it the event gets uploaded successfully.

Design Pattern:
  - Dagger hilt: vimodel,  EventManageWorker and EventWorker will be able to talk to usecase separatly
  - Factory pattern to create EventWorker
  - Builder Patter in creating PeriodicWorkRequestBuilder


Things to improment:
  - will create viewmodel for activity to handle the initialization of EventManageWorker, now the logic is in application
  - will add test case to test each layer
